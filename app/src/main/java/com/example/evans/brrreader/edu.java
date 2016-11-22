package com.example.evans.brrreader;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class edu extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);
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

        final Animation blinkanimation = new AlphaAnimation(1, 0);
        blinkanimation.setDuration(500);
        blinkanimation.setInterpolator(new LinearInterpolator());
        blinkanimation.setRepeatCount(Animation.INFINITE);
        blinkanimation.setRepeatMode(Animation.REVERSE);

        TextView welcome = (TextView)findViewById(R.id.textView2);

        welcome.startAnimation(blinkanimation);

    }

    public void speak(){

        String str1 = "  교육 페이지입니다.";
        tts.speak(str1, TextToSpeech.QUEUE_FLUSH, null);

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
