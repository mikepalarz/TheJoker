package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    private String myJoke;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public String getJoke() {
        return myJoke;
    }

    public void setJoke(String joke) {
        this.myJoke = joke;
    }
}