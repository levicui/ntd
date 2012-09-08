package com.example.ntd.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.ntd.R;
import com.example.ntd.adapter.ContactListAdapter;

public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ContactListAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
