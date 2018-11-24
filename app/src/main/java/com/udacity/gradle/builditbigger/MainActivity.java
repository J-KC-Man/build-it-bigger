package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jman.androidshowjokelib.ShowJokeActivity;
import com.jman.javajokelib.JokeProvider;

public class MainActivity extends AppCompatActivity {

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
        JokeProvider joker = new JokeProvider();

        String joke = joker.getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_LONG).show();

        launchLibraryActivity(joke);

    }

    public void launchLibraryActivity(String joke) {
        Intent intent = new Intent(this, ShowJokeActivity.class);
        intent.putExtra("javaLibJoke", joke);
        startActivity(intent);
    }

}
