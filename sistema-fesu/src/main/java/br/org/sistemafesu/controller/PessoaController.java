package br.org.sistemafesu.controller;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @GetMapping()
    public String listar(Model model){
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        model.addAttribute("pessoa", new Pessoa());
        return "lista-pessoa";
    }
    @PostMapping()
    public String form(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return "redirect:/pessoas";
    }
}
