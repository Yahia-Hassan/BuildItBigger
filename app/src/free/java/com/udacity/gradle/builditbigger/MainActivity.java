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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.OnAsyncTaskFinished {

    private ProgressBar mProgressBar;
    private Button mTellJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mProgressBar = findViewById(R.id.progressBar);
        mTellJokeButton = findViewById(R.id.tell_joke_button);
        setTitle(getString(R.string.app_name) + " - Free");
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
