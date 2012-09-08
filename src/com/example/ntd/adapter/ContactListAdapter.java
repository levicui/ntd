package com.example.ntd.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ntd.R;
import com.example.ntd.entity.Contact;

public class ContactListAdapter extends BaseAdapter {
	
    private LayoutInflater inflater;
    private ArrayList<Contact> contactList; 

    public ContactListAdapter(Context context) {
    	inflater = LayoutInflater.from(context);
    	initContactList();
    }

    public void initContactList() {
    	contactList = new ArrayList<Contact>();
    	contactList.add(new Contact("Boy", "Please reply my email as soon as possible...", true, R.drawable.boy));
    	contactList.add(new Contact("Girl", "Go to lunch?", false, R.drawable.girl));
    	contactList.add(new Contact("A Man", "", false, R.drawable.man));
    	contactList.add(new Contact("Foo", "Haha", false, R.drawable.foo));
    	contactList.add(new Contact("Bar", "", true, R.drawable.bar));
    	contactList.add(new Contact("Another Man", "OK", false, R.drawable.man));
    }
    
    public int getCount() {
        return contactList.size();
    }

    /**
     * Since the data comes from an array, just returning the index is
     * sufficent to get at the data. If we were using a more complex data
     * structure, we would return whatever object represents one row in the
     * list.
     *
     * @see android.widget.ListAdapter#getItem(int)
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * Use the array index as a unique id.
     *
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_main, null);
            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.photoIcon = (ImageView) convertView.findViewById(R.id.icon);
            holder.nameText = (TextView) convertView.findViewById(R.id.nameText);
            holder.msgText = (TextView) convertView.findViewById(R.id.msgText);
            holder.drivingIcon = (ImageView) convertView.findViewById(R.id.drivingIcon);

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        holder.photoIcon.setImageResource(contactList.get(position).getIconID());
        holder.nameText.setText(contactList.get(position).getName());
        holder.msgText.setText(contactList.get(position).getLastMessage());
        if (contactList.get(position).isDriving()) {
        	holder.drivingIcon.setImageResource(R.drawable.driving);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView nameText, msgText;
        ImageView drivingIcon, photoIcon;
    }
}
