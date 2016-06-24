package br.poc.agendacompromisso.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.entidade.Login;
import br.poc.agendacompromisso.regras.RegraLoginAsyncTask;
import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by 36736636825 on 15/06/2016.
 */
public class CadastroActivity extends AppCompatActivity implements View.OnClickListener, InterfaceAsyncTask {

    private EditText email, senha, repitaSenha;
    private Button salvarLogin;
    private RegraLoginAsyncTask regraLoginAsyncTask;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_login);

        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cadastro Usuário");
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        repitaSenha = (EditText) findViewById(R.id.repita_senha);
        salvarLogin = (Button) findViewById(R.id.salvar_login);

        salvarLogin.setOnClickListener(this);

    }

    private void progress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserindo dados do cadastro...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar_login:
                if (validaCampos()) {
                    if (validaEmail()) {
                        if (senhaIgual()) {
                            progress();
                            regraLoginAsyncTask = new RegraLoginAsyncTask(this);
                            regraLoginAsyncTask.execute(ConstantesAgenda.INSERT_LOGIN, objLogin());
                        } else {
                            Toast.makeText(this, "Senha não confere", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Email inválido", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Favor preencher todos os campos.", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private boolean validaEmail() {
        return email.getText().toString().contains("@");
    }

    private Login objLogin() {
        Login login = new Login();
        login.setEmail(email.getText().toString());
        login.setSenha(senha.getText().toString());
        return login;
    }

    private boolean senhaIgual() {
        return senha.getText().toString().equals(repitaSenha.getText().toString());
    }

    private boolean validaCampos() {
        return !email.getText().toString().equals("")
                && !senha.getText().toString().equals("")
                && !repitaSenha.getText().toString().equals("");
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
            case ConstantesAgenda.INSERT_LOGIN:
                br.poc.agenda.backend.loginEndpoint.model.Login logins = (br.poc.agenda.backend.loginEndpoint.model.Login) results.get(1);
                if (logins != null) {
                    if (LoginActivity.ERRO) {
                        Toast.makeText(this, "Erro no cadastro, tente novamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    Toast.makeText(this, "Não foi possivel efetuar o cadastro, tente novamente", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
                break;
        }
    }
}
