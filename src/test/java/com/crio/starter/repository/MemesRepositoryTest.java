package com.crio.starter.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@AutoConfigureMockMvc
@SpringBootTest
public class MemesRepositoryTest {

    @Autowired
    MemesRepository memesRepository;

    // POST Request for Mongo DataBase:
    // curl --location --request POST 'http://localhost:8081/memes/' \
    // --header 'Content-Type: application/json' \
    // --data-raw '{
    // "name": "ashok kumar",
    // "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",
    // "caption": "This is a meme"
    // }'
    
    //@Disabled
    @Test
    void existsByNameTest(){
        assertTrue(memesRepository.existsByName("ashok kumar"));
        assertFalse(memesRepository.existsByName("I don't exist in DB"));
    }

    //@Disabled
    @Test
    void existsByNameAndUrlAndCaptionTest(){
        // Given
        String name = "ashok kumar";
        String url = "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg";
        String caption = "This is a meme";
        // When
        // Then
        assertTrue(memesRepository.existsByNameAndUrlAndCaption(name, url, caption));
        assertFalse(memesRepository.existsByNameAndUrlAndCaption("n", "ff", "ccc"));
    }

    //@Disabled
    @Test
    void findByNameTest(){
        // Given
        String name = "ashok kumar";
        // When
        // Then
        assertTrue(memesRepository.findByName("n").get().isEmpty());
        assertFalse(memesRepository.findByName(name).get().isEmpty());
    }

    //@Disabled
    @Test
    void findByUrlTest(){
        // Given
        String url = "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg";
        // When
        // Then
        assertTrue(memesRepository.findByUrl("ff").get().isEmpty());
        assertFalse(memesRepository.findByUrl(url).get().isEmpty());
    }

    //@Disabled
    @Test
    void findByCaptionTest(){
        // Given
        String caption = "This is a meme";
        // When
        // Then
        assertTrue(memesRepository.findByCaption("ccc").get().isEmpty());
        assertFalse(memesRepository.findByCaption(caption).get().isEmpty());
    }

    //@Disabled
    @Test
    void findByNameAndUrlAndCaptionTest(){
        // Given
        String name = "ashok kumar";
        String url = "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg";
        String caption = "This is a meme";
        // When
        // Then
        assertTrue(memesRepository.findByNameAndUrlAndCaption("n", "ff", "ccc").get().isEmpty());
        assertFalse(memesRepository.findByNameAndUrlAndCaption(name, url, caption).get().isEmpty());
    }
}
