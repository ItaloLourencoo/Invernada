package br.com.invernada.invernada.rest;

import br.com.invernada.invernada.model.InseminacaoDTO;
import br.com.invernada.invernada.service.InseminacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/inseminacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class InseminacaoResource {

    private final InseminacaoService inseminacaoService;

    public InseminacaoResource(final InseminacaoService inseminacaoService) {
        this.inseminacaoService = inseminacaoService;
    }

    @GetMapping
    public ResponseEntity<List<InseminacaoDTO>> getAllInseminacaos() {
        return ResponseEntity.ok(inseminacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InseminacaoDTO> getInseminacao(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(inseminacaoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createInseminacao(
            @RequestBody @Valid final InseminacaoDTO inseminacaoDTO) {
        final Long createdId = inseminacaoService.create(inseminacaoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateInseminacao(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final InseminacaoDTO inseminacaoDTO) {
        inseminacaoService.update(id, inseminacaoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInseminacao(@PathVariable(name = "id") final Long id) {
        inseminacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
