package com.palarz.mike.chucknorris;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mpala on 12/29/2017.
 */

public interface JokeClient {

    String BASE_URL = "http://api.icndb.com/";

    @GET("jokes")
    Call<List<Joke>> getJokes();

}
