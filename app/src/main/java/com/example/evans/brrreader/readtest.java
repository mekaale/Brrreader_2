package com.example.evans.brrreader;

import android.content.Intent;
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
    String testpath;
    public int []Korean = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};;
        int [] value;



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

        tts = new TextToSpeech(this,this);


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
     //Bitmap myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/1480935421141.png");
     //   Bitmap myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/test100.png");

        Bitmap myBitmap = BitmapFactory.decodeFile(picturePath);
        Bitmap myBitmap32 = myBitmap.copy(Bitmap.Config.ARGB_8888, true);


        Utils.bitmapToMat(myBitmap32,image);

        total = Braille(image);

        tts = new TextToSpeech(this,this);



/*
        if(total == 8)
        {
            Toast.makeText(getApplicationContext(), "해당 점자는 ㄱ 입니다. ", Toast.LENGTH_SHORT).show();

        }
*/
        /*
        else if(total == 9)
        {
            Toast.makeText(getApplicationContext(), "해당 점자는 ㄴ 입니다. ", Toast.LENGTH_SHORT).show();


        }
        else
        {

            Toast.makeText(getApplicationContext(), "bad ", Toast.LENGTH_SHORT).show();

        }
*/
    }



    public int Braille(Mat image){
        int total=0;
        int total1 = 0;
        int total2 = 0;
        int total3 = 0;
        int total4 = 0;
        int total5 = 0;

        int [] Ftotal;

        Ftotal = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        Mat image2 = new Mat();
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

        int THRESHOLD=150;

        int i;

        int line2 = 0;
        String name;
        int line1 = 0;
        int line3 = 0;
        int line4 = 0;
        name = "gg";


        int height, width;
        int j;

        height = image.rows(); // height 사진의 높이
        width = image.cols();  // width 사진의 가로길이

        System.out.println(String.format("Origin size : %d %d",image.rows(),image.cols()));


        for (i = 0; i<height; i++)
            for (j = 0; j<width; j++)
            {
                if (image.get(i, j)[0] > 128 && image.get(i, j)[1] > 128 && image.get(i, j)[2] > 128)
                {// 픽셀 값이 임계값 128보다 크면 흰색

                    image.get(i, j)[0] = image.get(i, j)[1] = image.get(i, j)[2] = 255;
                }
                else { // 128보다 작으면 검은색

                    image.get(i, j)[0] = image.get(i, j)[1] = image.get(i, j)[2] = 0;
                    }
            }


        for (int k=0; k<image.cols()/2 ; k++) // (좌/우 동일간격일때 대칭으로 자르기)
        {
            if (image.get(image.rows() / 2, k)[0] < 125)
            {
                line1 = k;
                break;
            }

        }

        for (int k=image.cols()-1; k>image.cols() / 2; k--) // (좌/우 동일간격일때 대칭으로 자르기)
        {
            if (image.get(image.rows() / 2, k)[0] < 125)
            {
                line2 = k;
                break;
            }

        }


        for (int k = 0; k<image.rows()/2; k++) // (상/하 동일간격일때 대칭으로 자르기)
        {
            if (image.get(k, image.cols() / 2)[0] < 125)
            {
                line3 = k;
                break;
            }

        }

        for (int k = image.rows()-1; k>image.rows() / 2; k--) // (상/하 동일간격일때 대칭으로 자르기)
        {
            if (image.get(k, image.cols() / 2)[0] < 125)
            {
                line4 = k;
                break;
            }

        }

        Log.e("path", "----------------" + name);
    //    image2 = image.rowRange(line3,line4).colRange(line1,line2);

        System.out.println(String.format("Cut in : %d %d - %d %d", line3, line4, line1, line2));

       image2 = image.submat(line3, line4, line1, line2);
/*

        Mat img1 = image2.submat(0,(int)((double)image2.rows()* 0.15),(int)((double)image2.cols() * 0.05),(int)((double)image2.cols() * 0.20));
        Mat img2 = image2.submat(0,(int)((double)image2.rows()*0.14),(int)((double)image2.cols()*0.1920),(int)((double)image2.cols()*0.340));
        Mat img3 = image2.submat(0, (int) ((double) image2.rows() * 0.14), (int) ((double) image2.cols()*0.35), (int) ((double) image2.cols()*0.5));
        Mat img4 = image2.submat(0, (int) ((double) image2.rows() * 0.14), (int) ((double) image2.cols()*0.52), (int) ((double) image2.cols()*0.66));
        Mat img5 = image2.submat(0, (int) ((double) image2.rows() * 0.14), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.82));
        Mat img6 = image2.submat(0, (int) ((double) image2.rows() * 0.14), (int) ((double) image2.cols()*0.83), (int) ((double) image2.cols()*0.97));

        Mat img21 = image2.submat((int)((double)image2.rows()*0.14),(int)((double)image2.rows()*0.297),0,(int)((double)image2.cols() * 0.18));
        Mat img22 = image2.submat((int)((double)image2.rows()*0.14),(int)((double)image2.rows()*0.305),(int)((double)image2.cols()*0.1835),(int)((double)image2.cols()*0.340));
        Mat img23 = image2.submat((int)((double)image2.rows()*0.14), (int) ((double) image2.rows() * 0.305), (int) ((double) image2.cols()*0.3435), (int) ((double) image2.cols()*0.5));
        Mat img24 = image2.submat((int)((double)image2.rows()*0.14), (int) ((double) image2.rows() * 0.305), (int) ((double) image2.cols()*0.5035), (int) ((double) image2.cols()*0.66));
        Mat img25 = image2.submat((int)((double)image2.rows()*0.14), (int) ((double) image2.rows() * 0.305), (int) ((double) image2.cols()*0.6635), (int) ((double) image2.cols()*0.82));
        Mat img26 = image2.submat((int)((double)image2.rows()*0.14), (int) ((double) image2.rows() * 0.305), (int) ((double) image2.cols()*0.8335), (int) ((double) image2.cols()));

        Mat img31 = image2.submat((int)((double)image2.rows()*0.36),(int)((double)image2.rows()*0.465),0,(int)((double)image2.cols() * 0.18));
        Mat img32 = image2.submat((int)((double)image2.rows()*0.36),(int)((double)image2.rows()*0.465),(int)((double)image2.cols()*0.1835),(int)((double)image2.cols()*0.340));
        Mat img33 = image2.submat((int)((double)image2.rows()*0.36), (int) ((double) image2.rows() * 0.465), (int) ((double) image2.cols()*0.3435), (int) ((double) image2.cols()*0.5));
        Mat img34 = image2.submat((int)((double)image2.rows()*0.36), (int) ((double) image2.rows() * 0.465), (int) ((double) image2.cols()*0.5035), (int) ((double) image2.cols()*0.66));
        Mat img35 = image2.submat((int)((double)image2.rows()*0.36), (int) ((double) image2.rows() * 0.465), (int) ((double) image2.cols()*0.6635), (int) ((double) image2.cols()*0.82));
        Mat img36 = image2.submat((int)((double)image2.rows()*0.36), (int) ((double) image2.rows() * 0.465), (int) ((double) image2.cols()*0.8335), (int) ((double) image2.cols()));


        Mat img41 = image2.submat((int)((double)image2.rows()*0.520),(int)((double)image2.rows()*0.625),0,(int)((double)image2.cols() * 0.18));
        Mat img42 = image2.submat((int)((double)image2.rows()*0.520),(int)((double)image2.rows()*0.625),(int)((double)image2.cols()*0.1835),(int)((double)image2.cols()*0.340));
        Mat img43 = image2.submat((int)((double)image2.rows()*0.520), (int) ((double) image2.rows() * 0.625), (int) ((double) image2.cols()*0.3435), (int) ((double) image2.cols()*0.5));
        Mat img44 = image2.submat((int)((double)image2.rows()*0.520), (int) ((double) image2.rows() * 0.625), (int) ((double) image2.cols()*0.5035), (int) ((double) image2.cols()*0.66));
        Mat img45 = image2.submat((int)((double)image2.rows()*0.520), (int) ((double) image2.rows() * 0.625), (int) ((double) image2.cols()*0.6635), (int) ((double) image2.cols()*0.82));
        Mat img46 = image2.submat((int)((double)image2.rows()*0.520), (int) ((double) image2.rows() * 0.625), (int) ((double) image2.cols()*0.8335), (int) ((double) image2.cols()));

        Mat img51 = image2.submat((int)((double)image2.rows()*0.68),(int)((double)image2.rows()*0.83),0,(int)((double)image2.cols() * 0.18));
        Mat img52 = image2.submat((int)((double)image2.rows()*0.680),(int)((double)image2.rows()*0.83),(int)((double)image2.cols()*0.1835),(int)((double)image2.cols()*0.340));
        Mat img53 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.83), (int) ((double) image2.cols()*0.3435), (int) ((double) image2.cols()*0.5));
        Mat img54 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.83), (int) ((double) image2.cols()*0.5035), (int) ((double) image2.cols()*0.66));
        Mat img55 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.83), (int) ((double) image2.cols()*0.6635), (int) ((double) image2.cols()*0.82));
        Mat img56 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.83), (int) ((double) image2.cols()*0.8335), (int) ((double) image2.cols()));



*/

        Mat img1 = image2.submat(0,(int)((double)image2.rows()* 0.17),0,(int)((double)image2.cols() * 0.17));
        Mat img2 = image2.submat(0,(int)((double)image2.rows()*0.17),(int)((double)image2.cols()*0.17),(int)((double)image2.cols()*0.340));
        Mat img3 = image2.submat(0, (int) ((double) image2.rows() * 0.17), (int) ((double) image2.cols()*0.34), (int) ((double) image2.cols()*0.51));
        Mat img4 = image2.submat(0, (int) ((double) image2.rows() * 0.17), (int) ((double) image2.cols()*0.51), (int) ((double) image2.cols()*0.68));
        Mat img5 = image2.submat(0, (int) ((double) image2.rows() * 0.17), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.85));
        Mat img6 = image2.submat(0, (int) ((double) image2.rows() * 0.17), (int) ((double) image2.cols()*0.85), image2.cols());

        Mat img21 = image2.submat((int)((double)image2.rows()*0.17),(int)((double)image2.rows()*0.34),0,(int)((double)image2.cols() * 0.17));
        Mat img22 = image2.submat((int)((double)image2.rows()*0.17),(int)((double)image2.rows()*0.34),(int)((double)image2.cols()*0.17),(int)((double)image2.cols()*0.340));
        Mat img23 = image2.submat((int)((double)image2.rows()*0.17), (int) ((double) image2.rows() * 0.34), (int) ((double) image2.cols()*0.34), (int) ((double) image2.cols()*0.51));
        Mat img24 = image2.submat((int)((double)image2.rows()*0.17), (int) ((double) image2.rows() * 0.34), (int) ((double) image2.cols()*0.51), (int) ((double) image2.cols()*0.68));
        Mat img25 = image2.submat((int)((double)image2.rows()*0.17), (int) ((double) image2.rows() * 0.34), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.85));
        Mat img26 = image2.submat((int)((double)image2.rows()*0.17), (int) ((double) image2.rows() * 0.34), (int) ((double) image2.cols()*0.85), (int) ((double) image2.cols()));

        Mat img31 = image2.submat((int)((double)image2.rows()*0.34),(int)((double)image2.rows()*0.51),0,(int)((double)image2.cols() * 0.17));
        Mat img32 = image2.submat((int)((double)image2.rows()*0.34),(int)((double)image2.rows()*0.51),(int)((double)image2.cols()*0.17),(int)((double)image2.cols()*0.340));
        Mat img33 = image2.submat((int)((double)image2.rows()*0.34), (int) ((double) image2.rows() * 0.51), (int) ((double) image2.cols()*0.34), (int) ((double) image2.cols()*0.51));
        Mat img34 = image2.submat((int)((double)image2.rows()*0.34), (int) ((double) image2.rows() * 0.51), (int) ((double) image2.cols()*0.51), (int) ((double) image2.cols()*0.68));
        Mat img35 = image2.submat((int)((double)image2.rows()*0.34), (int) ((double) image2.rows() * 0.51), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.85));
        Mat img36 = image2.submat((int)((double)image2.rows()*0.34), (int) ((double) image2.rows() * 0.51), (int) ((double) image2.cols()*0.85), (int) ((double) image2.cols()));


        Mat img41 = image2.submat((int)((double)image2.rows()*0.51),(int)((double)image2.rows()*0.68),0,(int)((double)image2.cols() * 0.17));
        Mat img42 = image2.submat((int)((double)image2.rows()*0.51),(int)((double)image2.rows()*0.68),(int)((double)image2.cols()*0.17),(int)((double)image2.cols()*0.340));
        Mat img43 = image2.submat((int)((double)image2.rows()*0.51), (int) ((double) image2.rows() * 0.68), (int) ((double) image2.cols()*0.34), (int) ((double) image2.cols()*0.51));
        Mat img44 = image2.submat((int)((double)image2.rows()*0.51), (int) ((double) image2.rows() * 0.68), (int) ((double) image2.cols()*0.51), (int) ((double) image2.cols()*0.68));
        Mat img45 = image2.submat((int)((double)image2.rows()*0.51), (int) ((double) image2.rows() * 0.68), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.85));
        Mat img46 = image2.submat((int)((double)image2.rows()*0.51), (int) ((double) image2.rows() * 0.68), (int) ((double) image2.cols()*0.85), (int) ((double) image2.cols()));

        Mat img51 = image2.submat((int)((double)image2.rows()*0.68),(int)((double)image2.rows()*0.85),0,(int)((double)image2.cols() * 0.17));
        Mat img52 = image2.submat((int)((double)image2.rows()*0.680),(int)((double)image2.rows()*0.85),(int)((double)image2.cols()*0.17),(int)((double)image2.cols()*0.340));
        Mat img53 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.85), (int) ((double) image2.cols()*0.34), (int) ((double) image2.cols()*0.51));
        Mat img54 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.85), (int) ((double) image2.cols()*0.51), (int) ((double) image2.cols()*0.68));
        Mat img55 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.85), (int) ((double) image2.cols()*0.68), (int) ((double) image2.cols()*0.85));
        Mat img56 = image2.submat((int)((double)image2.rows()*0.680), (int) ((double) image2.rows() * 0.85), (int) ((double) image2.cols()*0.85), (int) ((double) image2.cols()));




