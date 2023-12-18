package br.org.sistemafesu.controller.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.service.LocacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Locações", description = "Sua descrição")
@RequestMapping(path = "/api", produces = "application/json")
public class RestLocacaoController {
    private final LocacaoService locacaoService;

    public RestLocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("locacoes")
    public ResponseEntity<Iterable<Locacao>> findAll() {
        Iterable<Locacao> locacaos = locacaoService.getAll();

        return new ResponseEntity<>(locacaos, HttpStatus.OK);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sucesso ao obter a locação"),
        @ApiResponse(responseCode = "404", description = "Locação não encontrada", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("locacao/{id}")
    public ResponseEntity<Locacao> find(@PathVariable Long id) {
        Optional<Locacao> locacao = locacaoService.getById(id);

        return locacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Exclui a locação mantendo os equipamentos.", tags = "Métodos de exclusão")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Sucesso ao remover locação"),
        @ApiResponse(responseCode = "404", description = "Locação não encontrada", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("locacao/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (locacaoService.exists(id)) {
            locacaoService.deleteWithTreatment(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
