package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jman.androidshowjokelib.ShowJokeActivity;
//import com.jman.javajokelib.JokeProvider;

public class MainActivity extends AppCompatActivity implements IAsyncTaskCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Step 1:
    // Make the button display a toast showing
    // a joke retrieved from your Java joke telling library.
    public void tellJoke(View view) {

        // init asynctask object and make the MainActivity the callback calling object
        //new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));
        // for future reference: we do not need default new Pair<Context, String>(this, "Manfred")

        /*
        * init asynctask object and make the MainActivity the callback calling object
        * for future reference: We don't need the pair in the default call new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));
        * because the value being displayed in place of "Manfred" is the joke from the java lib.
        * We only need Context (the environment where the Async call is coming from)
        * */
        new EndpointsAsyncTask(this).execute(this);
    }


    @Override
    public void onResultReceived(String result) {
        Intent intent = new Intent(this, ShowJokeActivity.class);
        intent.putExtra("GCEJoke", result);
        startActivity(intent);
    }
}
