package br.com.invernada.invernada.repos;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Inseminacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InseminacaoRepository extends JpaRepository<Inseminacao, Long> {

    Inseminacao findFirstByAnimalId(Animal animal);

}
