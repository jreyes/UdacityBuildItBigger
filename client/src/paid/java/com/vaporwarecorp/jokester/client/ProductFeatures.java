package com.vaporwarecorp.jokester.client;

import android.content.Context;
import android.content.Intent;

public class ProductFeatures implements Features {
// ------------------------------ FIELDS ------------------------------

    private Context mContext;

// --------------------------- CONSTRUCTORS ---------------------------

    public ProductFeatures(Context context) {
        mContext = context;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Features ---------------------

    public void displayInterstitialAd() {
        mContext.sendBroadcast(new Intent(INTERSTITIAL_CLOSED));
    }
}
