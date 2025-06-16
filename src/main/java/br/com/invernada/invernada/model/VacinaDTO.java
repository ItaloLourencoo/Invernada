package br.com.invernada.invernada.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class VacinaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @NotNull
    private LocalDate dataAplicacao;

    private LocalDate dataProxAplicacao;

    @NotNull
    private Long animalId;

    private String identificadorAnimal;

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

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(final Long animalId) {
        this.animalId = animalId;
    }

    public String getIdentificadorAnimal() {
        return identificadorAnimal;
    }

    public void setIdentificadorAnimal(String identificadorAnimal) {
        this.identificadorAnimal = identificadorAnimal;
    }
}
