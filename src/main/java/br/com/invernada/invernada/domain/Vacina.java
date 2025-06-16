package br.com.invernada.invernada.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class Vacina {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataAplicacao;

    @Column
    private LocalDate dataProxAplicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id_id", nullable = false)
    private Animal animalId;

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

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(final LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataProxAplicacao() {
        return dataProxAplicacao;
    }

    public void setDataProxAplicacao(final LocalDate dataProxAplicacao) {
        this.dataProxAplicacao = dataProxAplicacao;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(final Animal animalId) {
        this.animalId = animalId;
    }

}
