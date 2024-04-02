package br.org.sistemafesu.controller.web;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import br.org.sistemafesu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class WebCadastroController {

    @Autowired
    private UserRepository userRepository;
//    private UserService userService;
    @GetMapping()
    public String callForm(Model model) {
        model.addAttribute("user", new User());

        return "form.html";
    }

    @PostMapping()
    public String cadastrarUsuario(User user) {
        userRepository.save(user);

        return "redirect:/login";
    }
}
