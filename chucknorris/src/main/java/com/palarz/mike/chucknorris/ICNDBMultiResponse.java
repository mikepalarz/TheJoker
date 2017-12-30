package com.palarz.mike.chucknorris;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class created to mimic a JSON response from ICNDB which contains an array of jokes.
 */

public class ICNDBMultiResponse {

    @SerializedName("type")
    String mType;

    @SerializedName("value")
    List<Joke> mJokes;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public List<Joke> getJokes() {
        return mJokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.mJokes = jokes;
    }

}


