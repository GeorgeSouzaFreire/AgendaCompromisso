package br.poc.agendacompromisso.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso;
import br.poc.agendacompromisso.R;
import br.poc.agendacompromisso.activity.AddCompromissoActivity;
import br.poc.agendacompromisso.activity.Principal;
import br.poc.agendacompromisso.regras.RegraCompromissoAsyncTask;
import br.poc.agendacompromisso.util.ConstantesAgenda;

/**
 * Created by 36736636825 on 06/06/2016.
 */
public class AdapterCompromisso extends BaseAdapter {

    private LayoutInflater layout;
    private List<Compromisso> compromissos;
    private Context context;
    private ArrayList<Compromisso> arraylist;
    private RegraCompromissoAsyncTask regraCompromissoAsyncTask;
    private Principal principal;
    private ProgressDialog progressDialog;

    public AdapterCompromisso(Context context, Principal principal, List<Compromisso> compromissos, ProgressDialog progressDialog) {
        this.layout = LayoutInflater.from(context);
        this.context = context;
        this.principal = principal;
        this.compromissos = compromissos;
        this.progressDialog = progressDialog;
        arraylist = new ArrayList<Compromisso>();
        arraylist.addAll(compromissos);
    }

    @Override
    public int getCount() {
        return compromissos.size();
    }

    @Override
    public Object getItem(int position) {
        return compromissos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Compromisso compromisso = compromissos.get(position);

        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            convertView = layout.inflate(R.layout.item_compromisso, parent, false);

            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_item_compromisso);
            holder.excluir = (ImageView) convertView.findViewById(R.id.id_excluir);
            holder.titulo = (TextView) convertView.findViewById(R.id.id_titulo);
            holder.data = (TextView) convertView.findViewById(R.id.id_data);
            holder.descricao = (TextView) convertView.findViewById(R.id.id_descricao);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titulo.setText(compromisso.getTitulo());
        holder.data.setText(compromisso.getData());
        holder.descricao.setText(compromisso.getDescricao());


        holder.excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Removendo Item da Lista");
                progressDialog.show();
                regraCompromissoAsyncTask = new RegraCompromissoAsyncTask(principal);
                regraCompromissoAsyncTask.execute(ConstantesAgenda.REMOVE, compromisso.getId());
            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(principal, AddCompromissoActivity.class);
                intent.putExtra(ConstantesAgenda.ID, String.valueOf(compromisso.getId()));
                principal.startActivity(intent);
            }
        });

        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundResource(R.color.white);
        } else {
            holder.linearLayout.setBackgroundResource(R.color.background);
        }


        return convertView;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase();

        compromissos.clear();
        if (charText.length() == 0) {
            compromissos.addAll(arraylist);

        } else {
            for (Compromisso postDetail : arraylist) {
                if (postDetail != null && postDetail.getTitulo() != null) {
                    if (charText.length() != 0 && postDetail != null && postDetail.getTitulo().toLowerCase().contains(charText)) {
                        compromissos.add(postDetail);
                    } else if (charText.length() != 0 && postDetail != null && postDetail.getTitulo().toLowerCase().contains(charText)) {
                        compromissos.add(postDetail);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        ImageView excluir;
        TextView titulo;
        TextView data;
        TextView descricao;

    }

}
