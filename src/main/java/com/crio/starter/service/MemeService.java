package com.crio.starter.service;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.repositoryservices.MemesRepositoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Provider; // Provider class is defined in javax, fasterXML, and others. Why
                              // Javax? I copied from QEats
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
// @RequiredArgsConstructor
public class MemeService {
  @Autowired
  private MemesRepositoryService memesRepositoryService;
  // In Crio's empty template for MVCS, it is final and @RequiredArgsCon... is resolving it.
  // How?

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  /**
   * Fetches a ModelMapper instance.
   *
   * @return ModelMapper
   */
  @Bean // Want a new obj every time
  @Scope("prototype")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  // TODO: Why I don't need to explicity define which Http Response should be sent when an exception
  // happens?
  // Also, why the string message I am putting is not getting published anywhere?
  public List<Meme> getLatest100Memes() throws ResponseStatusException {
    List<Meme> latest100Memes = new ArrayList<>();
    Optional<List<MemeEntity>> optionalLatest100MemeEntities =
        memesRepositoryService.getLatest100Memes();
    List<MemeEntity> latest100MemeEntities = optionalLatest100MemeEntities.get();
    // List<MemeEntity> latest100MemeEntities = optionalLatest100MemeEntities.orElseThrow(()-> new
    // ResponseStatusException(HttpStatus.NO_CONTENT, "No meme stored"));
    // List<MemeEntity> latest100MemeEntities = optionalLatest100MemeEntities.orElseThrow(()-> new
    // ResponseStatusException(HttpStatus.NOT_FOUND, "No meme stored")); // NOT_FOUND is wrong
    // status, I put it for test only.
    // List<MemeEntity> latest100MemeEntities = optionalLatest100MemeEntities.orElseThrow(()-> new
    // MemeNotFoundException("No meme stored"));
    ModelMapper mp = modelMapperProvider.get();
    for (MemeEntity memeEntity : latest100MemeEntities) {
      latest100Memes.add(mp.map(memeEntity, Meme.class));
    }
    return latest100Memes;
  }

  public Meme getMemeById(String id) {
    MemeEntity memeEntity = memesRepositoryService.getMemeById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meme not Found"));
    ModelMapper mp = modelMapperProvider.get();
    return mp.map(memeEntity, Meme.class);
  }

  public Meme saveAndRetrunMeme(PostMemeRequest postMemeRequest) throws ResponseStatusException {
    String name = postMemeRequest.getName();
    String url = postMemeRequest.getUrl();
    String caption = postMemeRequest.getCaption();
    if (isDuplicateMeme(name, url, caption)) {
      System.out.println("sout message: Received a duplicate request");
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Duplicate request. A Meme with similar attributes already exist");
      // throw new ResponseStatusException(HttpStatus.NO_CONTENT , "Duplicate request. A Meme with
      // similar attributes already exist");
      // If I am forcing a wrong HttpStatus then there is no output on the client side.
    } else {
      Meme incomingMeme = new Meme(name, url, caption);
      ModelMapper mp = modelMapperProvider.get();
      MemeEntity inputMemeEntity = mp.map(incomingMeme, MemeEntity.class);
      MemeEntity outGoingMemeEntity = memesRepositoryService.saveAndRetrunMeme(inputMemeEntity);
      Meme outGoingMeme = mp.map(outGoingMemeEntity, Meme.class);
      return outGoingMeme;
    }
  }

  private boolean isDuplicateMeme(String name, String url, String caption) {
    return memesRepositoryService.isDuplicateMeme(name, url, caption);
  }

}
