/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.palarz.mike.jokedisplayer.JokeDisplayerActivity;

/**
 * Primary purpose: This activity is largely responsible for containing the fragment. In addition,
 * it is also responsible for executing an AsyncTask when the joke button is clicked as well as
 * implementing the callback for the AsyncTask.
 */
public class MainActivity extends AppCompatActivity
        implements EndpointsAsyncTask.PostExecuteCallback {

    // A reference to our ProgressBar
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We instantiate our ProgressBar
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    // The onClick() of the joke button
    public void tellJoke(View view) {
        // When the button is clicked, we simply launch the AsyncTask
        new EndpointsAsyncTask(this, mProgressBar).execute();
    }

    // The callback method of the EndpointsAsyncTask. When the EndpointsAsyncTask is finished, we
    // will display the joke within the JokeDisplayerActivity activity.
    @Override
    public void supplyJoke(String theJoke) {
        Intent jokeIntent = new Intent(this, JokeDisplayerActivity.class);
        jokeIntent.putExtra(JokeDisplayerActivity.BUNDLE_EXTRA_KEY_JOKE, theJoke);
        startActivity(jokeIntent);
    }
}
