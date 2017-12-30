package com.palarz.mike.chucknorris;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckNorris {

    List<Joke> mJokes;
    public String mRandomJoke;

    public ChuckNorris() {
        this.mJokes = null;
        this.mRandomJoke = "";
    }

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
                    System.out.println(currentJoke.getJoke());
                }
            }

            @Override
            public void onFailure(Call<ICNDBMultiResponse> call, Throwable t) {
                System.out.println("This is the request URL: " + call.request().url());
                System.out.println("The callback was a failure");
            }
        });
    }

    // TODO: Keep in mind that the web API also provides a feature to provide a random joke
    public String getRandomJokeFromArray() {
        if (mJokes != null || !(mJokes.isEmpty())) {
            int index = new Random().nextInt(mJokes.size());
            Joke currentJoke = mJokes.get(index);
            return currentJoke.getJoke();
        }
        else {
            return "No jokes are available at this time.";
        }
    }

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
                    System.out.println(currentJoke.getJoke() + "\n");
                }
            }

            @Override
            public void onFailure(Call<ICNDBMultiResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
            }
        });
    }

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
                // TODO: Clean this up at some point. This looks really confusing with getJoke()
                // being called twice. Try to differentiate the different method calls somehow.
                mRandomJoke = response.body().getJoke().getJoke();
                System.out.println("The new random joke: " + mRandomJoke);
            }

            @Override
            public void onFailure(Call<ICNDBSingleResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
                mRandomJoke = "Joke could not be provided";
            }
        });
    }

    public void getRandomNerdyJoke(){
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
                // TODO: Clean this up at some point. This looks really confusing with getJoke()
                // being called twice. Try to differentiate the different method calls somehow.
                mRandomJoke = response.body().getJoke().getJoke();
                System.out.println("The new random nerdy joke: " + mRandomJoke);
            }

            @Override
            public void onFailure(Call<ICNDBSingleResponse> call, Throwable t) {
                System.out.println("The callback was a failure");
                mRandomJoke = "Joke could not be provided";
            }
        });
    }

    public static void main(String[] args) {
        ChuckNorris chuckNorris = new ChuckNorris();
//        chuckNorris.getAllJokes();
//        chuckNorris.getNerdyJokes();
        chuckNorris.getRandomNerdyJoke();
    }

}
