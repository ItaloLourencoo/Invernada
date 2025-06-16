package br.com.invernada.invernada.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class InseminacaoDTO {

    private Long id;

    @NotNull
    private LocalDate dataCio;

    @NotNull
    private LocalDate dataInseminacao;

    @NotNull
    @Size(max = 255)
    private String codigoSemen;

    @Size(max = 255)
    private String observacoes;

    @NotNull
    private Long animalId;

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

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(final Long animalId) {
        this.animalId = animalId;
    }

}
