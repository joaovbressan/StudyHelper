package com.example.studyhelper

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StudyHelperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    // (RF03) - Cria o canal para os lembretes
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Lembretes de Tarefas"
            val descriptionText = "Canal para notificações de lembretes do StudyHelper"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("STUDY_HELPER_CHANNEL", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
