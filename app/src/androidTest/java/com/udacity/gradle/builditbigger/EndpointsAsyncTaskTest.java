package com.udacity.gradle.builditbigger;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements IAsyncTaskCallback {

    EndpointsAsyncTask endpointsAsyncTask;
    CountDownLatch signal;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);

        // 'this' = this EndpointsAsyncTaskTest class as the callback calling object
        endpointsAsyncTask = new EndpointsAsyncTask(this);
    }


    @Test
    public void doInBackgroundTest() throws InterruptedException {

        endpointsAsyncTask.doInBackground(InstrumentationRegistry.getContext());
        signal.await(20, TimeUnit.SECONDS);

    }

    @Override
    public void onResultReceived(String result) {

        signal.countDown();

        // assert the result - "This is a Joke!"
        Assert.assertNotEquals("", result);
        Assert.assertNotNull(result);
       // Assert.assertEquals("This is a joke!", result);

    }
}