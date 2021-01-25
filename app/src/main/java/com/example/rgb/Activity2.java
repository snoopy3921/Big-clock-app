package com.example.rgb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Activity2 extends AppCompatActivity {





    TextView tv5;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    String Status="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tv5 = findViewById(R.id.textView5);

        Intent intent = getIntent();
        Status = intent.getStringExtra("Trangthai");

        System.out.println(Status);


        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        if(!Status.equals("0")){
            tv5.setText("Bluetooth connected");
        }else{
            tv5.setText("Bluetooth is not connected");
        }


        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status.equals("0")) {
                    Activity3.clientClass.send("a");

                } else {
                    tv5.setText("Cant send data because of not bluetooth connection");
                }
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status.equals("0")) {
                    Activity3.clientClass.send("b");

                } else {
                    tv5.setText("Cant send data because of not bluetooth connection");
                }
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status.equals("0")) {
                    Activity3.clientClass.send("c");

                } else {
                    tv5.setText("Cant send data because of not bluetooth connection");
                }
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status.equals("0")) {
                    Activity3.clientClass.send("d");

                } else {
                    tv5.setText("Cant send data because of not bluetooth connection");
                }
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status.equals("0")) {
                    Activity3.clientClass.send("e");

                } else {
                    tv5.setText("Cant send data because of not bluetooth connection");
                }
            }
        });




    }
    public void onResume() {

        super.onResume();









    }


}