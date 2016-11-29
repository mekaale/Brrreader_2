package com.example.evans.brrreader;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class edu extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static TextToSpeech tts;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private EditText editText;
    private String page;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnspeak);
        String page =txtSpeechInput.getText().toString();



        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
                           }
        });
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

        String str1 = "  교육 페이지에 접속 하셨습니다. 저희 비알 리더에서는 총 여섯 가지의 교육 시나리오를 제공합니다." +
                "잘 들으시고 원하시는 교육 시나리오에 해당 하는 번호를 말씀 해 주세요.                               " +
                "     1번 자음 모음 익히기.                  " +
                "     2번 글자 익히기.                       " +
                "     3번 단어 익히기.                       " +
                "     4번 줄임말 익히기.                     " +
                "     5번 문장 익히기. ";
        tts.speak(str1, TextToSpeech.QUEUE_FLUSH, null);

    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    if(txtSpeechInput.getText().toString().equals("일본"))    //비교를 통해 교육 시나리오로 이동
                    {
                        txtSpeechInput.setText("1번");
                        Intent i = new Intent(getApplicationContext(),ganada.class);
                        startActivity(i);
                    }
                    else if(txtSpeechInput.getText().toString().equals("이번"))
                    {
                        txtSpeechInput.setText("2번");
                        Intent i = new Intent(getApplicationContext(),ganada2.class);
                        startActivity(i);
                    }
                    else if(txtSpeechInput.getText().toString().equals("3번"))
                    {
                        Intent i = new Intent(getApplicationContext(),ganada3.class);
                        startActivity(i);
                    }

                    if(txtSpeechInput.getText().toString().equals("4번"))    //비교를 통해 교육 시나리오로 이동
                    {
                        Intent i = new Intent(getApplicationContext(),ganada4.class);
                        startActivity(i);
                    }

                    if(txtSpeechInput.getText().toString().equals("5번"))    //비교를 통해 교육 시나리오로 이동
                    {
                        Intent i = new Intent(getApplicationContext(),ganada5.class);
                        startActivity(i);
                    }

                }
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
