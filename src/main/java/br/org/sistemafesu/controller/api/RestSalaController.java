package br.org.sistemafesu.controller.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Salas", description = "Sua descrição")
@RequestMapping(path = "/api", produces = "application/json")
public class RestSalaController {
    private final SalaService salaService;

    public RestSalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("salas")
    public ResponseEntity<Iterable<Sala>> findAll() {
        Iterable<Sala> salas =  salaService.getAll();

        return new ResponseEntity<>(salas, HttpStatus.OK);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sucesso ao obter a sala"),
        @ApiResponse(responseCode = "404", description = "Sala não encontrada", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("sala/{id}")
    public ResponseEntity<Sala> find(@PathVariable Long id) {
        Optional<Sala> sala = salaService.getById(id);

        return sala.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiResponses({ @ApiResponse(description = "Sucesso ao criar pessoa") })
    @PostMapping("sala")
    public ResponseEntity<Sala> create(@RequestBody Sala user){
        return new ResponseEntity<>(salaService.save(user), HttpStatus.CREATED);
    }

    @Operation(summary = "aaaa", tags = "Métodos de exclusão")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Sucesso ao remover a sala"),
        @ApiResponse(responseCode = "404", description = "Sala não encontrada", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("sala/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (salaService.exists(id)) {
            salaService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
