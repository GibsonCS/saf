package br.org.sistemafesu.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.service.RoleService;
import br.org.sistemafesu.service.UserService;

@Controller
@RequestMapping("/usuarios")
public class WebUsuarioController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String listarUsuarios(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);

        model.addAttribute("listaUsuarios", userService.getAll());
        model.addAttribute("user", new User());
        return "usuario";
    }



    @PostMapping()
    public String cadastrarUsuario(User user) {
        userService.save(user);
        return "redirect:/usuarios";
    }

    @DeleteMapping("{id}")
    public String deletarUsuario(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/usuarios";
    }


    @GetMapping("{id}")
    public String getFormUpdateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));

        return "editar-usuario.html";
    }

    @PutMapping("/editar/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        userService.update(id, user);

        return "redirect:/usuarios";
    }
}

