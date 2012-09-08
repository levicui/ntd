package com.example.ntd.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ntd.R;
import com.example.ntd.activity.MessageActivity;
import com.example.ntd.entity.Contact;
import com.example.ntd.util.Util;

public class ContactListAdapter extends ArrayAdapter<Contact> {
	
    private LayoutInflater inflater;
    private int textViewID;
    private Contact[] contacts;
    private Intent chatIntent;

    public ContactListAdapter(
    		Context context, 
    		int textViewResourceId,
    		Contact[] contacts) {
		super(context, textViewResourceId, contacts);
		this.contacts = contacts;
		textViewID = textViewResourceId;
    	inflater = LayoutInflater.from(context);
        chatIntent = new Intent(this.getContext(), MessageActivity.class);
    }
    
    public int getCount() {
        return contacts.length;
    }
    
    public Contact getItem(int position) {
        return contacts[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(textViewID, null);
            holder = new ViewHolder();
            holder.photoIcon = (ImageView) convertView.findViewById(R.id.icon);
            holder.nameText = (TextView) convertView.findViewById(R.id.nameText);
            holder.msgText = (TextView) convertView.findViewById(R.id.msgText);
            holder.drivingIcon = (ImageView) convertView.findViewById(R.id.drivingIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.photoIcon.setImageResource(getItem(position).getIconID());
        holder.nameText.setText(getItem(position).getName());
        holder.msgText.setText(getItem(position).getLastMessage());
        if (getItem(position).isDriving()) {
        	holder.drivingIcon.setImageResource(R.drawable.driving);
        }
        OnClickListener textViewClickListener = new OnClickListener() { 
            public void onClick(View v) {
                Log.d(Util.APP_TAG, "Clicked on " + ((TextView)v).getText());
                ContactListAdapter.this.getContext().startActivity(chatIntent);
            }
        };
        holder.msgText.setOnClickListener(textViewClickListener);
        holder.nameText.setOnClickListener(textViewClickListener);
        return convertView;
    }

    static class ViewHolder {
        TextView nameText, msgText;
        ImageView drivingIcon, photoIcon;
    }
}
