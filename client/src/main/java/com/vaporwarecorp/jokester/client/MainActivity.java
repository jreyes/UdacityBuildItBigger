package com.vaporwarecorp.jokester.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vaporwarecorp.jokester.client.core.JokesterActivity;
import com.vlonjatg.progressactivity.ProgressActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// ------------------------------ FIELDS ------------------------------

    JokesterAsyncTask.Callback mCallback = new JokesterAsyncTask.Callback() {
        @Override
        public void onSuccess(String jokeUrl) {
            displayJoke(jokeUrl);
        }

        @Override
        public void onError() {
            displayError();
        }
    };
    View.OnClickListener mErrorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mProgress.showContent();
            setTitle(R.string.app_name);
        }
    };

    private Features mFeatures;
    private JokeReceiver mJokeReceiver;
    private JokesterAsyncTask mJokesterAsyncTask;
    private ProgressActivity mProgress;

// -------------------------- OTHER METHODS --------------------------

    public void cancelJokeAsyncTask() {
        if (mJokesterAsyncTask != null) {
            mJokesterAsyncTask.cancel(true);
            mJokesterAsyncTask = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFeatures();
        initReceiver();
        initProgress();
        initStartButton();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mJokeReceiver);
        cancelJokeAsyncTask();
        super.onDestroy();
    }

    private void displayAd() {
        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mFeatures.displayInterstitialAd();
        } else {
            displayError();
        }
    }

    private void displayError() {
        mProgress.showError(
                getResources().getDrawable(R.drawable.ic_perm_scan_wifi_white_48dp),
                getString(R.string.error_title),
                getString(R.string.error_description),
                getString(R.string.error_action),
                mErrorClickListener,
                new ArrayList<Integer>());
        setTitle(getString(R.string.error));
    }

    private void displayJoke(String jokeUrl) {
        mProgress.showContent();
        startActivity(JokesterActivity.newInstance(this, jokeUrl));
    }

    private void initFeatures() {
        mFeatures = new ProductFeatures(this);
    }

    private void initProgress() {
        mProgress = (ProgressActivity) findViewById(R.id.progress);
    }

    private void initReceiver() {
        mJokeReceiver = new JokeReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Features.INTERSTITIAL_CLOSED);

        registerReceiver(mJokeReceiver, intentFilter);
    }

    private void initStartButton() {
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAd();
            }
        });
    }

// -------------------------- INNER CLASSES --------------------------

    /**
     * Joke intent receiver that will load a joke from GCE
     */
    class JokeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Features.INTERSTITIAL_CLOSED)) {
                mProgress.showLoading();

                cancelJokeAsyncTask();

                mJokesterAsyncTask = new JokesterAsyncTask(MainActivity.this, mCallback);
                mJokesterAsyncTask.execute();
            }
        }
    }
}
