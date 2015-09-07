package com.vaporwarecorp.jokester.client;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class JokesterAsyncTask extends AsyncTask<Void, Void, String> {
// ------------------------------ FIELDS ------------------------------

    private WeakReference<Callback> mCallback;
    private Context mContext;
    private boolean mFailed;

// --------------------------- CONSTRUCTORS ---------------------------

    public JokesterAsyncTask(@NonNull Context context, @NonNull Callback callback) {
        mContext = context;
        mCallback = new WeakReference<>(callback);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return JokesterApplication
                    .getJokeApi(mContext)
                    .getJoke()
                    .execute()
                    .getData();
        } catch (IOException e) {
            mFailed = true;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mFailed) {
            mCallback.get().onError();
        } else {
            mCallback.get().onSuccess(result);
        }
    }

// -------------------------- INNER CLASSES --------------------------

    public interface Callback {
        void onSuccess(String jokeUrl);

        void onError();
    }
}