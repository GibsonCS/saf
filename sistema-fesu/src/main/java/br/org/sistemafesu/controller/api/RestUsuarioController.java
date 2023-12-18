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

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Usuários", description = "Sua descrição")
@RequestMapping(path = "/api", produces = "application/json")
public class RestUsuarioController {
    // @Autowired
    // private UserRepository userRepository;
    private final UserService userService;

    public RestUsuarioController(UserService userService) {
        this.userService = userService;
    }

    // @ResponseStatus(HttpStatus.OK)
    // @GetMapping(name = "Usuários", path = "usuarios")
    // public ResponseEntity<List<User>> find() {
    //     List<User> usuarios = userRepository.findAll();

    //     if (usuarios.size() > 0)
    //         return new ResponseEntity<>(usuarios, HttpStatus.OK);

    //     return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    // }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuarios")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> users = userService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sucesso ao obter usuário"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrada", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("usuario/{id}")
    public ResponseEntity<User> find(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiResponses({ @ApiResponse(description = "Sucesso ao criar pessoa") })
    @PostMapping("usuario")
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um usuário.", tags = "Métodos de exclusão")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Sucesso ao remover usuário"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("usuario/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (userService.exists(id)) {
            userService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
