//package br.org.sistemafesu.controller.api;
//
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.org.sistemafesu.entity.Equipamento;
//import br.org.sistemafesu.service.EquipamentoService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@RestController
//@Tag(name = "Equipamentos", description = "Sua descrição")
//@RequestMapping(path = "/api", produces = "application/json")
//public class RestEquipamentoController {
//    private final EquipamentoService equipamentoService;
//
//    public RestEquipamentoController(EquipamentoService equipamentoService) {
//        this.equipamentoService = equipamentoService;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("equipamentos")
//    public ResponseEntity<Iterable<Equipamento>> findAll() {
//        Iterable<Equipamento> equipamentos = equipamentoService.getAll();
//
//        return new ResponseEntity<>(equipamentos, HttpStatus.OK);
//    }
//
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Sucesso ao obter o equipamento"),
//        @ApiResponse(responseCode = "404", description = "Equipamento não encontrado", content = @Content(schema = @Schema(hidden = true)))
//    })
//    @GetMapping("equipamento/{id}")
//    public ResponseEntity<Equipamento> find(@PathVariable Long id) {
//        Optional<Equipamento> equipamento = equipamentoService.getById(id);
//
//        return equipamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @ApiResponses({ @ApiResponse(description = "Sucesso ao criar equipamento") })
//    @PostMapping("equipamento")
//    public ResponseEntity<Equipamento> create(@RequestBody Equipamento equipamento){
//        return new ResponseEntity<>(equipamentoService.save(equipamento), HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Exclui um equipamento.", tags = "Métodos de exclusão")
//    @ApiResponses({
//        @ApiResponse(responseCode = "204", description = "Sucesso ao remover locação"),
//        @ApiResponse(responseCode = "404", description = "Locação não encontrada", content = @Content(schema = @Schema(hidden = true)))
//    })
//    @DeleteMapping("equipamento/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        if (equipamentoService.exists(id)) {
//            equipamentoService.deleteById(id);
//
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//}
