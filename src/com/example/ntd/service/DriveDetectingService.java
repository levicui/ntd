package com.example.ntd.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.ntd.util.Util;

public class DriveDetectingService extends Service {
	
	private Timer locationUpdateTimer;
	
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
	    Intent intent = new Intent(Util.IntentType.INTENT_UPDATE_STATUS);
	}
	
	
}
