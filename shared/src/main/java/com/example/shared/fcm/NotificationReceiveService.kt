package com.example.shared.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class NotificationReceiveService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Timber.d("New firebase token: $token")
    }
}