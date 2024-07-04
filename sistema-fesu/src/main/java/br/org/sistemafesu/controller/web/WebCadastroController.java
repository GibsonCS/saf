package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.service.PessoaService;

@Controller
@RequestMapping("/cadastro")
public class WebCadastroController {

    @Autowired
    private PessoaService pessoaService;
//    private UserService userService;
    @GetMapping()
    public String callForm(Model model) {
        model.addAttribute("pessoa", new Pessoa());

        return "form.html";
    }

    @PostMapping()
    public String cadastrarUsuario(Pessoa pessoa) {
        pessoaService.save(pessoa);

        return "redirect:/login";
    }
}
