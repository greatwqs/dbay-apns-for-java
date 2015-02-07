package com.dbay.apns4j;

import com.dbay.apns4j.model.PushNotification;

/**
 * A NOOP delegate that does nothing!
 */
public class ApnsDelegateAdapter implements ApnsDelegate {

	@Override
	public void messageSendFailed(PushNotification message, byte[] responseCode) {
	}

}
