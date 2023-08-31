package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemesRepository extends MongoRepository<MemeEntity, String> {

  public Optional<List<MemeEntity>> findTop100ByOrderByTimeStampDesc();

  public Optional<MemeEntity> findById(String id);

  public boolean existsByNameAndUrlAndCaption(String name, String url, String caption);

  public boolean existsByName(String name);

  public Optional<List<MemeEntity>> findByNameAndUrlAndCaption(String name, String url,
      String caption);

  public Optional<List<MemeEntity>> findByName(String name);

  public Optional<List<MemeEntity>> findByUrl(String url);

  public Optional<List<MemeEntity>> findByCaption(String caption);

}
