package com.example.studyhelper.services

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.studyhelper.R // Importe seu R (ex: para o ícone)

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val tarefaNome = intent.getStringExtra("TAREFA_NOME") ?: "Sua tarefa"
        val tarefaId = intent.getIntExtra("TAREFA_ID", 0)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, "STUDY_HELPER_CHANNEL")
            .setSmallIcon(R.mipmap.ic_launcher) // IMPORTANTE: Adicione um ícone em res/mipmap
            .setContentTitle("Lembrete de Tarefa!")
            .setContentText("Não se esqueça: $tarefaNome")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        notificationManager.notify(tarefaId, builder.build())
    }
}
