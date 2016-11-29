package com.example.evans.brrreader;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
private static TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tts = new TextToSpeech(this,this);
        Button speakbutton = (Button) findViewById(R.id.button);
        Button edubutton = (Button)findViewById(R.id.button2);

        speakbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),read.class);
                startActivity(i);
            }
        });
        edubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),edu.class);
                startActivity(i);
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

    public void speak(){

        String str1 = "  메인 페이지에 접속하셨습니다.";
        tts.speak(str1,TextToSpeech.QUEUE_FLUSH,null);

    }
    @Override
    public void onInit(int i) {

       speak();

    }

    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

    public static int Braille(Mat image){
        int total=0;

        int one_x=image.rows()/4, one_y=image.cols()/4;
        int two_x=image.rows()/2, two_y=image.cols()/4;
        int three_x=(int)(image.rows()/1.4), three_y=image.cols()/4;
        int four_x=image.rows()/4, four_y=(int)(image.cols()/1.4);
        int five_x=image.rows()/2, five_y=(int)(image.cols()/1.4);
        int six_x=(int)(image.rows()/1.4), six_y=(int)(image.cols()/1.4);

        if(image.get(one_x,one_y)[0]<100)
            total+=1;
        if(image.get(two_x,two_y)[0]<100)
            total+=2;
        if(image.get(three_x,three_y)[0]<100)
            total+=4;
        if(image.get(four_x,four_y)[0]<100)
            total+=8;
        if(image.get(five_x,five_y)[0]<100)
            total+=16;
        if(image.get(six_x,six_y)[0]<100)
            total+=32;

        return total;
    }

}
