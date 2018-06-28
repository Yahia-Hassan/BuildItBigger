package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokedisplayer.JokeDisplayActivity;
import com.example.joketeller.JokeProvider;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.OnAsyncTaskFinished {

    private ProgressBar mProgressBar;
    private Button mTellJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        mTellJokeButton = findViewById(R.id.tell_joke_button);
        setTitle(getString(R.string.app_name) + " - Paid");
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
        mProgressBar.setVisibility(View.VISIBLE);
        mTellJokeButton.setEnabled(false);
        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void onFinished(String data) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mTellJokeButton.setEnabled(true);
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        if (data == null) {
            intent.putExtra(JokeDisplayActivity.JOKE_DISPLAY_INTENT_EXTRA, "Error! Cannot load your joke!");
        } else {
            intent.putExtra(JokeDisplayActivity.JOKE_DISPLAY_INTENT_EXTRA, data);
        }
        startActivity(intent);
    }



}
