package com.vaporwarecorp.jokester.client;

import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokesterAsyncTaskTest extends ApplicationTestCase<JokesterApplication> {
// ------------------------------ FIELDS ------------------------------

    private JokesterAsyncTask.Callback mCallback = new JokesterAsyncTask.Callback() {
        @Override
        public void onSuccess(String jokeUrl) {
            mJokeUrl = jokeUrl;
            mCountDownLatch.countDown();
        }

        @Override
        public void onError() {
            fail("Got error retrieving the joke URL");
        }
    };
    private final CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private String mJokeUrl;

// --------------------------- CONSTRUCTORS ---------------------------

    public JokesterAsyncTaskTest() {
        super(JokesterApplication.class);
    }

// -------------------------- OTHER METHODS --------------------------

    public void testJokesterAsyncTask() {
        getApplication().initJokeTestApi();
        try {
            new JokesterAsyncTask(getContext(), mCallback).execute();
            mCountDownLatch.await(15, TimeUnit.SECONDS);
            assertNotNull(mJokeUrl);
            assertTrue(mJokeUrl.startsWith("https://s-media-cache-ak0.pinimg.com/736x"));
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }
}
