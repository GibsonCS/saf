package br.org.sistemafesu.controller.web;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class WebCadastroController {

    @GetMapping()
    public String callForm(Model model){
        model.addAttribute("user", new User());

        return "form.html";
    }


}
