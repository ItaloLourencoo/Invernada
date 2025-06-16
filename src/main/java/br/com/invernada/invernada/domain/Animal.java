package br.com.invernada.invernada.domain;

import br.com.invernada.invernada.model.Sexo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Animal {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column
    private String numeroIdentificacaoPai;

    @Column
    private String numeroIdentificacaoMae;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false)
    private String numeroIdentificacao;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @Column
    private LocalDateTime dataAtualizacao;

    @Column
    private BigDecimal peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id_criacao_id", nullable = false)
    private Usuario usuarioIdCriacao;

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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(final Sexo sexo) {
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

    public Usuario getUsuarioIdCriacao() {
        return usuarioIdCriacao;
    }

    public void setUsuarioIdCriacao(final Usuario usuarioIdCriacao) {
        this.usuarioIdCriacao = usuarioIdCriacao;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
}
