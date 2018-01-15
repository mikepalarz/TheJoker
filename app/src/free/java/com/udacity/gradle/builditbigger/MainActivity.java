/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

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
import com.palarz.mike.jokedisplayer.JokeDisplayerActivity;

/**
 * Primary purpose: This activity is largely responsible for containing the fragment. In addition,
 * it is also responsible for executing an AsyncTask when the joke button is clicked as well as
 * implementing the callback for the AsyncTask. For the free flavor, it is also responsible for
 * displaying ads (both banner and interstitial).
 */
public class MainActivity extends AppCompatActivity
        implements EndpointsAsyncTask.PostExecuteCallback {

    // A reference to our interstitial ad
    InterstitialAd mInterstitialAd;

    // A reference to our ProgressBar
    ProgressBar mProgressBar;


    /*
    In the case of the free version, we will be showing ads. We handle the interstitial ads within
    the activity. This is because we'd like to show the interstitial ad once the EndpointsAsyncTask
    has finished. Because the task is implemented within the activity, then the interstitial ads
    are as well.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We instantiate our ProgressBar
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // We first create a new interstitial ad instance and set its unit ID to the ID for testing
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        // We then load the ad, but do not display it yet
        mInterstitialAd.loadAd(new AdRequest.Builder()
                                                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                                .build());
    }

    // The onClick() of the joke button
    public void tellJoke(View view) {
        // When the button is clicked, we simply launch the AsyncTask
        new EndpointsAsyncTask(this, mProgressBar).execute();
    }

    // The callback method of the EndpointsAsyncTask. When the EndpointsAsyncTask is finished, we
    // will display the joke within the JokeDisplayerActivity activity.
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

                    // Finally, we'll launch JokeDisplayerActivity to show the joke
                    Intent jokeIntent = new Intent(getApplicationContext(), JokeDisplayerActivity.class);
                    jokeIntent.putExtra(JokeDisplayerActivity.BUNDLE_EXTRA_KEY_JOKE, theJoke);
                    startActivity(jokeIntent);
                }

            });
        }
        else {
            // If the ad isn't loaded, we'll simply show the joke
            Intent jokeIntent = new Intent(this, JokeDisplayerActivity.class);
            jokeIntent.putExtra(JokeDisplayerActivity.BUNDLE_EXTRA_KEY_JOKE, theJoke);
            startActivity(jokeIntent);
        }

    }
}
