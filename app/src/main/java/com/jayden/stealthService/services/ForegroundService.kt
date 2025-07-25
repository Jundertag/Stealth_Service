package com.jayden.stealthService.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import com.jayden.stealthService.R
import java.lang.Thread.sleep

class ForegroundService : Service() {

    var pingService = false

    companion object {
        private const val NOTIFICATION_ID = 1

        private const val CHANNEL_ID = "foreground_service"
        private const val CHANNEL_NAME = "Foreground Service"

        private const val TAG = "ForegroundService"
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_ID, createNotification())
        return START_STICKY
    }

    private fun createNotification(): Notification {
        val channelId = CHANNEL_ID
        val channelName = CHANNEL_NAME

        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        return Notification.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("Running in the background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    fun pingService() {
        pingService = true
        Log.d(TAG, "Ping Service Command Sent")

        while (pingService) {
            sleep(5000)
            Log.i(TAG, "Ping Successful")
        }
    }

    fun stopPingService() {
        pingService = false
    }

    override fun onDestroy() {
        super.onDestroy()
        pingService = false
    }
}