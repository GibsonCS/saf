// package br.org.sistemafesu.controller.api;

// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

// import br.org.sistemafesu.entity.Pessoa;
// import br.org.sistemafesu.service.PessoaService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @Tag(name = "Pessoas", description = "Sua descrição")
// @RequestMapping(path = "/api", produces = "application/json")
// public class RestPessoaController {
//     private final PessoaService pessoaService;

//     public RestPessoaController(PessoaService pessoaService) {
//         this.pessoaService = pessoaService;
//     }

//     @ResponseStatus(HttpStatus.OK)
//     @GetMapping("pessoas")
//     public ResponseEntity<Iterable<Pessoa>> find() {
//         Iterable<Pessoa> pessoas = pessoaService.getAll();

//         return new ResponseEntity<>(pessoas, HttpStatus.OK);
//     }

//     @ApiResponses({
//         @ApiResponse(responseCode = "200", description = "Sucesso ao obter a pessoa"),
//         @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content(schema = @Schema(hidden = true)))
//     })
//     @GetMapping("pessoa/{id}")
//     public ResponseEntity<Pessoa> find(@PathVariable Long id) {
//         Optional<Pessoa> pessoa = pessoaService.getById(id);

//         return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @ApiResponses({ @ApiResponse(description = "Sucesso ao criar pessoa") })
//     @PostMapping("pessoa")
//     public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
//         return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
//     }

//     @Operation(summary = "aaaa", tags = "Métodos de exclusão")
//     @ApiResponses({
//         @ApiResponse(responseCode = "204", description = "Sucesso ao remover locação"),
//         @ApiResponse(responseCode = "404", description = "Locação não encontrada", content = @Content(schema = @Schema(hidden = true)))
//     })

//     @DeleteMapping("pessoa/{id}")
//     public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//         if (pessoaService.exists(id)) {
//             pessoaService.deleteWithTreatment(id);

//             return ResponseEntity.noContent().build();
//         }

//         return ResponseEntity.notFound().build();
//     }
// }
