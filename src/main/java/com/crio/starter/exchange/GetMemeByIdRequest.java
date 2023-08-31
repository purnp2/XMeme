package com.crio.starter.exchange;

import lombok.Data;

@Data
public class GetMemeByIdRequest {
    private String id = null;
    // private String userName = null;
    // private String caption = null;
    // private String uriMemeImage = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
