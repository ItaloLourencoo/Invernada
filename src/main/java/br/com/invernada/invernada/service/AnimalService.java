package br.com.invernada.invernada.service;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Inseminacao;
import br.com.invernada.invernada.domain.Usuario;
import br.com.invernada.invernada.domain.Vacina;
import br.com.invernada.invernada.model.AnimalDTO;
import br.com.invernada.invernada.model.Sexo;
import br.com.invernada.invernada.repos.AnimalRepository;
import br.com.invernada.invernada.repos.InseminacaoRepository;
import br.com.invernada.invernada.repos.UsuarioRepository;
import br.com.invernada.invernada.repos.VacinaRepository;
import br.com.invernada.invernada.util.NotFoundException;
import br.com.invernada.invernada.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;
    private final VacinaRepository vacinaRepository;
    private final InseminacaoRepository inseminacaoRepository;

    public AnimalService(final AnimalRepository animalRepository,
            final UsuarioRepository usuarioRepository, final VacinaRepository vacinaRepository,
            final InseminacaoRepository inseminacaoRepository) {
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
        this.vacinaRepository = vacinaRepository;
        this.inseminacaoRepository = inseminacaoRepository;
    }

    public List<AnimalDTO> findAll() {
        final List<Animal> animals = animalRepository.findAll(Sort.by("id"));
        return animals.stream()
                .map(animal -> mapToDTO(animal, new AnimalDTO()))
                .toList();
    }

    public AnimalDTO get(final Long id) {
        return animalRepository.findById(id)
                .map(animal -> mapToDTO(animal, new AnimalDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AnimalDTO animalDTO) {
        final Animal animal = new Animal();
        mapToEntity(animalDTO, animal);
        return animalRepository.save(animal).getId();
    }

    public void update(final Long id, final AnimalDTO animalDTO) {
        final Animal animal = animalRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(animalDTO, animal);
        animalRepository.save(animal);
    }

    public void delete(final Long id) {
        animalRepository.deleteById(id);
    }

    private AnimalDTO mapToDTO(final Animal animal, final AnimalDTO animalDTO) {
        animalDTO.setId(animal.getId());
        animalDTO.setNome(animal.getNome());
        animalDTO.setRaca(animal.getRaca());
        animalDTO.setDataNascimento(animal.getDataNascimento());
        animalDTO.setNumeroIdentificacaoPai(animal.getNumeroIdentificacaoPai());
        animalDTO.setNumeroIdentificacaoMae(animal.getNumeroIdentificacaoMae());
        animalDTO.setSexo(animal.getSexo().name());
        animalDTO.setPeso(animal.getPeso());
        animalDTO.setNumeroIdentificacao(animal.getNumeroIdentificacao());
        animalDTO.setDataCadastro(animal.getDataCadastro());
        animalDTO.setDataAtualizacao(animal.getDataAtualizacao());
        animalDTO.setUsuarioIdCriacao(animal.getUsuarioIdCriacao() == null ? null : animal.getUsuarioIdCriacao().getId());
        return animalDTO;
    }

    private Animal mapToEntity(final AnimalDTO animalDTO, final Animal animal) {
        animal.setNome(animalDTO.getNome());
        animal.setRaca(animalDTO.getRaca());
        animal.setDataNascimento(animalDTO.getDataNascimento());
        animal.setNumeroIdentificacaoPai(animalDTO.getNumeroIdentificacaoPai());
        animal.setNumeroIdentificacaoMae(animalDTO.getNumeroIdentificacaoMae());
        animal.setSexo(Sexo.valueOf(animalDTO.getSexo()));
        animal.setPeso(animalDTO.getPeso());
        animal.setNumeroIdentificacao(animalDTO.getNumeroIdentificacao());
        animal.setDataCadastro(animalDTO.getDataCadastro());
        animal.setDataAtualizacao(animalDTO.getDataAtualizacao());
        final Usuario usuarioIdCriacao = animalDTO.getUsuarioIdCriacao() == null ? null : usuarioRepository.findById(animalDTO.getUsuarioIdCriacao())
                .orElseThrow(() -> new NotFoundException("usuarioIdCriacao not found"));
        animal.setUsuarioIdCriacao(usuarioIdCriacao);
        return animal;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Animal animal = animalRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Vacina animalIdVacina = vacinaRepository.findFirstByAnimalId(animal);
        if (animalIdVacina != null) {
            referencedWarning.setKey("animal.vacina.animalId.referenced");
            referencedWarning.addParam(animalIdVacina.getId());
            return referencedWarning;
        }
        final Inseminacao animalIdInseminacao = inseminacaoRepository.findFirstByAnimalId(animal);
        if (animalIdInseminacao != null) {
            referencedWarning.setKey("animal.inseminacao.animalId.referenced");
            referencedWarning.addParam(animalIdInseminacao.getId());
            return referencedWarning;
        }
        return null;
    }

}
