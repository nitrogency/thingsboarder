package com.example.diagnosis;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.BatteryManager;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    /*
        TODO:
            Add scheduling (runs every minute or something)
            Add location tracking
            Change burnt-in variables with localization
            Add HTTPS
     */

    private String urlAddress;
    private float batteryCap;
    private String batteryStatus;
    private int batteryTemp;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlAddress = "http://34.89.88.64:8080/api/v1/xY4jRk5wIUw0g0YrW3ua/telemetry";
        getStatus();
        sendStatus();
    }

    public void getStatus() {
        getBatteryInfo();
        getLocation();
    }

    public void getBatteryInfo() {
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        try {
            int level = getIntent().getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = getIntent().getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            batteryCap = level * 100 / (float)scale;

            if(BatteryManager.BATTERY_STATUS_CHARGING == 2) {
                batteryStatus = "Yes";
            }
            else {
                batteryStatus = "No";
            }

            batteryTemp = getIntent().getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLocation() {
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(checkLocationPermission() == true) {
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
            else {
                Log.e("LOCATION", "Location permission not given. Skipping location");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    public void sendStatus(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAddress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject message = new JSONObject();
                    message.put("batteryCap", batteryCap);
                    message.put("batteryStatus", batteryStatus);
                    message.put("batteryTemp", batteryTemp);

                    message.put("longitude", longitude);
                    message.put("latitude", latitude);

                    message.put("manufacturer", Build.MANUFACTURER);
                    message.put("model", Build.MODEL);
                    message.put("user", Build.USER);
                    message.put("host", Build.HOST);
                    message.put("version", Build.VERSION.RELEASE);
                    message.put("api", Build.VERSION.SDK_INT);


                    Log.i("JSON", message.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(message.toString());

                    os.flush();
                    os.close();

                    Log.i("RESPONSE", String.valueOf(conn.getResponseCode()));
                    Log.i("MESSAGE", conn.getResponseMessage());

                    conn.disconnect();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


}