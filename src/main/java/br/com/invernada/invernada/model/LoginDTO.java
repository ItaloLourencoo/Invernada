package br.com.invernada.invernada.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class LoginDTO {

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String senha;

    public @NotNull @Size(max = 255) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Size(max = 255) String email) {
        this.email = email;
    }

    public @NotNull @Size(max = 255) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull @Size(max = 255) String senha) {
        this.senha = senha;
    }
}