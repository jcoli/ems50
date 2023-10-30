package com.tecnocoli.ems50;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Created by Jeferson Coli on 10/30/23.
 * Tecnocoli
 * jcoli@tecnocoli.com.br
 */
public class BtConnection {

    private static BtConnection conectionBT;
    private final BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothDevice BTDevice;
    private final BluetoothSocket BTSocket;
    private BufferedReader mBufferedReader = null;
    private final UUID activeUUID= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //ID padrão da porta serial.
    private boolean isConnected = true;
    private final int REQUEST_ENABLE_BT = 1;

    private BtConnection(BluetoothDevice device) {
        BTDevice = device;
        BluetoothSocket temp = null;

        try
        {
            temp = BTDevice.createRfcommSocketToServiceRecord(activeUUID);
        }
        catch (IOException io)
        {
            Log.i("LOG", "Erro IO");
        }
        BTSocket = temp;

        if(BTAdapter.isDiscovering())
            BTAdapter.cancelDiscovery();

        try {

            InputStream aStream = null;
            InputStreamReader aReader = null;
            BTSocket.connect();

            aStream = BTSocket.getInputStream();
            aReader = new InputStreamReader( aStream );
            mBufferedReader = new BufferedReader( aReader );
        }catch (IOException e) {
            isConnected = false;
            return;
        }
    }

    public BufferedReader getmBufferedReader() {
        return mBufferedReader;
    }

    public static  BtConnection getInstance(BluetoothDevice d, boolean subrescrever) {
        if (conectionBT == null)
            conectionBT= new  BtConnection(d);
        else
        if(subrescrever)
        {
            conectionBT = new BtConnection(d);
            Log.i( "ConexaoBluetooth","Sobrescreveu a conexão");
        }
        return conectionBT;
    }

    public boolean isConnected () {
        return BTAdapter.isEnabled();
    }


    public BluetoothSocket getConection() {
        return BTSocket;
    }

    public boolean finish() {
        try {
            BTSocket.close();
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }
}
