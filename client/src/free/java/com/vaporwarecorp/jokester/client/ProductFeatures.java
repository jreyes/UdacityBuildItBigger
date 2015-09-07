package com.vaporwarecorp.jokester.client;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class ProductFeatures implements Features {
// ------------------------------ FIELDS ------------------------------

    private Context mContext;
    private PublisherInterstitialAd mPublisherInterstitialAd;

// --------------------------- CONSTRUCTORS ---------------------------

    public ProductFeatures(Context context) {
        mContext = context;
        initFeatures();
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Features ---------------------

    public void displayInterstitialAd() {
        mPublisherInterstitialAd.show();
    }

    private void initFeatures() {
        mPublisherInterstitialAd = new PublisherInterstitialAd(mContext);
        mPublisherInterstitialAd.setAdUnitId(mContext.getString(R.string.ad_unit_id));
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                onInterstitialAdClosed();
            }
        });
        requestNewInterstitialAd();
    }

    private void onInterstitialAdClosed() {
        requestNewInterstitialAd();
        mContext.sendBroadcast(new Intent(INTERSTITIAL_CLOSED));
    }

    private void requestNewInterstitialAd() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(BuildConfig.TEST_DEVICE_ID)
                .build();
        mPublisherInterstitialAd.loadAd(adRequest);
    }
}
