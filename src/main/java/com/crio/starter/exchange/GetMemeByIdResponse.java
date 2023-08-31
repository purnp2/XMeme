package com.crio.starter.exchange;

import lombok.Data;
import com.crio.starter.dto.Meme;


// TODO: Looks like we don't need this class as we are directly returning the Meme object
// How to understand this conflict: Should I have an DTO class or not?
// Should I create Request-Response class for each type of possible request? - It will be a lot of
// classes if there are multiple possible APIs

@Data
public class GetMemeByIdResponse {
    private Meme meme = null;

    // .......C-G-S............
    public GetMemeByIdResponse(Meme meme) {
        this.meme = meme;
    }

    public Meme getMeme() {
        return meme;
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
    }

}
