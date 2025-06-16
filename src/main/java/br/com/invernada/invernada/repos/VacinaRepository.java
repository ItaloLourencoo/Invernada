package br.com.invernada.invernada.repos;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    Vacina findFirstByAnimalId(Animal animal);

    List<Vacina> findAllByAnimalId(Animal animal);

}
