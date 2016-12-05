package com.example.evans.brrreader;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ganada_1 extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private TextToSpeech tts2;
    private TextToSpeech tts3;
    private TextToSpeech tts4;
    private TextToSpeech tts5;


    String s3 =   "우측 첫 번째 점자는 니은 입니다." ;
    String s4 = "좌측 두번 째 점자는 디귿입니다." ;
    String s5 =  "우측 두번 째 점자는 리을 입니다." ;
    String s6=     "좌측 세 번째 점자는 미음 입니다." ;
    String s7=   "우측 세 번째 점자는 비읍 입니다." ;
    String s8="한 번 더 반복하겠습니다. 좌측 첫 번째 점자는 기억 입니다." ;
    String s9="우측 첫 번째 점자는 니은 입니다." ;
    String s10="좌측 두번 째 점자는 디귿입니다." ;
    String s11="우측 두번 째 점자는 리을 입니다." ;
    String s12= "좌측 세 번째 점자는 미음 입니다." ;
    String s13="우측 세 번째 점자는 비읍 입니다." ;
    String s14 = "교육을 완료 했습니다. 테스트를 진행합니다.";

    public int count = 0;

    Button m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganada_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tts = new TextToSpeech(this,this);
        tts2 = new TextToSpeech(this,this);
        tts3 = new TextToSpeech(this,this);
        tts4 = new TextToSpeech(this,this);
        tts5 = new TextToSpeech(this,this);

        m = (Button)findViewById(R.id.button3);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (count == 0)
                {
                    tts3.speak(s3,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }
                else if(count ==1)
                {
                    tts4.speak(s4,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }
                else if(count == 2)
                {
                    tts5.speak(s5,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else if(count == 3)
                {
                    tts5.speak(s6,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else if(count == 4)
                {
                    tts5.speak(s7,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else if(count == 5)
                {
                    tts5.speak(s8,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else if(count == 6)
                {
                    tts5.speak(s9,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else if(count == 7)
                {
                    tts5.speak(s10,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }
                else if(count == 8)
                {
                    tts5.speak(s11,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }
                else if(count == 9)
                {
                    tts5.speak(s12,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }
                else if(count == 10)
                {
                    tts5.speak(s13,TextToSpeech.QUEUE_ADD,null);
                    count ++;
                }

                else
                {
                    tts5.speak(s14,TextToSpeech.QUEUE_ADD,null);

                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onInit(int status) {
        tts.setSpeechRate(1.0f);
        tts2.setSpeechRate(0.7f);
        tts.setPitch(0.5f);
        tts2.setPitch(0.5f);
        speak();

    }

    public void speak()
    {
        String s1 = "자음 모음 교육 카드를 펼쳐주세요.";
        String s2 = " 좌측 첫 번째 점자는 기억 입니다." ;


        tts.speak(s1,TextToSpeech.QUEUE_FLUSH,null);
        tts2.speak(s2, TextToSpeech.QUEUE_FLUSH, null);

    }

    protected void onDestroy() {

        tts.stop();
        tts2.stop();
        tts3.stop();
        tts4.stop();
        tts5.stop();

        tts.shutdown();
        tts2.shutdown();
        tts3.shutdown();
        tts4.shutdown();
        tts5.shutdown();
        super.onDestroy();
    }


}
