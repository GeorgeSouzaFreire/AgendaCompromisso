package br.poc.agendacompromisso.regras;

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
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import br.poc.agenda.backend.loginEndpoint.LoginEndpoint;
import br.poc.agenda.backend.loginEndpoint.model.Login;
import br.poc.agendacompromisso.activity.LoginActivity;
import br.poc.agendacompromisso.async.AsyncTaskRunner;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by 36736636825 on 15/06/2016.
 */
public class RegraLoginAsyncTask extends AsyncTaskRunner<Object, Object> {

    private static LoginEndpoint endpoint = null;

    public RegraLoginAsyncTask(InterfaceAsyncTask<Object> asyncTaskRunner) {
        super(asyncTaskRunner);
    }

    @Override
    protected Object doInBackground(Object[] params) {

        Integer option = (Integer) params[0];
        List<Object> result = new ArrayList<>();
        result.add(option);

        switch (option) {
            case ConstantesAgenda.INSERT_LOGIN:
                result.add(insertUsuario((br.poc.agendacompromisso.entidade.Login) params[1]));
                break;
            case ConstantesAgenda.GET_USUARIO:
                result.add(retornoUsuario((br.poc.agendacompromisso.entidade.Login) params[1]));
                break;
        }
        return result;
    }

    private Login insertUsuario(br.poc.agendacompromisso.entidade.Login loginObj) {
        Login response = null;
        try {
            LoginEndpoint.Builder builder = new LoginEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            LoginEndpoint service = builder.build();
            Login login = new Login();
            login.setSenha(loginObj.getSenha());
            login.setEmail(loginObj.getEmail());

            response = service.insertLogin(login).execute();

        } catch (SocketTimeoutException e) {
            LoginActivity.ERRO = true;
        } catch (ConnectException e) {
            LoginActivity.ERRO = true;
        } catch (UnknownHostException e) {
            LoginActivity.ERRO = true;
        } catch (Exception e) {
            LoginActivity.ERRO = true;
        }
        return response;
    }


    private boolean retornoUsuario(br.poc.agendacompromisso.entidade.Login login) {
        List<Login> response = null;
        if (endpoint == null) {
            LoginEndpoint.Builder builder = new LoginEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://sharp-ring-133523.appspot.com/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            endpoint = builder.build();
        }
        try {
            response = endpoint.listLogin().execute().getItems();
            if (response != null && !response.isEmpty()) {
                for (Login retornoLogin : response) {
                    if (retornoLogin.getEmail().equals(login.getEmail()) && retornoLogin.getSenha().equals(login.getSenha())) {
                        return true;
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            LoginActivity.ERRO = true;
        } catch (ConnectException e) {
            LoginActivity.ERRO = true;
        } catch (UnknownHostException e) {
            LoginActivity.ERRO = true;
        } catch (SSLHandshakeException e) {
            LoginActivity.ERRO = true;
        } catch (IOException e) {
            LoginActivity.ERRO = true;
        } catch (Exception e) {
            LoginActivity.ERRO = true;
        }
        return false;
    }

}
