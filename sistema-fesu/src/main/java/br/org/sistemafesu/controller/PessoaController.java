package br.org.sistemafesu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import jakarta.validation.Valid;

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
    public String form(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("listaPessoas", pessoaRepository.findAll());
            model.addAttribute("pessoa", pessoa);

            return "lista-pessoa";
        }

        pessoaRepository.save(pessoa);

        return "redirect:/pessoas";
    }
}
