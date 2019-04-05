package com.wix.reactnativenotifications.gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.wix.reactnativenotifications.core.notification.IPushNotification;
import com.wix.reactnativenotifications.core.notification.PushNotification;

import java.util.Map;

import com.useinsider.insider.Insider;
import com.useinsider.insider.InsiderCore;
import com.useinsider.insider.RequestUtils;
import com.useinsider.insider.config.InsiderVariableDataType;

import static com.wix.reactnativenotifications.Defs.LOGTAG;

/**
 * Instance-ID + token refreshing handling service. Contacts the GCM to fetch the updated token.
 *
 * @author amitd
 */
public class FcmInstanceIdListenerService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        // Bundle bundle = message.toIntent().getExtras();
        // Log.d(LOGTAG, "New message from GCM: " + bundle);

        // try {
        //     final IPushNotification notification = PushNotification.get(getApplicationContext(), bundle);
        //     notification.onReceived();
        // } catch (IPushNotification.InvalidNotificationException e) {
        //     // A GCM message, yes - but not the kind we know how to work with.
        //     Log.v(LOGTAG, "GCM message handling aborted", e);
        // }

        super.onMessageReceived(remoteMessage);
        if (remoteMessage != null && remoteMessage.getData().containsKey("source") && remoteMessage.getData().get("source").equals("Insider")) {
            Insider.Instance.handleNotification(getApplicationContext(), remoteMessage);
            return;
        }
    }
}
