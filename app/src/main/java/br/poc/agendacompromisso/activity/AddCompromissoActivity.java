package br.poc.agendacompromisso.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.poc.agendacompromisso.util.AndroidUtil;
import br.poc.agendacompromisso.util.ConstantesAgenda;
import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.async.InterfaceAsyncTask;
import br.poc.agendacompromisso.entidade.Compromisso;
import br.poc.agendacompromisso.regras.RegraCompromissoAsyncTask;
import br.poc.agendacompromisso.services.ReceiverAlarme;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddCompromissoActivity extends AppCompatActivity implements InterfaceAsyncTask , View.OnClickListener {

    // AsyncTask
    private RegraCompromissoAsyncTask addCompromissoAsyncTask;
    private br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso compromissoModel;
    // Componentes
    private Button salvar;
    private TextView data;
    private TextView hora;
    private Button btnData;
    private Button btnHora;
    private EditText titulo;
    private EditText descricao;
    private ProgressDialog progressDialog;
    private boolean isEdicao;

    // Objeto
    private Compromisso compromisso;
    public AddCompromissoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromisso);

        init();

        if(getIntent().getSerializableExtra(ConstantesAgenda.ID) != null){
            initPutExtra();
        }
    }

    private void initPutExtra() {
        compromissoModel = getIntent().getSerializableExtra(ConstantesAgenda.ID);

        if(compromisso != null){
            titulo.setText(compromisso.getTitulo());
            data.setText(compromisso.getData().split(" ")[0]);
            hora.setText(compromisso.getData().split(" ")[1]);
            descricao.setText(compromisso.getDescricao());
        }

    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titulo    = (EditText) findViewById(R.id.titulo);
        descricao = (EditText) findViewById(R.id.descricao);
        salvar    = (Button) findViewById(R.id.id_salvar);
        data      = (TextView) findViewById(R.id.data);
        hora      = (TextView) findViewById(R.id.hora);

        btnData = (Button) findViewById(R.id.btn_data);
        btnHora = (Button) findViewById(R.id.btn_hora);

        btnData.setOnClickListener(this);
        btnHora.setOnClickListener(this);
        salvar.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void taskStarting() {
    }

    @Override
    public void taskCompleted(Object result) {

        List<Object> results = (List<Object>) result;
        Integer option = (Integer) results.get(0);

        switch (option) {
            case ConstantesAgenda.ADD:
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Dados inseridos com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case ConstantesAgenda.UPDATE:
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Update realizado com sucesso.", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case ConstantesAgenda.REMOVE:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_salvar:
                if (obrigatorio()) {
                    if(validahora()) {
                        progress();
                        if (isEdicao) {
                            persisteEdicaoAgenda(compromissoModel);
                            alarme();
                        } else {
                            persistirDadosAgenda();
                            alarme();
                            asynTask();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Hora escolhida menor que hora atual", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_data:

                final Calendar c = Calendar.getInstance();
                int mYear  = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay   = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                data.setText(zeroEsquerda(dayOfMonth) + "/" + zeroEsquerda((monthOfYear + 1)) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btn_hora:

                final Calendar calendar = Calendar.getInstance();
                int mHour   = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hora.setText(zeroEsquerda(hourOfDay) + ":" + zeroEsquerda(minute));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                break;
        }
    }

    private String zeroEsquerda(int numero){
        String numeroZero = "";
        if(String.valueOf(numero).length() == 1){
            numeroZero = "0" + String.valueOf(numero);
        }else{
            numeroZero = String.valueOf(numero);
        }
        return numeroZero;
    }

    private boolean validahora() {
        boolean isRetorno = false;
        String dataHoraAtual  = AndroidUtil.getHoraAtual();
        String dataHoraPicker = AndroidUtil.formatDataHoraNota(data.getText().toString() , hora.getText().toString());

        if(dataHoraPicker.compareTo(dataHoraAtual) > 0){
            isRetorno = true;
        }

        return isRetorno;
    }

    private void alarme(){

        //Dia
        int dia = 1;
        //Mes
        int mes = 2;
        //Ano
        int ano = 3;
        //hora
        int hora = 4;
        //minutos
        int minutos = 5;

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, getDataHora(mes));
        calendar.set(Calendar.YEAR, getDataHora(ano));
        calendar.set(Calendar.DAY_OF_MONTH, getDataHora(dia));

        calendar.set(Calendar.HOUR_OF_DAY, getDataHora(hora));
        calendar.set(Calendar.MINUTE, getDataHora(minutos));
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();
        date.getTime();
        long horaDatePicker = calendar.getTimeInMillis();
        long horaAtual      = AndroidUtil.horaAtualMillis();

        Intent alarmIntent = new Intent(AddCompromissoActivity.this, ReceiverAlarme.class);
        alarmIntent.putExtra(ConstantesAgenda.TEXT_TITULO_NOTIFICATION, titulo.getText().toString());
        Log.i(Principal.TAG, "Texto Titulo " + titulo.getText().toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(AddCompromissoActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.i(Principal.TAG, "Millesimo Date Picker " + String.valueOf(horaDatePicker));
        Log.i(Principal.TAG, "Millesimo Data Atual " + String.valueOf(horaAtual));

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, horaAtual, horaDatePicker - horaAtual , pendingIntent);

        Log.i(Principal.TAG, "Calculo Millesimo " + String.valueOf(horaDatePicker - horaAtual));

    }

    private int getDataHora(int dataHora) {
        int retorno = 0;
        String date = data.getText().toString();
        String time = hora.getText().toString();
        switch (dataHora){
            case 1:
                retorno = Integer.parseInt(date.substring(0 , 2));
                break;
            case 2:
                retorno = Integer.parseInt(date.substring(3 , 5));
                break;
            case 3:
                retorno = Integer.parseInt(date.substring(6 , 10));
                break;
            case 4:
                retorno = Integer.parseInt(time.substring(0 , 2));
                break;
            case 5:
                retorno = Integer.parseInt(time.substring(3 , 5));
                break;
        }
        return retorno;
    }

    private void persisteEdicaoAgenda(Compromisso compromisso) {
        addCompromissoAsyncTask = new RegraCompromissoAsyncTask(AddCompromissoActivity.this);
        addCompromissoAsyncTask.execute(ConstantesAgenda.UPDATE, compromisso);
    }

    private void asynTask() {
        addCompromissoAsyncTask = new RegraCompromissoAsyncTask(AddCompromissoActivity.this);
        addCompromissoAsyncTask.execute(ConstantesAgenda.ADD, compromisso);
    }

    private boolean obrigatorio() {
        return !(titulo.getText().toString().equals("")
                && descricao.getText().toString().equals("")
                && data.getText().toString().equals("")
                && hora.getText().toString().equals(""));
    }

    private void progress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserindo dados...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    private void persistirDadosAgenda() {
        if(compromisso == null) {
            compromisso = new Compromisso();
            compromisso.setTitulo(titulo.getText().toString());
            compromisso.setData(retornaDataHora());
            compromisso.setDescricao(descricao.getText().toString());
        }
    }

    private String retornaDataHora(){
        return data.getText().toString() + " " + hora.getText().toString();
    }
}
