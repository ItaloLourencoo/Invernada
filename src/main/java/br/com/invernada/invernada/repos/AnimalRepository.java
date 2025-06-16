package br.com.invernada.invernada.repos;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findFirstByUsuarioIdCriacao(Usuario usuario);

    Optional<Animal> findByNumeroIdentificacaoLikeIgnoreCaseOrNomeLikeIgnoreCase(String numeroIdentificacao, String nome);

}
