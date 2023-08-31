package com.crio.starter.dto;

import java.time.LocalDateTime;

public class Meme {


    private String id;
    private LocalDateTime timeStamp;
    private String name;
    private String url;
    private String caption;


    // ..........C-G-S................

    public Meme(String userName, String url, String caption) {
        this.timeStamp = LocalDateTime.now();

        this.name = userName;
        this.url = url;
        this.caption = caption;
    }

    public Meme() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
