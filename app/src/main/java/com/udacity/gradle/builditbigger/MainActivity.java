package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.palarz.mike.chucknorris.ChuckNorris;


public class MainActivity extends AppCompatActivity {

    ChuckNorris mChuckNorris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We instantiate an instance of our ChuckNorris class
        mChuckNorris = new ChuckNorris();
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

    public void tellJoke(View view) {
        // We then use ChuckNorris to pull a random, nerdy joke
        String currentJoke = mChuckNorris.getRandomNerdyJoke();
        Toast.makeText(this, currentJoke, Toast.LENGTH_LONG).show();
    }


}
