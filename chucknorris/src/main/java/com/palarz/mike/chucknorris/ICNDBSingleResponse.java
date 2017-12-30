package com.palarz.mike.chucknorris;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class created to mimic a JSON response from ICNDB which contains a single joke.
 */

public class ICNDBSingleResponse {

    @SerializedName("type")
    String mType;

    @SerializedName("value")
    Joke mJoke;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public Joke getJoke() {
        return mJoke;
    }

    public void setJoke(Joke joke) {
        this.mJoke = joke;
    }

}
