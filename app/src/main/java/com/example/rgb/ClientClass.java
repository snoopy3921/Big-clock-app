package com.example.rgb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import static com.example.rgb.Activity3.bluetoothAdapter;

public class ClientClass {
    private static final UUID MY_UUID=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Context context;
    private BluetoothSocket bluetoothSocket ;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    public ClientClass(Context context, BluetoothAdapter bluetoothAdapter, BluetoothDevice bluetoothDevice){
        this.bluetoothAdapter=bluetoothAdapter;
        this.bluetoothDevice=bluetoothDevice;
        this.context=context;
        try{

            this.bluetoothSocket=this.bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);

            this.bluetoothSocket.connect();

        } catch (Exception ex) {
            try{
                bluetoothSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            Toast.makeText(context,"FAILED: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void send(String data){
        try {
            OutputStream outputStream = this.bluetoothSocket.getOutputStream();
            outputStream.write(data.getBytes());

        }catch (Exception ex){
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(context,"FAILED: "+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void connect(){
        try {
            bluetoothSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            Toast.makeText(context,"FAILED: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public Boolean cnt(){
        return bluetoothSocket.isConnected();
    }




}
