/*
* The following code is the property and sole work of Mike Palarz, a student at Udacity.
*/

package com.palarz.mike.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Primary purpose: A simple activity which displays a received joke from another activity. For the
 * purposes of this project, it is responsible for displaying a joke received from MainActivity,
 * which obtains the joke from the GCE + Java library.
 */
public class JokeDisplayer extends AppCompatActivity {

    /*
    * A string that is used for the Bundle within the Intent that launches this activity. It is
    * used as a key for the joke.
    */
    public static final String BUNDLE_EXTRA_KEY_JOKE = "com.palarz.mike.jokedisplayer.joke";

    // Our joke and out TextView that displays the joke
    String mJoke;
    TextView mJokeTV;

    // Super simple: obtains the joke and displays it.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        Bundle receivedBundle = getIntent().getExtras();
        mJoke = receivedBundle.getString(BUNDLE_EXTRA_KEY_JOKE, getString(R.string.joke_error));

        mJokeTV = (TextView) findViewById(R.id.joke_displayer_the_joke);
        mJokeTV.setText(mJoke);
    }
}
