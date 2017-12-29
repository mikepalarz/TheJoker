package com.palarz.mike.chucknorris;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckNorris {

    List<Joke> mJokes;

    public ChuckNorris() {
        this.mJokes = null;
    }

    public void getJokes(){

        // We use Retrofit in order to obtain the HTTP response and parse the JSON data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JokeClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // We then create an instance of our interface and create our Call object
        JokeClient client = retrofit.create(JokeClient.class);
        Call<List<Joke>> call = client.getJokes();

        call.enqueue(new Callback<List<Joke>>() {
            // If we were successful in obtaining a response to our HTTP request...
            @Override
            public void onResponse(Call<List<Joke>> call, Response<List<Joke>> response) {
                // ...we extract the jokes from the response...
                mJokes = response.body();
            }

            // If we failed to obtain an HTTP response, we at least notify the user.
            @Override
            public void onFailure(Call<List<Joke>> call, Throwable t) {

            }
        });
    }
}
