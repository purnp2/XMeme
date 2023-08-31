package com.crio.starter.controller;

import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetMemeByIdRequest;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.service.MemeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeController {
  @Autowired
  private MemeService memeService;

  // TODO: Again, I didn't use the GetLatestMemesResponse. Should I create those exchange classes or
  // not?
  @GetMapping("/memes")
  public ResponseEntity<List<Meme>> getLatest100Memes() {
    List<Meme> latest100Memes = memeService.getLatest100Memes();
    return ResponseEntity.ok().body(latest100Memes);
  }

  @GetMapping("/memes/{id}")
  public ResponseEntity<Meme> getMemeByID(GetMemeByIdRequest getMemeByIdRequest) {
    String id = getMemeByIdRequest.getId();
    Meme memeById = memeService.getMemeById(id);
    return ResponseEntity.ok().body(memeById);
  }


  // TODO: I can map the RequestBody in to either the PostMemeRequest or Meme classes.
  // Which one should I do? If 1st then should I modelMap PostMemeRequest into MemeEntity?
  // Do the above in Controller layer or the service layer?
  // If 2nd then is this standary processor to adapt all requests classes into a DTO? Should there
  // be DTO or not?
  // @RequestMapping("/memes")
  @PostMapping("/memes")
  public ResponseEntity<PostMemeResponse> createMeme(@Valid @RequestBody PostMemeRequest postMemeRequest) {
    Meme outputMeme = memeService.saveAndRetrunMeme(postMemeRequest);
    PostMemeResponse postMemeResponse = new PostMemeResponse(outputMeme.getId());
    return ResponseEntity.ok().body(postMemeResponse);
  }

}
