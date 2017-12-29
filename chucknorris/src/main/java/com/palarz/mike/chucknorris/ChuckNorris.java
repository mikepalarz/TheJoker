package com.palarz.mike.chucknorris;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckNorris {

    List<ICNDBResponse.Joke> mJokes;

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
        Call<ICNDBResponse> call = client.getJokes();

        call.enqueue(new Callback<ICNDBResponse>() {
            // If we were successful in obtaining a response to our HTTP request...
            @Override
            public void onResponse(Call<ICNDBResponse> call, Response<ICNDBResponse> response) {
                System.out.println("This is the request body: " + call.request().body());

                // ...we extract the jokes from the response
                mJokes = response.body().getJokes();
                System.out.println("The callback was a success");
                System.out.println("Newly added jokes: ");
                for (ICNDBResponse.Joke currentJoke : mJokes) {
                    System.out.println(currentJoke.getJoke());
                }
            }

            @Override
            public void onFailure(Call<ICNDBResponse> call, Throwable t) {
                System.out.println("This is the request URL: " + call.request().url());
                System.out.println("The callback was a failure");
            }
        });
    }

    // TODO: Keep in mind that the web API also provides a feature to provide a random joke
    public String getRandomJoke() {
        if (mJokes != null || !(mJokes.isEmpty())) {
            int index = new Random().nextInt(mJokes.size());
            ICNDBResponse.Joke currentJoke = mJokes.get(index);
            return currentJoke.getJoke();
        }
        else {
            return "No jokes are available at this time.";
        }
    }

    public static void main(String[] args) {
        ChuckNorris chuckNorris = new ChuckNorris();
        chuckNorris.getJokes();
    }

}
