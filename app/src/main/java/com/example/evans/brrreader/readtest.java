package com.example.evans.brrreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.*;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;


public class readtest extends AppCompatActivity implements TextToSpeech.OnInitListener {

    String picturePath;

    private static TextToSpeech tts;
    static{

        System.loadLibrary("opencv_java");

    }
    BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected ( int status) {

            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Mat image = new Mat();
                    Mat image2  = new Mat();
                    Bitmap drawBitmap=null;

                    if(image.empty())
                    {
                        Toast.makeText(getApplication(), "no file", Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                    }
                } break;
                default: {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int total= 0 ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readtest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Mat image = new Mat();
        Mat image2 = new Mat();

        Bitmap drawBitmap=null;

        picturePath  = getIntent().getStringExtra("picturepath");
        Log.e("path", "----------------" + picturePath);

        setSupportActionBar(toolbar);
        /*
        try {
            Bitmap myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/20161202_144103.jpg");
            Bitmap myBitmap32 = myBitmap.copy(Bitmap.Config.ARGB_8888, true);
            Utils.bitmapToMat(myBitmap32,image);

           image2 = Utils.loadResource(this, R.drawable.mic2, Highgui.CV_LOAD_IMAGE_COLOR);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
      //  Bitmap myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/test3.png");

        Bitmap myBitmap = BitmapFactory.decodeFile(picturePath);

        Bitmap myBitmap32 = myBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(myBitmap32,image);

        total = Braille(image);

        System.out.println(total);

        if(total == 8)
        {
            Toast.makeText(getApplicationContext(), "해당 점자는 ㄱ 입니다. ", Toast.LENGTH_SHORT).show();

        }

        else
        {

            Toast.makeText(getApplicationContext(), "bad ", Toast.LENGTH_SHORT).show();

        }

    }



    public static int Braille(Mat image){
        int total=0;

        //Mat image2;
       // image.copyTo(image2);
        int one_x=0;
        int one_y=0;
        int two_x=0;
        int two_y=0;
        int three_x=0;
        int three_y=0;
        int four_x=0;
        int four_y=0;
        int five_x=0;
        int five_y=0;
        int six_x=0;
        int six_y=0;

        int line2 = 0;
        String name;
        int line1 = 0;
        int line3 = 0;
        int line4 = 0;
        name = "gg";


        for (int k=0; k<image.cols()/2 ; k++) // (좌/우 동일간격일때 대칭으로 자르기)
        {
            if (image.get(image.rows() / 2, k)[0] < 50)
            {
                line1 = k;
                break;
            }

        }

        for (int k=image.cols()-1; k>image.cols() / 2; k--) // (좌/우 동일간격일때 대칭으로 자르기)
        {
            if (image.get(image.rows() / 2, k)[0] < 50)
            {
                line2 = k;
                break;
            }

        }


        for (int k = 0; k<image.rows()/2; k++) // (상/하 동일간격일때 대칭으로 자르기)
        {
            if (image.get(k, image.cols() / 2)[0] < 50)
            {
                line3 = k;
                break;
            }

        }

        for (int k = image.rows()-1; k>image.rows() / 2; k--) // (상/하 동일간격일때 대칭으로 자르기)
        {
            if (image.get(k, image.cols() / 2)[0] < 50)
            {
                line4 = k;
                break;
            }

        }

        Log.e("path", "----------------" + name);
        //image2 = image.rowRange(line3,line4).colRange(line1,line2);

       Mat image2 = image.submat(line3, line4, line1, line2);

        /*
        image2.get(one_y,one_x)[0] = 255;
        image2.get(one_y,one_x)[1] = 255;
        image2.get(one_y,one_x)[2] = 255;
        image2.get(two_y,two_x)[0] = 255;
        image2.get(two_y,two_x)[1] = 255;
        image2.get(two_y,two_x)[1] = 255;
        image2.get(three_y,three_x)[0] = 255;
        image2.get(three_y,three_x)[1] = 255;
        image2.get(three_y,three_x)[2] = 255;
        image2.get(four_y,four_x)[0] = 255;
        image2.get(four_y,four_x)[1] = 255;
        image2.get(four_y,four_x)[2] = 255;
        image2.get(five_y,five_x)[0] = 255;
        image2.get(five_y,five_x)[1] = 255;
        image2.get(five_y,five_x)[2] = 255;
        image2.get(six_y,six_x)[0] = 255;
        image2.get(six_y,six_x)[1] = 255;

        image2.get(six_y,six_x)[2] = 255;
*/


        one_x=image2.rows()/4;
        one_y=image2.cols()/4;
        two_x=image2.rows()/2;
        two_y=image2.cols()/4;
        three_x=(int)(image2.rows()/ 1.4);
        three_y=image2.cols()/4;
        four_x=image2.rows()/4;
        four_y=(int)(image2.cols()/1.4);
        five_x=image2.rows()/2;
        five_y=(int)(image2.cols()/1.4);
        six_x=(int)(image2.rows()/1.4);
        six_y=(int)(image2.cols()/1.4);


        if(image2.get(one_y,one_x)[0]<100)
            total+=1;
        if(image2.get(two_y,two_x)[0]<100)
            total+=2;
        if(image2.get(three_y,three_x)[0]<100)
            total+=4;
        if(image2.get(four_y,four_x)[0]<100)
            total+=8;
        if(image2.get(five_y,five_x)[0]<100)
            total+=16;
        if(image2.get(six_y,six_x)[0]<100)
            total+=32;

        return total;
    }


    @Override
    public void onInit(int status) {

    }
}