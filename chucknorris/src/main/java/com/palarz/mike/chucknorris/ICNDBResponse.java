package com.palarz.mike.chucknorris;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mpala on 12/29/2017.
 */

public class ICNDBResponse {

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

}


