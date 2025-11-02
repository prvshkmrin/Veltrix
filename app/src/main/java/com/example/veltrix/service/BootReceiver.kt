package com.example.veltrix.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("BootReceiver", "Device boot completed or restart detected")
        // TODO: re-schedule WorkManager jobs if needed
        // Example: Scheduler.rescheduleAll(context)
    }
}
