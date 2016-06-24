package br.poc.agendacompromisso.entidade;

/**
 * Created by 36736636825 on 06/06/2016.
 */
public class Compromisso {


    private String titulo;
    private String data;
    private String descricao;
    private Long id;

    public Compromisso() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}



