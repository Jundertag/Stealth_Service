package com.jayden.stealthService.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.jayden.stealthService.R

class ForegroundService : Service() {

    private var handler = Handler(Looper.getMainLooper())

    companion object {
        private const val NOTIFICATION_ID = 1

        private const val CHANNEL_ID = "foreground_service"
        private const val CHANNEL_NAME = "Foreground Service"

        private const val TAG = "ForegroundService"
    }

    private val ping = object : Runnable {
        override fun run() {
            Log.i(TAG, "Ping Successful")
            handler.postDelayed(this, 1000)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        startForeground(NOTIFICATION_ID, createNotification())
        return START_STICKY
    }

    private fun createNotification(): Notification {
        Log.d(TAG, "createNotification")

        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Running in the background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    fun pingService() {
        Log.d(TAG, "Ping Service Command Received")
        handler.post(ping)
    }

    fun stopPingService() {
        Log.d(TAG, "Stop Ping Service Command Received")
        handler.removeCallbacks(ping)
    }

    fun stopService() {
        Log.d(TAG, "Kill Service Command Received")
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        handler.removeCallbacks(ping)
        stopForeground(STOP_FOREGROUND_REMOVE)
    }
}