package com.example.usuario.testeowifi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TesteoWifi extends Service {
    private static final String TESTEO_WIFI = "TESTEO_WIFI";
    public boolean enEjecucion = false;

    Tester tester;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("WIFI", "Servicio detección WIFI Activado");
        tester = new Tester();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!enEjecucion){
            enEjecucion=true;
            Log.i(TESTEO_WIFI, "Servicio iniciándose");
            tester.start();
        }else{
            Log.i(TESTEO_WIFI, "Servicio ya iniciado");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        enEjecucion= false;
        tester.interrupt();

    }

    private class Tester extends Thread{



        public boolean CompruebaWifi(){
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo= connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (networkInfo!=null){
                if (networkInfo.isConnected())
                    return true;
            }
            return false;
        }

        @Override
        public void run() {
            super.run();
            while(enEjecucion){
                Log.i(TESTEO_WIFI, "Servicio se está ejecutando");
                if (CompruebaWifi()){
                    Log.i(TESTEO_WIFI, "Tengo WIFI");
                }else{

                    Log.i(TESTEO_WIFI, "Wifi desactivada");
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
