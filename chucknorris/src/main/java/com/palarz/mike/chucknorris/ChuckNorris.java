package com.palarz.mike.chucknorris;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A class that is used to make use of the Retrofit client in order make use of all of the different
 * type of jokes that can be obtained.
 */

public class ChuckNorris {

    List<Joke> mJokes;
    public String mRandomJoke;

    public ChuckNorris() {
        this.mJokes = null;
        this.mRandomJoke = "";
    }

    // Gets every single joke from ICNDB
    public void getAllJokes(){

        // We use Retrofit in order to obtain the HTTP response and parse the JSON data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JokeClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // We then create an instance of our interface and create our Call object
        JokeClient client = retrofit.create(JokeClient.class);
        Call<ICNDBMultiResponse> call = client.getAllJokes();

        call.enqueue(new Callback<ICNDBMultiResponse>() {
            // If we were successful in obtaining a response to our HTTP request...
            @Override
            public void onResponse(Call<ICNDBMultiResponse> call, Response<ICNDBMultiResponse> response) {
                System.out.println("This is the request body: " + call.request().body());

                // ...we extract the jokes from the response
                mJokes = response.body().getJokes();
                System.out.println("The callback was a success");
                System.out.println("Newly added jokes: ");
                for (Joke currentJoke : mJokes) {
                    System.out.println(currentJoke.getJokeString());
                }
            }

            @Override
            public void onFailure(Call<ICNDBMultiResponse> call, Throwable t) {
                System.out.println("This is the request URL: " + call.request().url());
                System.out.println("The callback was a failure");
            }
        });
    }

    // Gets all the nerdy jokes from ICNDB
    public void getNerdyJokes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JokeClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JokeClient client = retrofit.create(JokeClient.class);
        Call<ICNDBMultiResponse> call = client.getJokesByCategory(new String[]{JokeClient.CATEGORY_NERDY});
        call.enqueue(new Callback<ICNDBMultiResponse>() {
            @Override
            public void onResponse(Call<ICNDBMultiResponse> call, Response<ICNDBMultiResponse> response) {
                System.out.println("The callback was a success");
                mJokes = response.body().getJokes();
                System.out.println("Newly added nerdy jokes: ");
                for (Joke currentJoke : mJokes) {
                    System.out.println("Joke category: " + currentJoke.getCategories()[0]);
                    System.out.println(currentJoke.getJokeString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<ICNDBMultiResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
            }
        });
    }

    // Gets any random joke form ICNDB
    public void getRandomJoke(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JokeClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JokeClient client = retrofit.create(JokeClient.class);
        Call<ICNDBSingleResponse> call = client.getRandomJoke();
        call.enqueue(new Callback<ICNDBSingleResponse>() {
            @Override
            public void onResponse(Call<ICNDBSingleResponse> call, Response<ICNDBSingleResponse> response) {
                System.out.println("The callback was a success");
                mRandomJoke = response.body().getJoke().getJokeString();
                System.out.println("The new random joke: " + mRandomJoke);
            }

            @Override
            public void onFailure(Call<ICNDBSingleResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
                mRandomJoke = "Joke could not be provided";
            }
        });
    }

    // Gets a random nerdy joke from ICNDB
    public String getRandomNerdyJoke(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JokeClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JokeClient client = retrofit.create(JokeClient.class);
        Call<ICNDBSingleResponse> call = client.getRandomNerdyJoke(new String[]{JokeClient.CATEGORY_NERDY});
        call.enqueue(new Callback<ICNDBSingleResponse>() {
            @Override
            public void onResponse(Call<ICNDBSingleResponse> call, Response<ICNDBSingleResponse> response) {
                System.out.println("The callback was a success");
                mRandomJoke = response.body().getJoke().getJokeString();
                System.out.println("The new random nerdy joke: " + mRandomJoke);
            }

            @Override
            public void onFailure(Call<ICNDBSingleResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
                mRandomJoke = "Joke could not be provided";
            }
        });

        return mRandomJoke;
    }

    // main() was previously used to test out each of the method calls to ensure that they worked.
    public static void main(String[] args) {

    }

}
