package com.example.studyhelper.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.studyhelper.data.local.model.Tarefa
import java.util.Calendar

object AlarmScheduler {

    fun scheduleReminder(context: Context, tarefa: Tarefa, horasAntes: Int = 2) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
            putExtra("TAREFA_NOME", tarefa.nome)
            putExtra("TAREFA_ID", tarefa.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            tarefa.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val horaLembrete = Calendar.getInstance().apply {
            timeInMillis = tarefa.dataEntrega
            add(Calendar.HOUR_OF_DAY, -horasAntes)
        }.timeInMillis

        // Agenda apenas se o lembrete for no futuro
        if (horaLembrete > System.currentTimeMillis()) {
            if (alarmManager.canScheduleExactAlarms()) { // Verifica permissão (API 31+)
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    horaLembrete,
                    pendingIntent
                )
            }
        }
    }

    fun cancelReminder(context: Context, tarefa: Tarefa) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderBroadcastReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            tarefa.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
    }
}
