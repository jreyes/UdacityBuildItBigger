package com.vaporwarecorp.jokester.client.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class JokesterActivity extends AppCompatActivity {
// ------------------------------ FIELDS ------------------------------

    private static final String KEY_JOKE_URL = "KEY_JOKE_URL";

    private String mJokeUrl;

// -------------------------- STATIC METHODS --------------------------

    public static Intent newInstance(Context context, String jokeUrl) {
        return new Intent(context, JokesterActivity.class).putExtra(KEY_JOKE_URL, jokeUrl);
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokester);
        initJoke(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_JOKE_URL, mJokeUrl);
    }

    private void initJoke(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mJokeUrl = getIntent().getStringExtra(KEY_JOKE_URL);
        } else {
            mJokeUrl = savedInstanceState.getString(KEY_JOKE_URL);
        }

        // load joke
        Glide.with(getApplicationContext())
                .load(mJokeUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(R.drawable.ic_troll_face)
                .into((ImageView) findViewById(R.id.joke));
    }
}
