package com.tecnocoli.ems50;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    View connectionView;

    private Button btnConnect;
    private Button btnListDevices;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket BTSocket = null;
    private final String nomeDispositivo = "beMyEyes"; //Mude beMyEyes para o nome do seu módulo Bluetooth.
    private final int REQUEST_ENABLE_BT = 1; // Código padrão para o requerimento em tempo de execução.
    private BtConnection connectionBT;
    private IntentFilter it = null;
    private Thread connectThread;


    public ConnectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConnectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConnectionFragment newInstance(String param1, String param2) {
        ConnectionFragment fragment = new ConnectionFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        connectionView = inflater.inflate(R.layout.fragment_connection, container, false);
        btnConnect = (Button) connectionView.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonClicked(v);
            }
        });
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        return connectionView;
    }

    public void onButtonClicked(View view)
    {
        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(getContext(), "BlueTooth Off... ", Toast.LENGTH_LONG).show();
        }else {
            final String address = "78:21:84:BB:22:8E";
            final String name = "EMS10";
            boolean fail = false;
//            connectThread = new Thread(new Runnable() {
//
//                public void run() {


                    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
                    try {
                        BTSocket = createBluetoothSocket(device);
                        if (BTSocket == null){
                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), BTSocket.toString(), Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
            try {
                BTSocket.connect();
                if (BTSocket == null) {
                    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), BTSocket.toString(), Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                try {
                    fail = true;
                    BTSocket.close();
//                    mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
//                            .sendToTarget();
                } catch (IOException e2) {
                    //insert code to deal with this
                    Toast.makeText(getContext(), "ERROR SOCKET", Toast.LENGTH_SHORT).show();
                }
            }

//                }//run
//
//            });
//            connectThread.start();

        }
        Toast.makeText(getContext(), "after run", Toast.LENGTH_SHORT).show();
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e("BT", "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }


    public void BtEnable(){
        //liga o bluetooth
        if (!bluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
            Toast.makeText(getContext(), "Bluetooth Ligado", Toast.LENGTH_SHORT).show();

        } else {
            lookFor();
        }
    }

    protected void lookFor() { // Procura por dispositivos
        if(bluetoothAdapter.startDiscovery()){}
        else{

        }

    }
}