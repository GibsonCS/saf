package br.org.sistemafesu.controller;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @RequestMapping(value = "/cadastrar-pessoa", method = RequestMethod.GET)
    public String form(){
        return "form-pessoa";
    }

    @RequestMapping(value = "/cadastrar-pessoa", method = RequestMethod.POST)
    public String form(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return "redirect:/pessoas";
    }
    @RequestMapping("/pessoas")
    public String listar(Model model){
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        model.addAttribute("pessoa", new Pessoa());
        return "lista-pessoa";
    }
}
