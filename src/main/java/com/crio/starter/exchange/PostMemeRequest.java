package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
// import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// @Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMemeRequest {

    //@NonNull
    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotBlank
    //@lombok.NonNull
    @NotEmpty
    private String url;

    @NotNull
    @NotBlank
    //@lombok.NonNull
    @NotEmpty
    private String caption;


    // ..........C-G-S............
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
