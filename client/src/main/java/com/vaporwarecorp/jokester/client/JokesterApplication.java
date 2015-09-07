package com.vaporwarecorp.jokester.client;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.vaporwarecorp.joke.server.jokeApi.JokeApi;

import java.io.IOException;

public class JokesterApplication extends Application {
// ------------------------------ FIELDS ------------------------------

    protected JokeApi mJokeApi;

// -------------------------- STATIC METHODS --------------------------

    public static JokesterApplication getApplication(@NonNull Context context) {
        return (JokesterApplication) context.getApplicationContext();
    }

    public static JokeApi getJokeApi(@NonNull Context context) {
        return getApplication(context).mJokeApi;
    }

// -------------------------- OTHER METHODS --------------------------

    public void initJokeApi() {
        mJokeApi = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setApplicationName("Jokester")
                .setRootUrl(BuildConfig.APP_ENGINE_URL)
                .build();
    }

    public void initJokeTestApi() {
        mJokeApi = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setApplicationName("Jokester")
                .setRootUrl(BuildConfig.DEV_APP_ENGINE_URL)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                })
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initJokeApi();
    }
}
