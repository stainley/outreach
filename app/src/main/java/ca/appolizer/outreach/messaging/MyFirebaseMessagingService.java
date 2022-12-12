package ca.appolizer.outreach.messaging;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.d(MyFirebaseMessagingService.class.getName(), "From: " + message.getFrom());

        // check if message contain data payload
        if (message.getData().size() > 0) {

        }

        // check if message contains a notification payload
        if (message.getNotification() != null) {
            Log.d(MyFirebaseMessagingService.class.getName(), "Message notification body: " + message.getNotification().getBody());
        }

    }
}
