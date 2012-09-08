package com.example.ntd.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;

import com.example.ntd.R;
import com.example.ntd.adapter.ContactListAdapter;
import com.example.ntd.entity.Contact;
import com.example.ntd.service.ServiceChecking;
import com.example.ntd.util.FakeData;

public class MainActivity extends ListActivity {
	
	private AccelerationServiceReceiver accelerationServiceReceiver;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        accelerationServiceReceiver = new AccelerationServiceReceiver();
        IntentFilter msgFilter = new IntentFilter(ServiceChecking.INFO);
        this.registerReceiver(accelerationServiceReceiver, msgFilter);
        startService(new Intent(this, com.example.ntd.service.ServiceChecking.class));
		
		setListAdapter(new ContactListAdapter(this, R.layout.activity_main, FakeData.getContacts()));
	}
	
    public void onDestroy() {
        unregisterReceiver(accelerationServiceReceiver);
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
	    return mTelephonyMgr.getLine1Number().substring(2); 
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
}
