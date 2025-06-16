package br.com.invernada.invernada.service;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Inseminacao;
import br.com.invernada.invernada.model.InseminacaoDTO;
import br.com.invernada.invernada.repos.AnimalRepository;
import br.com.invernada.invernada.repos.InseminacaoRepository;
import br.com.invernada.invernada.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InseminacaoService {

    private final InseminacaoRepository inseminacaoRepository;
    private final AnimalRepository animalRepository;

    public InseminacaoService(final InseminacaoRepository inseminacaoRepository,
            final AnimalRepository animalRepository) {
        this.inseminacaoRepository = inseminacaoRepository;
        this.animalRepository = animalRepository;
    }

    public List<InseminacaoDTO> findAll() {
        final List<Inseminacao> inseminacaos = inseminacaoRepository.findAll(Sort.by("id"));
        return inseminacaos.stream()
                .map(inseminacao -> mapToDTO(inseminacao, new InseminacaoDTO()))
                .toList();
    }

    public InseminacaoDTO get(final Long id) {
        return inseminacaoRepository.findById(id)
                .map(inseminacao -> mapToDTO(inseminacao, new InseminacaoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final InseminacaoDTO inseminacaoDTO) {
        final Inseminacao inseminacao = new Inseminacao();
        mapToEntity(inseminacaoDTO, inseminacao);
        return inseminacaoRepository.save(inseminacao).getId();
    }

    public void update(final Long id, final InseminacaoDTO inseminacaoDTO) {
        final Inseminacao inseminacao = inseminacaoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(inseminacaoDTO, inseminacao);
        inseminacaoRepository.save(inseminacao);
    }

    public void delete(final Long id) {
        inseminacaoRepository.deleteById(id);
    }

    private InseminacaoDTO mapToDTO(final Inseminacao inseminacao,
            final InseminacaoDTO inseminacaoDTO) {
        inseminacaoDTO.setId(inseminacao.getId());
        inseminacaoDTO.setDataCio(inseminacao.getDataCio());
        inseminacaoDTO.setDataInseminacao(inseminacao.getDataInseminacao());
        inseminacaoDTO.setCodigoSemen(inseminacao.getCodigoSemen());
        inseminacaoDTO.setObservacoes(inseminacao.getObservacoes());
        inseminacaoDTO.setAnimalId(inseminacao.getAnimalId() == null ? null : inseminacao.getAnimalId().getId());
        return inseminacaoDTO;
    }

    private Inseminacao mapToEntity(final InseminacaoDTO inseminacaoDTO,
            final Inseminacao inseminacao) {
        inseminacao.setDataCio(inseminacaoDTO.getDataCio());
        inseminacao.setDataInseminacao(inseminacaoDTO.getDataInseminacao());
        inseminacao.setCodigoSemen(inseminacaoDTO.getCodigoSemen());
        inseminacao.setObservacoes(inseminacaoDTO.getObservacoes());
        final Animal animalId = inseminacaoDTO.getAnimalId() == null ? null : animalRepository.findById(inseminacaoDTO.getAnimalId())
                .orElseThrow(() -> new NotFoundException("animalId not found"));
        inseminacao.setAnimalId(animalId);
        return inseminacao;
    }

}
