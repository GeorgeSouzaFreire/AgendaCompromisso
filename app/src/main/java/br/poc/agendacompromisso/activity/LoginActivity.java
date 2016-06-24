package br.poc.agendacompromisso.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.entidade.Login;
import br.poc.agendacompromisso.regras.RegraLoginAsyncTask;
import br.poc.agendacompromisso.util.ConstantesAgenda;


public class LoginActivity extends AppCompatActivity implements OnClickListener, InterfaceAsyncTask {


    public static boolean ERRO = false;
    // UI references.
    private EditText senha, email;
    private Button cadastro, login;

    // AsyncTask
    private RegraLoginAsyncTask regraLoginAsyncTask;

    private View loginFormView;
    private View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


    }

    private void init() {
        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
        email = (EditText) findViewById(R.id.email);
        cadastro = (Button) findViewById(R.id.cadastro);
        login = (Button) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);

        login.setOnClickListener(this);
        cadastro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cadastro:
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
                break;
            case R.id.login:
                if (validaCampos()) {
                    if (validaEmail()) {
                        progress(true);
                        regraLoginAsyncTask = new RegraLoginAsyncTask(this);
                        regraLoginAsyncTask.execute(ConstantesAgenda.GET_USUARIO, setObjLogin());
                    } else {
                        Toast.makeText(this, "Email inválido.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private Login setObjLogin() {
        Login loginObj = new Login();
        loginObj.setEmail(email.getText().toString());
        loginObj.setSenha(senha.getText().toString());
        return loginObj;
    }

    private void progress(boolean isRetorno) {
        if (isRetorno) {
            progressView.setVisibility(View.VISIBLE);
            loginFormView.setVisibility(View.GONE);
        } else {
            progressView.setVisibility(View.GONE);
            loginFormView.setVisibility(View.VISIBLE);
        }

    }

    private boolean validaCampos() {
        return !email.getText().toString().equals("") && !senha.getText().toString().equals("");
    }

    private boolean validaEmail() {
        return email.getText().toString().contains("@");
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void taskStarting() {

    }

    @Override
    public void taskCompleted(Object result) {
        List<Object> results = (List<Object>) result;
        Integer option = (Integer) results.get(0);

        switch (option) {
            case ConstantesAgenda.GET_USUARIO:
                boolean isRetornoUser = (boolean) results.get(1);
                if (isRetornoUser) {
                    if (LoginActivity.ERRO) {
                        Toast.makeText(this, "Erro no serviço de login, tente novamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, Principal.class));
                        finish();
                    }
                } else {
                    Toast.makeText(this, "Não foi possivel efetuar login, tente novamente", Toast.LENGTH_LONG).show();
                }
                progress(false);
                LoginActivity.ERRO = false;
                break;
        }

    }
}

