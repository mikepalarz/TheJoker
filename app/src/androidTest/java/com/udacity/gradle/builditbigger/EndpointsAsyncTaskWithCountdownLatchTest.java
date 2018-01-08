package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by mpala on 1/8/2018.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskWithCountdownLatchTest {

    String mJoke;
    CountDownLatch mLatch;
    EndpointsAsyncTask mTask;

    @Before
    public void initialSetup(){
        mLatch = new CountDownLatch(1);
    }

    @Test
    public void getJoke_CheckNotNullOrEmpty() throws InterruptedException{
        mTask = new EndpointsAsyncTask(new EndpointsAsyncTask.PostExecuteCallback() {
            @Override
            public String supplyJoke(String theJoke) {
                mJoke = theJoke;
                mLatch.countDown();

                return null;
            }
        });
        mTask.execute();
        mLatch.await();

        assertThat(mJoke, IsNull.notNullValue());
    }

    @After
    public void teardown(){
        mLatch.countDown();
    }

}
