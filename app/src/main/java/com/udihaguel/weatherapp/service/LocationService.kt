package com.udihaguel.weatherapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.udihaguel.weatherapp.R

class LocationService: LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_baseline_map_24)
            .setContentTitle("Weather App")
            .setContentText("Location Service")



    }

    //private fun getMainActivityPendingIntent() = PendingIntent.getActivity(this,)


    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }


    companion object{
        const val NOTIFICATION_CHANNEL_ID = "com.udihaguel.weatherapp.channelId"
        const val NOTIFICATION_CHANNEL_NAME = "com.udihaguel.weatherapp.channelName"
        const val NOTIFICATION_ID = 1
    }
}