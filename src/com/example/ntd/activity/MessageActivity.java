package com.example.ntd.activity;

import com.example.ntd.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MessageActivity extends Activity {
	
	public MessageActivity() {
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(Util.APP_TAG, "Message activity create");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(Util.APP_TAG, "Message activity start");
	}
}