/*
        boolean bool =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img1.bmp", img1);
        boolean bool2 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img2.bmp", img2);
        boolean bool3 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img3.bmp", img3);
        boolean bool4 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img4.bmp", img4);
        boolean bool5 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img5.bmp", img5);
        boolean bool6 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img6.bmp", img6);
*/
        boolean bool =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img1.bmp", img1);
        boolean bool2 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img2.bmp", img2);
        boolean bool3 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img3.bmp", img3);
        boolean bool4 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img4.bmp", img4);
        boolean bool5 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img5.bmp", img5);
        boolean bool6 =  Highgui.imwrite("/storage/emulated/0/DCIM/Camera/img6.bmp", img6);


        System.out.println(String.format("Area : %d %d",image2.rows(),image2.cols()));


        one_y =img1.rows()/4;
        one_x =img1.cols()/4;
        two_y =img1.rows()/2;
        two_x=img1.cols()/4;
        three_y=(int)(img1.rows()/ 1.4);
        three_x =img1.cols()/4;
        four_y=img1.rows()/4;
        four_x=(int)(img1.cols()/1.4);
        five_y=img1.rows()/2;
        five_x=(int) (img1.cols()/1.4);
        six_y=(int) (img1.rows()/1.4);
        six_x=(int)(img1.cols()/1.4);

