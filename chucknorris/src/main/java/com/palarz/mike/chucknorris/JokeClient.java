package com.palarz.mike.chucknorris;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * A Retrofit client used to pull the jokes from ICNDB.
 */

public interface JokeClient {

    // String used for the base URL
    String BASE_URL = "http://api.icndb.com/";
    // String used to identify only the nerdy jokes from the JSON response
    String CATEGORY_NERDY = "nerdy";

    // A method which returns every single joke from ICNDB
    @GET("jokes")
    Call<ICNDBMultiResponse> getAllJokes();

    // A method which returns only jokes for given categories. This method is meant to be used
    // along with CATEGORY_NERDY to specify only the nerdy jokes.
    @GET("jokes")
    Call<ICNDBMultiResponse> getJokesByCategory(@Query("limitTo") String[] categories);

    // A method which returns a random joke from any category
    @GET("jokes/random")
    Call<ICNDBSingleResponse> getRandomJoke();

    // A method which returns a random joke form a given category. It is meant to be used with
    // CATEGORY_NERDY.
    @GET("jokes/random")
    Call<ICNDBSingleResponse> getRandomNerdyJoke(@Query("limitTo") String[] categories);

}
