/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Primary purpose: This AsyncTask implementation is responsible for obtaining jokes from the GCE.
 * It was designed to be executed whenever a new joke request is made.
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    /*
     * A callback interface responsible for different activities and other classes to handle their
     * own implementation of what should happen during onPostExecute().
     */
    interface PostExecuteCallback {
        void supplyJoke(String theJoke);
    }

    private static MyApi myApiService = null;

    // An instance of our callback
    private static PostExecuteCallback mCallback = null;

    // A reference to the ProgressBar
    private ProgressBar mProgressBar;

    public EndpointsAsyncTask(PostExecuteCallback callback, ProgressBar progressBar){
        this.mCallback = callback;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        if (mProgressBar != null){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

        mCallback.supplyJoke(result);
    }
}