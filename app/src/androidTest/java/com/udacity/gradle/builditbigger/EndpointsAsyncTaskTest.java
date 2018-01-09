/*
The following code is the property and sole work of Mike Palarz, a student at Udacity.
 */

package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Primary purpose: This test is responsible for checking the contents of the returned joke from the
 * Chuck Norris Java library in various ways. There's nothing overly fancy being done with this
 * test. It was meant to be kept as simple as possible.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.PostExecuteCallback {

    // An instance of our ASyncTask
    EndpointsAsyncTask mTask;

    // We want to be sure that the ASyncTask is created properly before the tests are run.
    @Before
    public void instantiateTask(){
        mTask = new EndpointsAsyncTask(this);
    }

    // A simple test which checks whether the returned joke is either null or empty
    @Test
    public void getJoke_CheckNotNullOrEmpty(){
        String currentJoke = "";
        try {
            /*
             The get() method of the AsyncTask waits until the task is finished before it returns
              the result of the task. This essentially accomplishes everything that we did in
              the other test where a CountDownLatch is used.
              */
            currentJoke = mTask.execute().get();

        } catch (Exception e){
            e.printStackTrace();
        }

        // Finally, we make our assertions of the returned joke
        assertThat(currentJoke, is(not(isEmptyOrNullString())));
        assertThat(currentJoke, is(not(isEmptyString())));
    }


    /*
    Although this method has been implemented, it isn't doing anything because we have no need
    for it in this particular case.
    */
    @Override
    public void supplyJoke(String theJoke) {

    }
}
