package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.crio.starter.dto.Meme;
import com.crio.starter.service.MemeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

// @SpringBootTest(classes = {App.class})
// @ExtendWith(SpringExtension.class)
// @MockitoSettings(strictness = Strictness.STRICT_STUBS)
// @AutoConfigureMockMvc
// @DirtiesContext
// @ActiveProfiles("test")

@AutoConfigureMockMvc
@SpringBootTest
class MemeControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MemeService memeService;

  @Test
  void getLatestMemesReturnsAMemeTest() throws Exception {
    // given
    Meme meme = new Meme("name123", "https://BrooksWasHere.com", "This is a caption");
    Mockito.doReturn(new ArrayList<Meme>(Arrays.asList(meme))).when(memeService)
        .getLatest100Memes();

    // when
    // URI uri1 = UriComponentsBuilder.fromPath("/memes").queryParam("").build().toUri(); // /memes?
    URI uri = UriComponentsBuilder.fromPath("/memes").build().toUri(); // /memes

    MockHttpServletResponse response = // From Crio team, improvement suggested below. Both are
                                       // defined in the springframework.test.*
        mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_VALUE)).andReturn().getResponse();

    // MvcResult result = //ChatGPT suggested to use MvcResult as it help to access the response and
    // its content.
    // mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
    // .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    // then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule()); // Not necessary here though
    // List<Meme> getMemesResponse = mapper.readValue(responseStr, List.class); // mapper doesn't
    // know what is the Generic type (Meme) here so bad choice
    List<Meme> getMemesResponse = mapper.readValue(responseStr, new TypeReference<List<Meme>>() {});
    // ChatGPT suggested the use of TypeReference<List<Meme>>(){}

    assertEquals(1, getMemesResponse.size());
    Mockito.verify(memeService, Mockito.times(1)).getLatest100Memes();
  }



  // TODO: This is incomplete test below. I have to test weather the ID passed is getting correctly
  // read.
  @Test
  void getMemeByIdShouldReadIdCorrectlyTest() throws Exception {

    // given
    // URI uri = UriComponentsBuilder.fromPath("/memes").path("someId123").build().toUri(); //
    // /memessomeId123
    URI uri = UriComponentsBuilder.fromPath("/memes").pathSegment("someId123").build().toUri();
    // /memes/someId123
    // URI uri1 = UriComponentsBuilder.fromPath("/memes").queryParam("someId123").build().toUri();
    // /memes?someId123
    // URI uri2 = UriComponentsBuilder.fromPath("/memes").fragment("someId123").build().toUri(); //
    // /memes#someId123

    // when
    mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_VALUE)).andReturn().getResponse();

    // then
    ArgumentCaptor<String> idParameter = ArgumentCaptor.forClass(String.class);
    verify(memeService, times(1)).getMemeById(idParameter.capture());
    assertEquals("someId123", idParameter.getValue().toString());
  }
}
