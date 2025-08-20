package com.example.tasks

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.tasks.router.FirstFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace<FirstFragment>(R.id.fragment_container)
        }
    }
}

class ChargingNotification(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val context = applicationContext

    override fun doWork(): Result {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryAction = context.registerReceiver(null, intentFilter)

        val status = batteryAction?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL

        if (isCharging) {
            sendNotification()
        }

        return Result.success()
    }

    private fun sendNotification() {
        val notificationManager = NotificationManagerCompat.from(context)

        val channelId = "Test channel"
        val channelName = "Charge notification"

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Устройство на зарядке")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val isPermissionGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        if (!isPermissionGranted) {
            Log.d("!!!!!", "Notifications are not allowed")
        }

        notificationManager.notify(0, notification)
    }
}

