package com.example.rgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Activity3 extends AppCompatActivity {
    public static BluetoothAdapter bluetoothAdapter;
    public static BluetoothDevice selectedDevice;
    private List<BluetoothDevice> pairedDevices;
    private List<String> deviceNames;
    ListView listView;
    Button listDevices;
    TextView status;
   public static ClientClass clientClass;
   String Stt;


    BluetoothDevice[] btArray;


    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING=2;
    static final int STATE_CONNECTED=3;
    static final int STATE_CONNECTION_FAILED=4;
    static final int STATE_MESSAGE_RECEIVED=5;

    int REQUEST_ENABLE_BLUETOOTH=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        //clientClass = new ClientClass(getApplicationContext(),bluetoothAdapter, selectedDevice);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        listView=findViewById(R.id.listview);
        listDevices=findViewById(R.id.button5);
        status=findViewById(R.id.textView6);

        if(!bluetoothAdapter.isEnabled()){
            Intent enableIntent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE_BLUETOOTH);
        }

        implementListeners();


    }


    private void implementListeners() {

        listDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
                String[] strings = new String[bt.size()];
                btArray = new BluetoothDevice[bt.size()];
                int index = 0;

                if (bt.size() > 0) {
                    for (BluetoothDevice device : bt) {
                        btArray[index] = device;
                        strings[index] = device.getName();
                        index++;
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                selectedDevice=btArray[position];

                clientClass = new ClientClass(getApplicationContext(),bluetoothAdapter, selectedDevice);

                if(clientClass.cnt() ==true) status.setText("Connected");
                else status.setText("Connection failed");




                Stt="1";
                Intent intent = new Intent();

                intent.putExtra("StatusBt", Stt);

                setResult(RESULT_OK,intent);
            }
        });



    }




    @Override
    protected void onDestroy() {

        //clientClass.close();
        Intent intent=null;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        super.onDestroy();

    }


}