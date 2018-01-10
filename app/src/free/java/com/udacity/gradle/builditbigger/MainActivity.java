package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.palarz.mike.jokedisplayer.JokeDisplayer;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity
        implements EndpointsAsyncTask.PostExecuteCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    // A reference to our interstitial ad
    InterstitialAd mInterstitialAd;

    CountDownLatch mLatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We first create a new interstitial ad instance and set its unit ID to the ID for testing
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        // We then load the ad, but do not display it yet
        mInterstitialAd.loadAd(new AdRequest.Builder()
                                                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                                .build());
        mLatch = new CountDownLatch(1);
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

        // If the ad has finished loading...
        if (mInterstitialAd.isLoaded()) {
            //...We will show the ad...
            mInterstitialAd.show();
            //...We'll create a listener...
            mInterstitialAd.setAdListener(new AdListener(){

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    mLatch.countDown();
                    //...And load another ad in case the user decides to view more than one joke
                    mInterstitialAd.loadAd(new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build());
                                    }
            });
        }
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void supplyJoke(final String theJoke) {
        try {
            mLatch.await();
        } catch (InterruptedException exception) {
            Log.e(TAG, "The thread that the latch was awaiting on was interrupted.");
        }
        Intent jokeIntent = new Intent(this, JokeDisplayer.class);
        jokeIntent.putExtra(JokeDisplayer.BUNDLE_EXTRA_KEY_JOKE, theJoke);
        startActivity(jokeIntent);
    }
}
