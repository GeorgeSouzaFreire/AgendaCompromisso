package br.poc.agendacompromisso.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import br.poc.agenda.backend.acompanhamentoEndpoint.AcompanhamentoEndpoint;
import br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso;


/**
 * Created by 36736636825 on 08/06/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, List<Compromisso>> {
    private static AcompanhamentoEndpoint myApiService = null;
    private Context context;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Compromisso> doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            AcompanhamentoEndpoint.Builder builder = new AcompanhamentoEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sharp-ring-133523.appspot.com/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.listQuote().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Compromisso> compromissos) {
        super.onPostExecute(compromissos);
        for (Compromisso c : compromissos) {
            Toast.makeText(context, c.getData(), Toast.LENGTH_LONG).show();
        }
    }

}