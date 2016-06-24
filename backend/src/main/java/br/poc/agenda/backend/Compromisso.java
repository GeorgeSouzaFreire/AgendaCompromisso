package br.poc.agenda.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

/**
 * Created by 36736636825 on 07/06/2016.
 */
@Entity
public class Compromisso implements Serializable {
    private static final long serialVersionUID = 7390103290165670089L;
    @Id
    private Long id;
    private String titulo;
    private String data;
    private String descricao;

    public Compromisso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
}
