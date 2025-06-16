package br.com.invernada.invernada.service;

import br.com.invernada.invernada.domain.Animal;
import br.com.invernada.invernada.domain.Usuario;
import br.com.invernada.invernada.model.LoginDTO;
import br.com.invernada.invernada.model.Perfil;
import br.com.invernada.invernada.model.UsuarioDTO;
import br.com.invernada.invernada.repos.AnimalRepository;
import br.com.invernada.invernada.repos.UsuarioRepository;
import br.com.invernada.invernada.util.NotFoundException;
import br.com.invernada.invernada.util.ReferencedWarning;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final AnimalRepository animalRepository) {
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Long id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setPerfil(usuario.getPerfil().name());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPerfil(Perfil.valueOf(usuarioDTO.getPerfil()));
        return usuario;
    }

    public boolean emailExists(final String email) {
        return usuarioRepository.existsByEmailIgnoreCase(email);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Animal usuarioIdCriacaoAnimal = animalRepository.findFirstByUsuarioIdCriacao(usuario);
        if (usuarioIdCriacaoAnimal != null) {
            referencedWarning.setKey("usuario.animal.usuarioIdCriacao.referenced");
            referencedWarning.addParam(usuarioIdCriacaoAnimal.getId());
            return referencedWarning;
        }
        return null;
    }

    public UsuarioDTO login(LoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailIgnoreCase(loginDTO.getEmail());
        if(usuario.isPresent() && usuario.get().getSenha().equals(loginDTO.getSenha())){
            return  mapToDTO(usuario.get(), new UsuarioDTO());
        }
        return null;
    }
}
