package br.poc.agendacompromisso.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.activity.Principal;
import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by 36736636825 on 16/06/2016.
 */
public class AlarmService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);

        if (intent != null) {
            String textoDescricao = intent.getStringExtra(ConstantesAgenda.TEXT_TITULO_NOTIFICATION);
            addNotification(textoDescricao);
        }
        return START_STICKY;
    }

    private void addNotification(String titulo) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Principal.class), 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Nota Compromisso")
                .setContentText(titulo)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
