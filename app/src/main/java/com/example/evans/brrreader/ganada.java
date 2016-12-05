package com.example.evans.brrreader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ganada extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Uri fileUri;
    String picturePath ;
    Uri selectedImage;
    Bitmap photo;
    String ba1;
    String str2 = "교육 버튼을 클릭하셨습니다. 3초후 교육을 진행합니다.";
    private  static TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tts = new TextToSpeech(this,this);
        ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View btn1) {
                tts.speak(str2,TextToSpeech.QUEUE_FLUSH,null);

                final long changeTime = 4000L;
                btn1.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    clickpic();
                    }
                }, changeTime);
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

        final Animation blinkanimation = new AlphaAnimation(1, 0);
        blinkanimation.setDuration(500);
        blinkanimation.setInterpolator(new LinearInterpolator());
        blinkanimation.setRepeatCount(Animation.INFINITE);
        blinkanimation.setRepeatMode(Animation.REVERSE);

        TextView welcome = (TextView)findViewById(R.id.textView5);

        welcome.startAnimation(blinkanimation);

    }
    private void clickpic() {
        // Check Camera
        /*
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // Open default camera
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, 100);

        } else {
            Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
        }
        */

        Intent i = new Intent(getApplicationContext(),ganada_1.class);
        startActivity(i);

    }
    public void speak()
    {

        String str = "  자음 모음  익히기  테스트  입니다. 교육을 진행 하시려면 화면의 왼쪽을, 테스트를 바로 진행 하시려면 화면의 오른 쪽을 터치해 주세요. ";
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