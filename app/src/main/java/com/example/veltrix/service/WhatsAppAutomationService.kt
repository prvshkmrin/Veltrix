package com.example.veltrix.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.util.Log

class WhatsAppAutomationService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("WhatsAppAutomationSvc", "Accessibility service connected")
        // configure service if needed
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // receive accessibility events (UI changes) here
        // keep minimal: handle only what you need later
    }

    override fun onInterrupt() {
        // handle interruptions
    }
}
