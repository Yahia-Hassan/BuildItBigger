package com.example.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_DISPLAY_INTENT_EXTRA = "joke_display_intent_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_DISPLAY_INTENT_EXTRA);
        TextView jokeTextView = findViewById(R.id.joke_display_text_view);
        jokeTextView.setText(joke);
    }
}
