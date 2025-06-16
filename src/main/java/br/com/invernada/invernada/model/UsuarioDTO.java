package br.com.invernada.invernada.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UsuarioDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @NotNull
    @Size(max = 255)
    @UsuarioEmailUnique
    private String email;

    @Size(max = 255)
    private String telefone;

    @NotNull
    @Size(max = 255)
    private String senha;

    @Size(max = 255)
    private String perfil;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(final String perfil) {
        this.perfil = perfil;
    }

}
