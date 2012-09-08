package com.example.ntd.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;

/**
 * User: antonio081014
 * Date: 9/8/12
 * Time: 12:49 PM
 */
public class ServiceChecking extends Service {

    GetStatusThread myThread;

    public static final int sendInterval = 8;
//    public static final String TAG = "Service Title";
    public static final String INFO = "ATTHACKATHONDATA";

    public IBinder onBind(Intent intent) {
        if(myThread == null){
            myThread = new GetStatusThread();
            myThread.start();
        }
        return null;
    }

    public void onCreate(){
        super.onCreate();
    }

    public void onStart(Intent intent, int startId){
        if(myThread == null){
            myThread = new GetStatusThread();
            myThread.start();
        }
        Toast.makeText(getApplicationContext(), "service started. halo", Toast.LENGTH_SHORT).show();
        super.onStart(intent, startId);
    }

    public boolean onUnbind(Intent intent){
        if(myThread != null){
            myThread.flag = false;
            myThread = null;
        }
        return super.onUnbind(intent);
    }

    public void onDestroy(){
        if(myThread != null){
            myThread.flag = false;
            myThread = null;
        }
        super.onDestroy();
    }

    public class GetStatusThread extends Thread{
        public boolean flag;

        public GetStatusThread(){
            this.flag = true;
        }

        private  String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
//                JSONArray array = new JSONArray(jsonText);
                return json;
            } finally {
                is.close();
            }
        }

        public void processMessage(JSONArray users) throws Exception{
            int len = users.length();
            String[] phones = new String[len];
            String[] driving = new String[len];
            for (int i=0; i<len; i++){
                JSONObject user = (JSONObject)users.get(i);
                phones[i] = (String)(user.get("phone"));
                driving[i] = (String)(user.get("driving"));
            }

            Intent intent = new Intent(INFO);
            intent.putExtra("phones", phones);
            intent.putExtra("drivings", driving);

            sendBroadcast(intent);
        }

        public JSONObject writeJSON() {
            JSONObject object = new JSONObject();
            try {
                object.put("phone", "123456789");
                object.put("driving", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return object;
        }

        public void run(){
            while(flag){
                try{
//                    JSONObject a = writeJSON();
//                    JSONArray array = readJsonFromUrl("http://rohwer.appspot.com/users");
//                    JSONObject json = readJsonFromUrl("http://rohwer.appspot.com/users");
//                    processMessage(array);

                    Intent intent = new Intent(INFO);
                    intent.putExtra("phone", "Girl");
                    intent.putExtra("driving", "true");
//                    intent.setAction("test.android.mybroadcast");
                    sendBroadcast(intent);

                    sleep(sendInterval);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
