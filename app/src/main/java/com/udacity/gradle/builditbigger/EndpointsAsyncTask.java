package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.jman.androidshowjokelib.ShowJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


    /*
    * For future reference: we do not need default <Pair<Context, String>, Void, String> types like on the template
    * @ https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend
    * because the value of the pair being displayed in place of "Manfred" is the joke from the java lib.
    * We only need Context (the environment where the Async call is coming from)
    * */
    public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {


        private static MyApi myApiService = null;
    private Context context;

    private IAsyncTaskCallback asyncTaskCallback;

    public EndpointsAsyncTask(IAsyncTaskCallback asyncTaskCallback) {
        this.asyncTaskCallback = asyncTaskCallback;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        // asyncTaskCallback is the MainActivity object,
        // since asyncTaskCallback was initialised with the MainActivity object.
        // This is possible because MainActivity implemented the IAsyncTaskCallback
        // interface, so it is the same type as asyncTaskCallback.
        // this allows the de-coupling of code between the AsyncTask and the UI.
        asyncTaskCallback.onResultReceived(result);

    }


}
