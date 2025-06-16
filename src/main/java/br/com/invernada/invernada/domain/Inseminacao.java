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
public class Inseminacao {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataCio;

    @Column(nullable = false)
    private LocalDate dataInseminacao;

    @Column(nullable = false)
    private String codigoSemen;

    @Column
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id_id", nullable = false)
    private Animal animalId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getDataCio() {
        return dataCio;
    }

    public void setDataCio(final LocalDate dataCio) {
        this.dataCio = dataCio;
    }

    public LocalDate getDataInseminacao() {
        return dataInseminacao;
    }

    public void setDataInseminacao(final LocalDate dataInseminacao) {
        this.dataInseminacao = dataInseminacao;
    }

    public String getCodigoSemen() {
        return codigoSemen;
    }

    public void setCodigoSemen(final String codigoSemen) {
        this.codigoSemen = codigoSemen;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(final Animal animalId) {
        this.animalId = animalId;
    }

}
