package br.poc.agendacompromisso.regras;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import br.poc.agenda.backend.acompanhamentoEndpoint.AcompanhamentoEndpoint;
import br.poc.agendacompromisso.activity.Principal;
import br.poc.agendacompromisso.async.AsyncTaskRunner;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.entidade.Compromisso;
import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by 36736636825 on 14/06/2016.
 */
public class RegraCompromissoAsyncTask extends AsyncTaskRunner<Object, Object> {

    private static AcompanhamentoEndpoint endpoint = null;

    public RegraCompromissoAsyncTask(InterfaceAsyncTask<Object> asyncTaskRunner) {
        super(asyncTaskRunner);
    }

    @Override
    protected Object doInBackground(Object[] params) {

        Integer option = (Integer) params[0];
        List<Object> result = new ArrayList<>();
        result.add(option);

        switch (option) {
            case ConstantesAgenda.ADD:
                Compromisso compromisso = (Compromisso) params[1];
                result.add(insertCompromisso(compromisso));
                break;
            case ConstantesAgenda.UPDATE:
                br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso comproservice = (br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso) params[1];
                result.add(updateService(comproservice));
                break;
            case ConstantesAgenda.REMOVE:
                Long id = (Long) params[1];
                result.add(deleteObjetoServico(id));
                break;
            case ConstantesAgenda.LIST:
                result.add(retornaListaServico());
                break;


        }
        return result;
    }

    private boolean updateService(br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso comproservice) {
        try {
            AcompanhamentoEndpoint.Builder builder = new AcompanhamentoEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            AcompanhamentoEndpoint service = builder.build();
            br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso id = service.updateQuote(comproservice).execute();

            if (id != null) {
                return true;
            }

        } catch (SocketTimeoutException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (ConnectException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (UnknownHostException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (Exception e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        }
        return false;
    }

    private boolean deleteObjetoServico(Long id) {
        try {
            AcompanhamentoEndpoint.Builder builder = new AcompanhamentoEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            AcompanhamentoEndpoint service = builder.build();
            if (service.removeQuote(id).execute() != null) {
                return true;
            }

        } catch (SocketTimeoutException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (ConnectException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (UnknownHostException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (Exception e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        }
        return false;
    }

    private boolean insertCompromisso(Compromisso compromisso) {
        br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso response = null;
        try {
            AcompanhamentoEndpoint.Builder builder = new AcompanhamentoEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            AcompanhamentoEndpoint service = builder.build();
            br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso quote = new br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso();
            quote.setTitulo(compromisso.getTitulo());
            quote.setData(compromisso.getData());
            quote.setDescricao(compromisso.getDescricao());
            response = service.insertQuote(quote).execute();

            if (response != null) {
                return true;
            }

        } catch (SocketTimeoutException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (ConnectException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (UnknownHostException e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (Exception e) {
            Principal.IS_ERRO = true;
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        }
        return false;
    }


    protected List<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso> retornaListaServico() {
        if (endpoint == null) {
            AcompanhamentoEndpoint.Builder builder = new AcompanhamentoEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sharp-ring-133523.appspot.com/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            endpoint = builder.build();
        }
        try {
            return endpoint.listQuote().execute().getItems();
        } catch (SocketTimeoutException e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (ConnectException e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (UnknownHostException e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (SSLHandshakeException e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (IOException e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        } catch (Exception e) {
            Log.d(RegraCompromissoAsyncTask.class.getSimpleName(), e.getMessage() != null ? e.getMessage() : "Erro");
        }
        return Collections.EMPTY_LIST;
    }

}
