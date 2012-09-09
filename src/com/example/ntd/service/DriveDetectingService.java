package com.example.ntd.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.ntd.util.Util;

public class DriveDetectingService extends Service {
	
	private Timer locationUpdateTimer;
    public static final String INFO = "DriveDetectingService";
	
    @Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(Util.APP_TAG, "Driving Detector Service Created!");
		if (locationUpdateTimer!=null)
			locationUpdateTimer.cancel();
		else
			locationUpdateTimer = new Timer();
		locationUpdateTimer.scheduleAtFixedRate(new LocationUpdateTimerTask(), 1000 * 10, 1000*5);
	}
	
	private class LocationUpdateTimerTask extends TimerTask {
		@Override
		public void run() {
			new Handler().post(new Runnable() {
				public void run() {
					updateDrivingStatus();
				}
			});
		}
	}
	
	@Override 
	public void onDestroy() {
		if (locationUpdateTimer!=null)
			locationUpdateTimer.cancel();
	}
	
	private void updateDrivingStatus() {
	    try {
			URL url = new URL(Util.getUpdateURL(Util.PHONE_NUM, 0));
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) urlConn;
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream responseStream = httpConn.getInputStream();
				Reader reader = new InputStreamReader(responseStream);
				responseStream.close();

			    Intent intent = new Intent(Util.IntentType.INTENT_UPDATE_STATUS);
			    sendBroadcast(intent);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
