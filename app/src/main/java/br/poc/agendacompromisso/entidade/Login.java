package br.poc.agendacompromisso.entidade;

/**
 * Created by 36736636825 on 07/06/2016.
 */
public class Login {

    private Long id;
    private String email;
    private String senha;

    public Login() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
