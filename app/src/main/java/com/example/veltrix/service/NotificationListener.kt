package com.example.veltrix.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d("NotificationListener", "Connected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // called when a notification arrives — use for auto-reply triggers
        Log.d("NotificationListener", "Posted: ${sbn?.packageName}")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // notification removed
    }
}
