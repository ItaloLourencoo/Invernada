package br.com.invernada.invernada.rest;

import br.com.invernada.invernada.model.VacinaDTO;
import br.com.invernada.invernada.service.VacinaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/vacinas", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacinaResource {

    private final VacinaService vacinaService;

    public VacinaResource(final VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping
    public ResponseEntity<List<VacinaDTO>> getAllVacinas() {
        return ResponseEntity.ok(vacinaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinaDTO> getVacina(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(vacinaService.get(id));
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<List<VacinaDTO>> getVacinasAnimal(@PathVariable(name = "id") final String idOuNome) {
        return ResponseEntity.ok(vacinaService.listarPorIdentificadorAnimal(idOuNome));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVacina(@RequestBody @Valid final VacinaDTO vacinaDTO) {
        final Long createdId = vacinaService.create(vacinaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateVacina(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final VacinaDTO vacinaDTO) {
        vacinaService.update(id, vacinaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVacina(@PathVariable(name = "id") final Long id) {
        vacinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
