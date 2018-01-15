package com.palarz.mike.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayer extends AppCompatActivity {

    public static final String BUNDLE_EXTRA_KEY_JOKE = "com.palarz.mike.jokedisplayer.joke";

    String mJoke;
    TextView mJokeTV;

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
