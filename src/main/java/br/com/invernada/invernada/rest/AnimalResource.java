package br.com.invernada.invernada.rest;

import br.com.invernada.invernada.model.AnimalDTO;
import br.com.invernada.invernada.service.AnimalService;
import br.com.invernada.invernada.util.ReferencedException;
import br.com.invernada.invernada.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/animals", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimalResource {

    private final AnimalService animalService;

    public AnimalResource(final AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        return ResponseEntity.ok(animalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(animalService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAnimal(@RequestBody @Valid final AnimalDTO animalDTO) {
        final Long createdId = animalService.create(animalDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAnimal(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final AnimalDTO animalDTO) {
        animalService.update(id, animalDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAnimal(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = animalService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
