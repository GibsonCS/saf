package br.org.sistemafesu.controller;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @RequestMapping(value = "/cadastrar-pessoa", method = RequestMethod.GET)
    public String form(){
        return "formPessoa";
    }

    @RequestMapping(value = "/cadastrar-pessoa", method = RequestMethod.POST)
    public String form(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return "redirect:/cadastrar-pessoa";
    }
    @RequestMapping("/pessoas")
    public String listar(Model model){
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        return "listaPessoa";
    }
}
