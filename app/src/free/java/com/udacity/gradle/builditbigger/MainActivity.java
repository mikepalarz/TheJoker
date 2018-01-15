package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.palarz.mike.jokedisplayer.JokeDisplayer;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity
        implements EndpointsAsyncTask.PostExecuteCallback {

    // A reference to our interstitial ad
    InterstitialAd mInterstitialAd;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // We first create a new interstitial ad instance and set its unit ID to the ID for testing
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        // We then load the ad, but do not display it yet
        mInterstitialAd.loadAd(new AdRequest.Builder()
                                                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                                .build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        // When the button is clicked, we simply launch the AsyncTask
        new EndpointsAsyncTask(this, mProgressBar).execute();
    }

    @Override
    public void supplyJoke(final String theJoke) {

        // If the ad has finished loading...
        if (mInterstitialAd.isLoaded()) {
            //We will show the ad...
            mInterstitialAd.show();
            //We'll create a listener for when the ad is closed...
            mInterstitialAd.setAdListener(new AdListener(){

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    //And load another ad in case the user decides to view more than one joke
                    mInterstitialAd.loadAd(new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build());

                    // Finally, we'll launch JokeDisplayer to show the joke
                    Intent jokeIntent = new Intent(getApplicationContext(), JokeDisplayer.class);
                    jokeIntent.putExtra(JokeDisplayer.BUNDLE_EXTRA_KEY_JOKE, theJoke);
                    startActivity(jokeIntent);
                }

            });
        }
        else {
            // If the ad isn't loaded, we'll simply show the joke
            Intent jokeIntent = new Intent(this, JokeDisplayer.class);
            jokeIntent.putExtra(JokeDisplayer.BUNDLE_EXTRA_KEY_JOKE, theJoke);
            startActivity(jokeIntent);
        }

    }
}
