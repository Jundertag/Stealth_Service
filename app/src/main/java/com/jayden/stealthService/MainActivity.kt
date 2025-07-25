package com.jayden.stealthService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.jayden.stealthService.databinding.ActivityMainBinding
import com.jayden.stealthService.services.*


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val foregroundService = ForegroundService()
    private val serviceIntent = Intent(this, ForegroundService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onStart() {
        super.onStart()

        binding?.buttonStartService?.setOnClickListener {
            startForegroundService(serviceIntent)
        }

        binding?.buttonPingService?.setOnClickListener {
            foregroundService.pingService()
        }

        binding?.buttonStopPingService?.setOnClickListener {
            foregroundService.stopPingService()
        }

        binding?.buttonKillService?.setOnClickListener {
            stopService(serviceIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}