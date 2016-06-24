package br.poc.agendacompromisso.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso;
import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.adapter.AdapterCompromisso;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.regras.RegraCompromissoAsyncTask;
import br.poc.agendacompromisso.util.ConstantesAgenda;
import br.poc.agendacompromisso.view.FloatingActionButton;

public class Principal extends AppCompatActivity implements View.OnClickListener, InterfaceAsyncTask {
    public static final String TAG = Principal.class.getSimpleName();
    //Primitivos
    public static boolean IS_ERRO = false;
    // View
    private ListView listView;
    private ProgressDialog progressDialog;
    // Objetos
    private AdapterCompromisso adapterCompromisso;
    private RegraCompromissoAsyncTask regraCompromissoAsyncTask;
    private List<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso> compromisso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        init();
        servicoAppEngine();
        progressDialog = new ProgressDialog(this);
        processamento();
    }

    private void processamento() {
        progressDialog.setMessage("Carregando dados do serviço Agenda");
        progressDialog.show();
    }

    private void servicoAppEngine() {
        regraCompromissoAsyncTask = new RegraCompromissoAsyncTask(Principal.this);
        regraCompromissoAsyncTask.execute(ConstantesAgenda.LIST);
    }

    private void adapter(List<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso> compromisso) {
        adapterCompromisso = new AdapterCompromisso(getApplicationContext(), Principal.this, compromisso, progressDialog);
        listView.setAdapter(adapterCompromisso);
        adapterCompromisso.notifyDataSetChanged();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Notas");
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list_view);

        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.refresh);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.adicionar);

        refresh.setOnClickListener(this);
        add.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        processamento();
        servicoAppEngine();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adicionar:
                startActivity(new Intent(Principal.this, AddCompromissoActivity.class));
                break;
            case R.id.refresh:
                processamento();
                servicoAppEngine();
                break;
        }
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
            case ConstantesAgenda.LIST:
                compromisso = (List<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso>) results.get(1);
                if (compromisso != null && !compromisso.isEmpty()) {
                    adapter(compromisso);
                    progressDialog.dismiss();
                } else {
                    if (Principal.IS_ERRO) {
                        Toast.makeText(getApplicationContext(), "Erro no serviço App Engine - Atualize novamente.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Não há item na agenda", Toast.LENGTH_SHORT).show();

                    }
                    Principal.IS_ERRO = false;
                    progressDialog.dismiss();
                }
                break;
            case ConstantesAgenda.REMOVE:
                if (Principal.IS_ERRO) {
                    Toast.makeText(getApplicationContext(), "Erro ao remover item.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Item removido com sucesso", Toast.LENGTH_SHORT).show();

                }
                Principal.IS_ERRO = false;
                servicoAppEngine();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_busca, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                if (adapterCompromisso != null) {
                    adapterCompromisso.filter(searchQuery.toString().trim());
                    listView.invalidate();
                }
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Compromisso> getCompromisso() {
        return compromisso;
    }
}
