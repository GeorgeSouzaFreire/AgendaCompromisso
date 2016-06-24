package br.poc.agendacompromisso.async;

/**
 * Created by 36736636825 on 14/12/2015.
 */

import android.content.Context;
import android.os.AsyncTask;


public abstract class AsyncTaskRunner<T, TU> extends AsyncTask<T, Integer, TU> {
    private InterfaceAsyncTask<TU> _asyncTaskRunner;

    public AsyncTaskRunner(InterfaceAsyncTask<TU> asyncTaskRunner) {
        _asyncTaskRunner = asyncTaskRunner;
    }

    protected Context getContext() {
        return _asyncTaskRunner.getContext();
    }

    @Override
    protected void onPreExecute() {
        _asyncTaskRunner.taskStarting();
    }

    @Override
    protected void onPostExecute(TU result) {
        _asyncTaskRunner.taskCompleted(result);
    }
}