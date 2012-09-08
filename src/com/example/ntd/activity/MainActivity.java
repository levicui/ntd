package com.example.ntd.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.ntd.R;
import com.example.ntd.adapter.ContactListAdapter;
import com.example.ntd.entity.Contact;

public class MainActivity extends ListActivity {

	private Contact[] contacts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contacts = initContactList();
		setListAdapter(new ContactListAdapter(this, R.layout.activity_main, contacts));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public Contact[] initContactList() {
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.add(new Contact("Boy", "Please reply my email as soon as possible...", true, R.drawable.boy));
		contactList.add(new Contact("Girl", "Go to lunch?", false, R.drawable.girl));
		contactList.add(new Contact("A Man", "", false, R.drawable.man));
		contactList.add(new Contact("Foo", "Haha", false, R.drawable.foo));
		contactList.add(new Contact("Bar", "", true, R.drawable.bar));
		contactList.add(new Contact("Another Man", "OK", false, R.drawable.man));
		return contactList.toArray(new Contact[contactList.size()]);
	}

}
