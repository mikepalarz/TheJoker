package com.palarz.mike.chucknorris;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mpala on 12/29/2017.
 */

public class Joke {

    @SerializedName("id")
    int mID;

    @SerializedName("joke")
    String mJoke;

    @SerializedName("categories")
    String[] mCategories;

    public Joke() {
        this.mID = 0;
        this.mJoke = "";
        this.mCategories = new String[]{};
    }

    public Joke(int ID, String joke, String[] categories) {
        this.mID = ID;
        this.mJoke = joke;
        this.mCategories = categories;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String joke) {
        this.mJoke = joke;
    }

    public String[] getCategories() {
        return mCategories;
    }

    public void setCategories(String[] categories) {
        this.mCategories = categories;
    }
}
