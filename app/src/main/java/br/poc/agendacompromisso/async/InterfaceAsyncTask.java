package br.poc.agendacompromisso.async;

import android.content.Context;

/**
 * Created by 36736636825 on 14/12/2015.
 */
public interface InterfaceAsyncTask<T> {
    Context getContext();

    void taskStarting();

    void taskCompleted(T result);
}
