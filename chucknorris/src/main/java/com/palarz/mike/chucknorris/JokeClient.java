package com.palarz.mike.chucknorris;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mpala on 12/29/2017.
 */

public interface JokeClient {

    String BASE_URL = "http://api.icndb.com/";
    String CATEGORY_NERDY = "nerdy";
    String CATEGORY_EXPLICIT = "explicit";

    @GET("jokes")
    Call<ICNDBMultiResponse> getAllJokes();

    @GET("jokes")
    Call<ICNDBMultiResponse> getJokesByCategory(@Query("limitTo") String[] categories);

    @GET("jokes/random")
    Call<ICNDBSingleResponse> getRandomJoke();

    @GET("jokes/random")
    Call<ICNDBSingleResponse> getRandomNerdyJoke(@Query("limitTo") String[] categories);

}
