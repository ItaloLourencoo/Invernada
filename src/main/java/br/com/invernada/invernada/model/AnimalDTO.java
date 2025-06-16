package br.com.invernada.invernada.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AnimalDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @NotNull
    @Size(max = 255)
    private String raca;

    @NotNull
    private BigDecimal peso;

    @NotNull
    private LocalDate dataNascimento;

    @Size(max = 255)
    private String numeroIdentificacaoPai;

    @Size(max = 255)
    private String numeroIdentificacaoMae;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Size(max = 255)
    private String sexo;

    @NotNull
    @Size(max = 255)
    private String numeroIdentificacao;

    @NotNull
    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    @NotNull
    private Long usuarioIdCriacao;

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

    public String getRaca() {
        return raca;
    }

    public void setRaca(final String raca) {
        this.raca = raca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(final LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroIdentificacaoPai() {
        return numeroIdentificacaoPai;
    }

    public void setNumeroIdentificacaoPai(final String numeroIdentificacaoPai) {
        this.numeroIdentificacaoPai = numeroIdentificacaoPai;
    }

    public String getNumeroIdentificacaoMae() {
        return numeroIdentificacaoMae;
    }

    public void setNumeroIdentificacaoMae(final String numeroIdentificacaoMae) {
        this.numeroIdentificacaoMae = numeroIdentificacaoMae;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(final String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(final LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(final LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getUsuarioIdCriacao() {
        return usuarioIdCriacao;
    }

    public void setUsuarioIdCriacao(final Long usuarioIdCriacao) {
        this.usuarioIdCriacao = usuarioIdCriacao;
    }

    public @NotNull BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(@NotNull BigDecimal peso) {
        this.peso = peso;
    }
}
