package br.org.sistemafesu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String listarUsuarios(Model model){
        model.addAttribute("listaUsuarios", userRepository.findAll());
        model.addAttribute("user", new User());
        return "usuario";
    }

    @PostMapping()
    public String cadastrarUsuario(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/usuarios";
    }






}