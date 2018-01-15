/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * Primary purpose: A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    AdView mAdView;

    public MainActivityFragment() {
    }

    /*
    In the case of the free version, we will be showing ads. We handle the banner ads within the
    fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // We ensure to initialize the Mobile Ads SDK with  the app ID for testing purposes
        MobileAds.initialize(getContext(), getString(R.string.ad_mob_app_id));

        mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Finally, we load the ad
        mAdView.loadAd(adRequest);

        return root;
    }
}
