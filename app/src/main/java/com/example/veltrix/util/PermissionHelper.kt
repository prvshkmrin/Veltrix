import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

// Open Accessibility settings so user can enable your AccessibilityService
fun openAccessibilitySettings(activity: Activity) {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    activity.startActivity(intent)
}

// Open Notification Listener settings so user can enable your NotificationListenerService
fun openNotificationListenerSettings(activity: Activity) {
    val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
    activity.startActivity(intent)
}

// Open "Draw over other apps" (overlay) settings for your package
fun openOverlayPermissionSettings(activity: Activity) {
    val intent = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:${activity.packageName}")
    )
    activity.startActivity(intent)
}

// Request POST_NOTIFICATIONS at runtime (Android 13+)
fun requestPostNotifications(activity: Activity, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
            requestCode
        )
    }
}

// Open request to ignore battery optimizations (optional)
fun requestIgnoreBatteryOptimizations(activity: Activity) {
    val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
    intent.data = Uri.parse("package:${activity.packageName}")
    activity.startActivity(intent)
}
