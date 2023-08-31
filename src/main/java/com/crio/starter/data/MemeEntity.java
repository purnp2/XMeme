package com.crio.starter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Data
@Document(collection = "memesCollection")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemeEntity {
    @Id
    private String id;

    // TODO: HOW can I make it FINAL?
    @JsonIgnore
    private LocalDateTime timeStamp;

    @NonNull
    private String name;

    @lombok.NonNull
    private String url;

    @lombok.NonNull
    private String caption;

    public MemeEntity(@NonNull String name, @NonNull String url, @NonNull String caption) {
        this.name = name;
        this.url = url;
        this.caption = caption;
        this.timeStamp = LocalDateTime.now();
    }

    public MemeEntity() {}

}
