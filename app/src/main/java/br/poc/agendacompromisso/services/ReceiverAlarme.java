package br.poc.agendacompromisso.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by George on 16/06/2016.
 */
public class ReceiverAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, AlarmService.class);
        service.putExtra(ConstantesAgenda.TEXT_TITULO_NOTIFICATION, intent.getStringExtra(ConstantesAgenda.TEXT_TITULO_NOTIFICATION));
        context.startService(service);
    }
}
