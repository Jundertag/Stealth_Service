package com.jayden.stealthService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.jayden.stealthService.databinding.ActivityMainBinding
import com.jayden.stealthService.services.*
import android.util.Log


class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var binding: ActivityMainBinding? = null
    private val foregroundService = ForegroundService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        val serviceIntent = Intent(this, ForegroundService::class.java)

        binding?.buttonStartService?.setOnClickListener {
            startForegroundService(serviceIntent)
            Log.d(TAG, "Start Service Command Sent")
        }

        binding?.buttonPingService?.setOnClickListener {
            foregroundService.pingService()
            Log.d(TAG, "Ping Service Command Sent")
        }

        binding?.buttonStopPingService?.setOnClickListener {
            foregroundService.stopPingService()
            Log.d(TAG, "Stop Ping Service Command Sent")
        }

        binding?.buttonKillService?.setOnClickListener {
            foregroundService.stopService()
            stopService(serviceIntent)
            Log.d(TAG, "Kill Service Command Sent")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        binding = null
    }
}