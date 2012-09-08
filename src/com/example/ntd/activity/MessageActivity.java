package com.example.ntd.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ntd.R;
import com.example.ntd.util.FakeData;
import com.example.ntd.util.Util;

public class MessageActivity extends ListActivity {

	private EditText textEdit;
	private ImageButton btnSend;
	private ImageView drivingIcon;
	private ArrayAdapter<String> msgAdapter;
	private String receiverName;

	public MessageActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		Log.d(Util.APP_TAG, "Message activity create");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(Util.APP_TAG, "Message activity start");
		if (msgAdapter == null) {
			msgAdapter = new ArrayAdapter<String>(this, R.layout.message_text,
					FakeData.msgs);
		}
		setListAdapter(msgAdapter);

		ImageView photoIcon = (ImageView) findViewById(R.id.msgPhotoIcon);
		TextView nameText = (TextView) findViewById(R.id.msgNameText);
		drivingIcon = (ImageView) findViewById(R.id.msgDrivingIcon);
		textEdit = (EditText) findViewById(R.id.userText);
		btnSend = (ImageButton) findViewById(R.id.btnSend);

		Intent receiverIntent = this.getIntent();
		Bundle bundle = receiverIntent
				.getBundleExtra(Util.IntentType.INTENT_RECEIVER_SELECT);
		photoIcon.setImageResource(bundle.getInt(Util.BundleType.ICON_ID));
		nameText.setText(bundle.getString(Util.BundleType.NAME));
		receiverName = bundle.getString(Util.BundleType.NAME);
		if (bundle.getBoolean(Util.BundleType.IS_DRIVING)) {
			drivingIcon.setImageResource(R.drawable.driving);
		}
		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sendMsg();
			}
		});
	}

	private void sendMsg() {
		String text = textEdit.getText().toString();
		msgAdapter.add(text);
		FakeData.updateLastMsg(receiverName, text);
		textEdit.setText(null);
	}
}
