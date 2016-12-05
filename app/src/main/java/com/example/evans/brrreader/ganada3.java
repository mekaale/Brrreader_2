package com.example.evans.brrreader;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ganada3 extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private  static TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganada3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tts = new TextToSpeech(this,this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void speak()
    {

        String str = "  단어  익히기  테스트  입니다.";
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);

    }
    @Override
    public void onInit(int i) {
        speak();
    }

    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }


}
