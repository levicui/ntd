package com.example.ntd.util;


public class Util {
	
	final public static String APP_TAG = "NTD";
	public static String PHONE_NUM = "";
	
	public class BundleType {
		final public static String ICON_ID = "icon";
		final public static String NAME = "name";
		final public static String IS_DRIVING = "driving";
	}
	
	public class IntentType {
		final public static String INTENT_RECEIVER_SELECT = "ReceivingIntent";
		final public static String INTENT_UPDATE_STATUS = "UpdateStatus";
	}
	
	final private static String UPDATE_URL = "http://rohwer.appspot.com?phone=%s&status=%d";
	
	public static String getUpdateURL(String phone, int status) {
		return String.format(UPDATE_URL, phone, status);
	}
	
}
