package com.dbay.apns4j;

import com.dbay.apns4j.model.PushNotification;

/**
 * A delegate that gets notified of the status of notification delivery to the
 * Apple Server.
 *
 * The delegate doesn't get notified when the notification actually arrives at
 * the phone.
 */
public interface ApnsDelegate {

    /**
     * Called when the delivery of the message failed for any reason
     *
     * If message is null, then your notification has been rejected by Apple but
     * it has been removed from the cache so it is not possible to identify
     * which notification caused the error. In this case subsequent
     * notifications may be lost. If this happens you should consider increasing
     * your cacheLength value to prevent data loss.
     *
     * @param message the notification that was attempted to be sent
     * @param responseCode the cause and description of the failure
     */
    public void messageSendFailed(PushNotification message, byte[] responseCode);

    /**
     * A NOOP delegate that does nothing!
     */
    public final static ApnsDelegate EMPTY = new ApnsDelegateAdapter();
}
