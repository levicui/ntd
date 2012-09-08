package com.example.ntd.util;

import java.util.ArrayList;
import java.util.List;

import com.example.ntd.R;
import com.example.ntd.entity.Contact;

public class FakeData {

	static public ArrayList<String> msgs = new ArrayList<String>();

	static private Contact[] contacts;

	static public Contact[] getContacts() {
		if (contacts == null) {
			List<Contact> contactList = new ArrayList<Contact>();
			contactList.add(new Contact("Boy",
					"Please reply my email as soon as possible...", true,
					R.drawable.boy));
			contactList.add(new Contact("Girl", "Go to lunch?", false,
					R.drawable.girl));
			contactList.add(new Contact("A Man", "", false, R.drawable.man));
			contactList.add(new Contact("Foo", "Haha", false, R.drawable.foo));
			contactList.add(new Contact("Bar", "", true, R.drawable.bar));
			contactList.add(new Contact("Another Man", "OK", false,
					R.drawable.man));
			contacts = contactList.toArray(new Contact[contactList.size()]);
			return contacts;
		}
		return contacts;
	}
	
	static public void updateDriving(String name, boolean status) {
		for (int i = 0; i < getContacts().length; ++i) {
			if (getContacts()[i].getName().equals(name)) {
				getContacts()[i].setDrivingStatus(status);
				return;
			}
		}
	}
	
	static public void updateLastMsg(String name, String msg) {
		for (int i = 0; i < getContacts().length; ++i) {
			if (getContacts()[i].getName().equals(name)) {
				getContacts()[i].setLastMessage(msg);
				return;
			}
		}
	}
}
