package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.service.PessoaService;

@Controller
@RequestMapping("/pessoas")

public class WebPessoaController {
    // private final PessoaService pessoaService;

    // public WebPessoaController(PessoaService pessoaService) {
    //     this.pessoaService = pessoaService;
    // }

    @Autowired
    PessoaService pessoaService;

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("listaPessoas", pessoaService.getAll());
        model.addAttribute("pessoa", new Pessoa());

        return "lista-pessoa";
    }

    @PostMapping()
    public String cadastrarPessoa(Pessoa pessoa) {
        pessoaService.save(pessoa);

        return "redirect:/pessoas";
    }

    @DeleteMapping("{id}")
    public String deletarPessoa(@PathVariable("id") Long id){
        pessoaService.deleteWithTreatment(id);

        return "redirect:/pessoas";

    }

    @PutMapping("{id}")
    public String editarPessoa(@PathVariable("id") Long id, Pessoa pessoa) {
        pessoaService.updateTelefone(id, pessoa.getTelefone());

        return "redirect:/pessoas";
    }
}
