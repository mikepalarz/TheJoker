package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
 * Created by mpala on 1/6/2018.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.PostExecuteCallback {

    EndpointsAsyncTask mTask;

    @Before
    public void instantiateTask(){
        mTask = new EndpointsAsyncTask(this);
    }

    @Test
    public void getJoke_CheckNotNullOrEmpty(){
        String currentJoke = "";
        try {
            currentJoke = mTask.execute().get();

        } catch (Exception e){
            e.printStackTrace();
        }

        assertThat(currentJoke, is(not(isEmptyOrNullString())));
        assertThat(currentJoke, is(not(isEmptyString())));
    }


    @Override
    public String supplyJoke(String theJoke) {
        return theJoke;
    }
}
