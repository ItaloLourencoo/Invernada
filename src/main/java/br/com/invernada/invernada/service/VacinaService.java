package br.com.invernada.invernada.service;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Vacina;
import br.com.invernada.invernada.model.VacinaDTO;
import br.com.invernada.invernada.repos.AnimalRepository;
import br.com.invernada.invernada.repos.VacinaRepository;
import br.com.invernada.invernada.util.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VacinaService {

    private final VacinaRepository vacinaRepository;
    private final AnimalRepository animalRepository;

    public VacinaService(final VacinaRepository vacinaRepository,
            final AnimalRepository animalRepository) {
        this.vacinaRepository = vacinaRepository;
        this.animalRepository = animalRepository;
    }

    public List<VacinaDTO> findAll() {
        final List<Vacina> vacinas = vacinaRepository.findAll(Sort.by("id"));
        return vacinas.stream()
                .map(vacina -> mapToDTO(vacina, new VacinaDTO()))
                .toList();
    }

    public VacinaDTO get(final Long id) {
        return vacinaRepository.findById(id)
                .map(vacina -> mapToDTO(vacina, new VacinaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VacinaDTO vacinaDTO) {
        final Vacina vacina = new Vacina();
        mapToEntity(vacinaDTO, vacina);
        return vacinaRepository.save(vacina).getId();
    }

    public void update(final Long id, final VacinaDTO vacinaDTO) {
        final Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(vacinaDTO, vacina);
        vacinaRepository.save(vacina);
    }

    public void delete(final Long id) {
        vacinaRepository.deleteById(id);
    }

    private VacinaDTO mapToDTO(final Vacina vacina, final VacinaDTO vacinaDTO) {
        vacinaDTO.setId(vacina.getId());
        vacinaDTO.setNome(vacina.getNome());
        vacinaDTO.setDataAplicacao(vacina.getDataAplicacao());
        vacinaDTO.setDataProxAplicacao(vacina.getDataProxAplicacao());
        vacinaDTO.setAnimalId(vacina.getAnimalId() == null ? null : vacina.getAnimalId().getId());
        vacinaDTO.setIdentificadorAnimal(vacina.getAnimalId().getNumeroIdentificacao());
        return vacinaDTO;
    }

    private Vacina mapToEntity(final VacinaDTO vacinaDTO, final Vacina vacina) {
        vacina.setNome(vacinaDTO.getNome());
        vacina.setDataAplicacao(vacinaDTO.getDataAplicacao());
        vacina.setDataProxAplicacao(vacinaDTO.getDataProxAplicacao());
        final Animal animalId = vacinaDTO.getAnimalId() == null ? null : animalRepository.findById(vacinaDTO.getAnimalId())
                .orElseThrow(() -> new NotFoundException("animalId not found"));
        vacina.setAnimalId(animalId);
        return vacina;
    }

    public List<VacinaDTO> listarPorIdentificadorAnimal(String idOuNome) {
        idOuNome = "%"+idOuNome+"%";
        final Animal animalId = idOuNome == null ? null :
                animalRepository.findByNumeroIdentificacaoLikeIgnoreCaseOrNomeLikeIgnoreCase(idOuNome, idOuNome)
                .orElseThrow(() -> new NotFoundException("animalId not found"));

        List<VacinaDTO> listaVacinasDTO = new ArrayList<>();
        List<Vacina> vacinas = vacinaRepository.findAllByAnimalId(animalId);
        vacinas.stream().forEach(v -> {
            v.setAnimalId(animalId);
            listaVacinasDTO.add(mapToDTO(v, new VacinaDTO()));
        });
        return listaVacinasDTO;

    }
}
