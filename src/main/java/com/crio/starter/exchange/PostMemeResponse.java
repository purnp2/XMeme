package com.crio.starter.exchange;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostMemeResponse {

    private String id;


    // ..........C-G-S............
    public PostMemeResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