/*
        one_y = (int)(img1.rows()/3.95);
        one_x =(int)(img1.cols()/3.95);
        two_y =img1.rows()/2;
        two_x=(int)(img1.cols()/3.95);
        three_y=(int)(img1.rows()/ 1.45);
        three_x =(int)(img1.cols()/3.95);
        four_y=(int)(img1.rows()/3.95);
        four_x=(int)(img1.cols()/1.45);
        five_y=img1.rows()/2;
        five_x=(int) (img1.cols()/1.45);
        six_y=(int) (img1.rows()/1.45);
        six_x=(int)(img1.cols()/1.45);
*/
        System.out.println(String.format("one(%d,%d) : %f", one_y, one_x,image2.get(one_y,one_x)[0]));
        System.out.println(String.format("two(%d,%d) : %f", two_y, two_x,image2.get(two_y,two_x)[0]));
        System.out.println(String.format("three(%d,%d) : %f", three_y, three_x,image2.get(three_y,three_x)[0]));
        System.out.println(String.format("four(%d,%d) : %f", four_y, four_x,image2.get(four_y,four_x)[0]));
        System.out.println(String.format("five(%d,%d) : %f", five_y, five_x,image2.get(five_y,five_x)[0]));
        System.out.println(String.format("six(%d,%d) : %f", six_y, six_x,image2.get(six_y,six_x)[0]));


        Ftotal[0] = check_total(img1);
        Ftotal[1] = check_total(img2);
        Ftotal[2] = check_total(img3);
        Ftotal[3] = check_total(img4);
        Ftotal[4] = check_total(img5);
        Ftotal[5] = check_total(img6);

        Ftotal[6] = check_total(img21);
        Ftotal[7] = check_total(img22);
        Ftotal[8] = check_total(img23);
        Ftotal[9] = check_total(img24);
        Ftotal[10] = check_total(img25);
        Ftotal[11] = check_total(img26);

        Ftotal[12] = check_total(img31);
        Ftotal[13] = check_total(img32);
        Ftotal[14] = check_total(img33);
        Ftotal[15] = check_total(img34);
        Ftotal[16] = check_total(img35);
        Ftotal[17] = check_total(img36);


        Ftotal[18] = check_total(img41);
        Ftotal[19] = check_total(img42);
        Ftotal[20] = check_total(img43);
        Ftotal[21] = check_total(img44);
        Ftotal[22] = check_total(img45);
        Ftotal[23] = check_total(img46);


        Ftotal[24] = check_total(img51);
        Ftotal[25] = check_total(img52);
        Ftotal[26] = check_total(img53);
        Ftotal[27] = check_total(img54);
        Ftotal[28] = check_total(img55);
        Ftotal[29] = check_total(img56);

