package com.example.rgb;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Color;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    SeekBar skbar1;
     SeekBar skbar2;
     SeekBar skbar3;
     TextView tv1,tv2,tv3,tv4,tv5;
     ImageView imgView;
     Bitmap bitmap;
     Button bt1,bt2,bt3,bt4;
     public static final int REQUEST_CODE_3 =3;
    public static final int REQUEST_CODE_2 =2;
     String Stt="0";




    static final int STATE_MESSAGE_RECEIVED=5;

    int val1=0; // get maximum value of the Seek bar
    int val2=0; // get maximum value of the Seek bar
    int val3=0; // get maximum value of the Seek bar

    public MainActivity() { }//constructor


    String hexcode(int R){
        String SR="";
        if(R==0){
            SR="00";
            return(SR);
        }
        if(R<16&&R>9){
            if ( R  == 10) {
                SR = "0A" + SR;
            }
            if (R  == 11) {
                SR = "0B" + SR;
            }
            if (R  == 12) {
                SR = "0C" + SR;
            }
            if (R  == 13) {
                SR = "0D" + SR;
            }
            if (R  == 14) {
                SR = "0E" + SR;
            }
            if (R  == 15) {
                SR = "0F" + SR;
            }
            return(SR);
        }
        if(R<10){
            SR="0"+String.valueOf((int)R);
            return(SR);
        }
        else {
            while (R != 0) {
                if ((int) (R % 16) < 10) {
                    SR = String.valueOf((int) (R % 16)) + SR;
                    R = (int) (R / 16);
                }

                else {
                    if ((int) (R % 16) == 10) {
                        SR = "A" + SR;
                    }
                    if ((int) (R % 16) == 11) {
                        SR = "B" + SR;
                    }
                    if ((int) (R % 16) == 12) {
                        SR = "C" + SR;
                    }
                    if ((int) (R % 16) == 13) {
                        SR = "D" + SR;
                    }
                    if ((int) (R % 16) == 14) {
                        SR = "E" + SR;
                    }
                    if ((int) (R % 16) == 15) {
                        SR = "F" + SR;
                    }
                    R = (int) (R / 16);
                }
            }
        }

        //System.out.println(SR);
        return(SR);
    }
    void openAct2(){
        Intent intent=new Intent(getApplicationContext(), Activity2.class);
        intent.putExtra("Trangthai", Stt);
        startActivity(intent);
    }
    void openAct3(){
        Intent intent=new Intent(getApplicationContext(), Activity3.class);
        startActivityForResult(intent,REQUEST_CODE_3);
    }

    public static ClientClass clientClass;







    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView7);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        bt3 = (Button)findViewById(R.id.button3);
        bt4 = (Button)findViewById(R.id.button4);
        imgView = (ImageView) findViewById(R.id.imgv);
        imgView.setDrawingCacheEnabled(true);
        imgView.buildDrawingCache(true);


        skbar1 = (SeekBar) findViewById(R.id.seekBar); // initiate the Seek bar
        skbar1.setProgress(val1);
        skbar2 = (SeekBar) findViewById(R.id.seekBar2); // initiate the Seek bar
        skbar2.setProgress(val2);
        skbar3 = (SeekBar) findViewById(R.id.seekBar3); // initiate the Seek bar
        skbar3.setProgress(val3);

        // Phone does not support Bluetooth so let the user know and exit.




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAct2();
            }
        });

        // Phone does not support Bluetooth so let the user know and exit.
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openAct3();
            }
        });



       


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_3){
            if(resultCode==RESULT_OK){
                    Stt=data.getStringExtra("StatusBt");

            }
        }
        if(requestCode==REQUEST_CODE_2){
            if(resultCode==RESULT_OK){


            }
        }

    }
    public void onResume() {
        super.onResume();
        if(Stt!="0"){
            tv5.setText("Bluetooth connected !");
        }else{
            tv5.setText("Bluetooth is not connected !");
        }

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Stt!="0") {
                    Activity3.clientClass.send("TEMP)");
                }else{
                    tv5.setText("Bluetooth was not connected \n Click on bluetooth button and connect to device");
                }

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Stt!="0") {
                    Activity3.clientClass.send("TIME)");
                }else{
                    tv5.setText("Bluetooth was not connected \n Click on bluetooth button and connect to device");
                }
            }
        });
        imgView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {




                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                        bitmap = imgView.getDrawingCache();
                        int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());
                        val1 = Color.red(pixel);
                        val2 = Color.blue(pixel);
                        val3 = Color.green(pixel);
                        tv4.setBackgroundColor(Color.parseColor("#" + hexcode(val1) + hexcode(val3) + hexcode(val2)));
                        tv4.setText("#" + hexcode(val1) + hexcode(val3) + hexcode(val2));

                        skbar1.setProgress(val1);
                        skbar2.setProgress(val2);
                        skbar3.setProgress(val3);

                        tv1.setText(String.valueOf(val1));
                        tv2.setText(String.valueOf(val2));
                        tv3.setText(String.valueOf(val3));


                    }
                    if(Stt!="0") {
                        Activity3.clientClass.send(String.valueOf(val1)+"."+String.valueOf(val3)+"."+String.valueOf(val2)+")");


                    }else {
                        tv5.setText("Use color pick without data output \n Click bluetooth button to connect device");
                    }


                return true;
            }
        });
        skbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                val1 = progress;
                tv1.setText(String.valueOf(progressChangedValue));
                tv4.setBackgroundColor(Color.parseColor("#" + hexcode(val1) + hexcode(val3) + hexcode(val2)));
                tv4.setText("#" + hexcode(val1) + hexcode(val3) + hexcode(val2));
                if(Stt=="0"){
                    tv5.setText("Use seekbar without data output \n Click bluetooth button to connect device");
                }else{
                    Activity3.clientClass.send(String.valueOf(val1)+"."+String.valueOf(val3)+"."+String.valueOf(val2)+")");

                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
        skbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                val2 = progress;
                tv2.setText(String.valueOf(progressChangedValue));
                tv4.setBackgroundColor(Color.parseColor("#" + hexcode(val1) + hexcode(val3) + hexcode(val2)));
                tv4.setText("#" + hexcode(val1) + hexcode(val3) + hexcode(val2));
                if(Stt=="0"){
                    tv5.setText("Use seekbar without data output \n Click bluetooth button to connect device");
                }else{
                    Activity3.clientClass.send(String.valueOf(val1)+"."+String.valueOf(val3)+"."+String.valueOf(val2)+")");
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        skbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                val3 = progress;
                tv3.setText(String.valueOf(progressChangedValue));
                tv4.setBackgroundColor(Color.parseColor("#" + hexcode(val1) + hexcode(val3) + hexcode(val2)));
                tv4.setText("#" + hexcode(val1) + hexcode(val3) + hexcode(val2));
                if(Stt=="0"){
                    tv5.setText("Use seekbar without data output \n Click bluetooth button to connect device");
                }else{
                    Activity3.clientClass.send(String.valueOf(val1)+"."+String.valueOf(val3)+"."+String.valueOf(val2)+")");
                }


            }

            public void onStartTrackingTouch(SeekBar seekBar) {

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {


            }


        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Activity3.clientClass.send("f");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Calendar c = Calendar.getInstance();

                int minutes = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH)+1;
                int year = c.get(Calendar.YEAR);
                String time = hour + ":" + minutes + "-"+ day + ":" + month +":" + year + ">";



                Activity3.clientClass.send(time);


                tv5.setText("Sent data for set up time \n If clock still isn't true, please double tap");
            }
        });
    }
    public void onPause() {
        super.onPause();



    }
}