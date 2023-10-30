package com.tecnocoli.ems50;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tecnocoli.ems50.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private BluetoothAdapter BA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        BA = BluetoothAdapter.getDefaultAdapter();
        BtEnable();


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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void BtEnable(){
        //liga o bluetooth
        if (BA == null){
            Toast.makeText(MainActivity.this, "Bluetooth NOT FOUND", Toast.LENGTH_LONG).show();
        }else{
            if (!BA.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);

            }else{
                Toast.makeText(MainActivity.this, "BlueTooth ON", Toast.LENGTH_LONG).show();
            }

        }

        }
//        if (!BA.isEnabled()) {
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
//            Toast.makeText(MainActivity.this, "Bluetooth Ligado", Toast.LENGTH_SHORT).show();
//
//        } else {
//            lookFor();
//        }
//        // Essa if em especial, verifica se a versão Android é 6.0 ou maior, pois caso seja, uma permissão para localização, além das relacionadas ao Bluetooth, sao necessárias.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//                requestPermissions(PermissionsLocation,ResquestLocationId);
//            }
//        }
//    }
}