/*
            for(int u = 0; u <29; u++) {
                Ftotal[u] = Korean[u];
                System.out.println(String.format("%d",Ftotal[u]) );

                  }
*/




            System.out.println(String.format(" total 1 = %d입니다.", Ftotal[0]));

            System.out.println(String.format(" total 2 = %d입니다.", Ftotal[1]));
            System.out.println(String.format(" total 3 = %d입니다.", Ftotal[2]));
        System.out.println(String.format(" total 4 = %d입니다.", Ftotal[3]));
        System.out.println(String.format(" total 5 = %d입니다.", Ftotal[4]));
        System.out.println(String.format(" total 6 = %d입니다.", Ftotal[5]));




            Toast.makeText(getApplicationContext(), "bad ", Toast.LENGTH_SHORT).show();



        Korean[0] =Ftotal[0];
        Korean[1] =Ftotal[1];
        Korean[2] =Ftotal[2];
        Korean[3] =Ftotal[3];
        Korean[4] = Ftotal[4];
        Korean[5] =Ftotal[5];
        Korean[6] = Ftotal[6];
        Korean[7] =Ftotal[7];
        Korean[8] = Ftotal[8];
        Korean[9] =Ftotal[9];
        Korean[10] = Ftotal[10];
        Korean[11] =Ftotal[11];
        Korean[12] =Ftotal[12];
        Korean[13] =Ftotal[13];
        Korean[14] =Ftotal[14];
        Korean[15] =Ftotal[15];
        Korean[16] = Ftotal[16];
        Korean[17] =Ftotal[17];
        Korean[18] = Ftotal[18];
        Korean[19] =Ftotal[19];
        Korean[20] = Ftotal[20];
        Korean[21] =Ftotal[21];
        Korean[22] = Ftotal[22];
        Korean[23] =Ftotal[23];
        Korean[24] = Ftotal[24];
        Korean[25] =Ftotal[25];
        Korean[26] = Ftotal[26];
        Korean[27] =Ftotal[27];
        Korean[28] = Ftotal[28];
        Korean[29] =Ftotal[29];
             //BrailieKorean.CombineBrailie(Korean);
            System.out.println(BrailieKorean.CombineBrailie(Korean));

        return total;
    }

    /*
        public void speak()
        {
            String str1 = "점자 인식이 완료 되었습니다.";

            tts.speak(str1,TextToSpeech.QUEUE_FLUSH,null);

        }
    */
    @Override
    public void onInit(int status) {

        String str1 = BrailieKorean.CombineBrailie(Korean);


        System.out.println(BrailieKorean.CombineBrailie(Korean));

        tts.speak(str1,TextToSpeech.QUEUE_FLUSH,null);
        tts.setSpeechRate(1.0f);
//        tts2.setSpeechRate(0.7f);


    }





    private static int check_total(Mat img1){
        int total=0;
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

        one_y =img1.rows()/4;
        one_x =img1.cols()/4;
        two_y =img1.rows()/2;
        two_x=img1.cols()/4;
        three_y=(int)(img1.rows()/ 1.4);
        three_x =img1.cols()/4;
        four_y=img1.rows()/4;
        four_x=(int)(img1.cols()/1.4);
        five_y=img1.rows()/2;
        five_x=(int) (img1.cols()/1.4);
        six_y=(int) (img1.rows()/1.4);
        six_x=(int)(img1.cols()/1.4);


        int THRESHOLD=150;

        if(img1.get(one_y,one_x)[0]<THRESHOLD)
            total+=1;
        if(img1.get(two_y,two_x)[0]<THRESHOLD)
            total+=2;
        if(img1.get(three_y,three_x)[0]<THRESHOLD)
            total+=4;
        if(img1.get(four_y,four_x)[0]<THRESHOLD)
            total+=8;
        if(img1.get(five_y,five_x)[0]<THRESHOLD)
            total+=16;
        if(img1.get(six_y,six_x)[0]<THRESHOLD)
            total+=32;



        return total;
    }



}