package com.example.ntd.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;

import com.example.ntd.R;
import com.example.ntd.adapter.ContactListAdapter;
import com.example.ntd.entity.Contact;
import com.example.ntd.service.ServiceChecking;
import com.example.ntd.util.FakeData;
import com.example.ntd.util.Util;

public class MainActivity extends ListActivity {
	
	private AccelerationServiceReceiver accelerationServiceReceiver;
	private DriveDetectingReceiver driveDetectingReceiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.PHONE_NUM = getPhoneNumber();
		Log.d(Util.APP_TAG, "This Phone No.: " + Util.PHONE_NUM);
        accelerationServiceReceiver = new AccelerationServiceReceiver();
        driveDetectingReceiver = new DriveDetectingReceiver();
        IntentFilter msgFilter = new IntentFilter(ServiceChecking.INFO);
        IntentFilter drivingFilter = new IntentFilter(Util.IntentType.INTENT_UPDATE_STATUS);
        this.registerReceiver(accelerationServiceReceiver, msgFilter);
        this.registerReceiver(driveDetectingReceiver, drivingFilter);
        startService(new Intent(this, com.example.ntd.service.ServiceChecking.class));
		startService(new Intent(this, com.example.ntd.service.DriveDetectingService.class));
		setListAdapter(new ContactListAdapter(this, R.layout.activity_main, FakeData.getContacts()));
	}
	
    public void onDestroy() {
        unregisterReceiver(accelerationServiceReceiver);
        unregisterReceiver(driveDetectingReceiver);
        super.onDestroy();
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public String getPhoneNumber(){ 
	    TelephonyManager mTelephonyMgr; 
	    mTelephonyMgr = (TelephonyManager)this.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);  
	    return mTelephonyMgr.getLine1Number().substring(1); 
	}
	
	public class AccelerationServiceReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Bundle extras = intent.getExtras();

            String phone = intent.getStringExtra("phone");
            String driving = intent.getStringExtra("driving");
            for(Contact contact : FakeData.getContacts()){
                if(contact.getName().compareToIgnoreCase(phone)==0){
                    contact.setDrivingStatus(driving.compareToIgnoreCase("true") ==0 ? true : false);
                    break;
                }
            }
        }
    }
	
	public class DriveDetectingReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Bundle extras = intent.getExtras();

            String phone = intent.getStringExtra("phone");
            String driving = intent.getStringExtra("driving");
            for(Contact contact : FakeData.getContacts()){
                if(contact.getName().compareToIgnoreCase(phone)==0){
                    contact.setDrivingStatus(driving.compareToIgnoreCase("true") ==0 ? true : false);
                    break;
                }
            }
        }
    }
}
