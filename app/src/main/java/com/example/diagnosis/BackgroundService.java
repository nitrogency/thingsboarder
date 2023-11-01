package com.example.diagnosis;

import static android.content.Intent.getIntent;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StatFs;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundService extends Service {

    private Handler handler;
    private Runnable periodicTask;

    private String urlAddress;
    private float batteryCap;
    private String batteryStatus;
    private double longitude;
    private double latitude;
    private double ramPercent;
    private double storagePercent;

    @Override
    public void onCreate() {
        Log.i("BackgroundService", "Service launched");
        urlAddress = getResources().getString(R.string.url_address);

        handler = new Handler();
        periodicTask = new Runnable() {
            @Override
            public void run() {
                sendStatus();
                handler.postDelayed(this, 60000);
            }
        };

        handler.post(periodicTask);
    }

    public void getStatus() {
        getBatteryInfo();
        getLocation();
        getMemory();
        getStorage();
    }

    public void getBatteryInfo() {
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        try {
            batteryCap = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            if(BatteryManager.BATTERY_STATUS_CHARGING == 2) {
                batteryStatus = "Yes";
            }
            else {
                batteryStatus = "No";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLocation() {
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(checkLocationPermission()) {
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

    public void getMemory() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        ramPercent = mi.availMem / (double)mi.totalMem * 100.0;
    }

    public void getStorage() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long blockSize = statFs.getBlockSizeLong();
        long totalSize = statFs.getBlockCountLong()*blockSize;
        long availableSize = statFs.getAvailableBlocksLong()*blockSize;
        long usedSize = totalSize - availableSize;

        storagePercent = ((double) usedSize / totalSize) * 100.0;
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
                    Log.i("BackgroundService", "Thread started");
                    getStatus();
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

                    message.put("longitude", longitude);
                    message.put("latitude", latitude);

                    message.put("manufacturer", Build.MANUFACTURER);
                    message.put("model", Build.MODEL);
                    message.put("user", Build.USER);
                    message.put("host", Build.HOST);
                    message.put("version", Build.VERSION.RELEASE);
                    message.put("api", Build.VERSION.SDK_INT);

                    message.put("RAM", ramPercent);
                    message.put("storage", storagePercent);
                    message.put("request", conn.getRequestMethod());

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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(periodicTask);
        Log.i("BackgroundService", "Service stopped");
    }
}
