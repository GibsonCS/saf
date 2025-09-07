package br.org.sistemafesu.controller.web;

import br.org.sistemafesu.entity.User;
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
    private UserService userService;

    // private UserService userService;
    @GetMapping()
    public String callForm(Model model) {
        model.addAttribute("user", new User());

        return "form.html";
    }

    @PostMapping()
    public String cadastrarUsuario(User user) {
        userService.saveUser(user);

        return "redirect:/login";
    }
}
