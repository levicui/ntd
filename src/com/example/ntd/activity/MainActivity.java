package com.example.ntd.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;

import com.example.ntd.R;
import com.example.ntd.adapter.ContactListAdapter;
import com.example.ntd.util.FakeData;

public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ContactListAdapter(this, R.layout.activity_main, FakeData.getContacts()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public String getPhoneNumber(){ 
	    TelephonyManager mTelephonyMgr; 
	    mTelephonyMgr = (TelephonyManager)this.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);  
	    return mTelephonyMgr.getLine1Number().substring(2); 
	}
}
