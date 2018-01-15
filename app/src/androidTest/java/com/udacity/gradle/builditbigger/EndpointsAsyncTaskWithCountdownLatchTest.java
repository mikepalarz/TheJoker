/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

/**
 * Primary purpose: The primary purpose of this test is to check the contents of the returned
 * joke in various ways. This test is done slightly differently by making use of a CountDownLatch.
 * It was created in order to see an alternative approach to testing an AsyncTask.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskWithCountdownLatchTest {

    private static final String TAG =
            EndpointsAsyncTaskWithCountdownLatchTest.class.getSimpleName();

    // The joke that we'll be testing
    String mJoke;

    /*
    A CountDownLatch allows us to wait until our AsyncTask has finished before we check the contents
    of the returned joke. If we do not wait until the task if finished, then it's possible that
    the task did not yet complete and the joke will be empty or null.
     */
    CountDownLatch mLatch;

    // An instance of our ASyncTask implementation
    EndpointsAsyncTask mTask;

    /* Some initial setup before the test is ran. This code will be ran prior to the test being ran
    due to the @Before annotation. In this case, we are setting up the CountDownLatch with a
    thread count of 1.
      */
    @Before
    public void initialSetup(){
        mLatch = new CountDownLatch(1);
    }

    // A simple test which checks if the return joke is null or empty
    @Test
    public void getJoke_CheckNotNullOrEmpty() {
        // First we instantiate our task and implement our callback interface. Also, we set the
        // ProgressBar to null since we have no need for it.
        mTask = new EndpointsAsyncTask(new EndpointsAsyncTask.PostExecuteCallback() {
            @Override
            public void supplyJoke(String theJoke) {
                // Within the callback, we retrieve the joke by setting mJoke equal to it and
                // reduce the amount of threads to mLatch by 1 via countdown()
                mJoke = theJoke;
                mLatch.countDown();
            }
        }, null);
        /*
        Then, we launch the task via execute() and make the test wait until the tasks finishes via
        the await() method.
         */
        mTask.execute();
        try {
            mLatch.await();
        } catch (InterruptedException exception){
            Log.e(TAG, "The testing thread that the CountDownLatch was awaiting on had been interrupted.");
        }

        // Finally, we make our assertions on the returned String
        assertThat(mJoke, IsNull.notNullValue());
        assertThat(mJoke, not(isEmptyString()));
        assertThat(mJoke, is(not(isEmptyOrNullString())));
    }

    /*
    This code is ran after the test completes. This is accomplished through the @After
    annotation. In this case, we want to be sure that the CountDownLatch is released, regardless
    if our test passed or not. Therefore, we use countdown() again to ensure this.
    */
    @After
    public void teardown(){
        mLatch.countDown();
    }

}
