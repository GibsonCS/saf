package br.org.sistemafesu.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.service.RoleService;
import br.org.sistemafesu.service.UserService;

@Controller
@RequestMapping("/usuarios")
public class WebUsuarioController {
    private final UserService userService;

    @Autowired
    private RoleService roleService;

    public WebUsuarioController(UserService userService) {
        this.userService = userService;
    }

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
    public String deletarUsuario (@PathVariable("id")Long id) {
        userService.deleteById(id);
        return "redirect:/usuarios";
    }

    @PutMapping()
    public String atualizarSenha(User user){
        // System.out.println('-'*30 + user.getName());
        userService.save(user);
        // if (userRepository.findById(user.getId()).isPresent()){
        //     user.setPassword(encoder.encode(user.getPassword()));
        //     userRepository.save(user);
        // }

        return "redirect:/usuarios";
    }

}