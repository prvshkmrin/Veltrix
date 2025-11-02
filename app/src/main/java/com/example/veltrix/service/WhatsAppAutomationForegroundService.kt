package com.example.veltrix.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class WhatsAppAutomationForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("WhatsAppFgService", "Started")
        // If you plan to actually run foreground, create a notification and call startForeground(...)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("WhatsAppFgService", "Destroyed")
    }
}
