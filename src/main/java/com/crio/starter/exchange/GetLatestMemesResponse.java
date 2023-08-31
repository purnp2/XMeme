package com.crio.starter.exchange;

// import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import com.crio.starter.dto.Meme;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO: I am not using this class in the controller. Do I need this class at all?
// @Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatestMemesResponse {

    private List<Meme> memes = new ArrayList<>();

    // ............C-G-S................
    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

    public GetLatestMemesResponse(List<Meme> memes) {
        this.memes = memes;
    }

    public GetLatestMemesResponse() {
        // Default constructor
    }

}
