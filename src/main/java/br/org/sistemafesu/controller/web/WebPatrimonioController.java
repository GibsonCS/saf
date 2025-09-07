package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Patrimonio;
import br.org.sistemafesu.repository.PatrimonioRepository;

@Controller
@RequestMapping("/patrimonios")
public class WebPatrimonioController {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @GetMapping
    public String getPatrimonios(Model model) {
        model.addAttribute("listaPatrimonios", patrimonioRepository.findAll());
        model.addAttribute("patrimonio", new Patrimonio());

        return "lista-patrimonio";
    }

    @PostMapping
    public String cadatrarPatrimonio (Patrimonio patrimonio) {
        patrimonioRepository.save(patrimonio);

        return "redirect:/patrimonios";
    }
    //@DeleteMapping("/{id}") id na url
    @DeleteMapping("{id}")
    //@PathVariable pega o id na URL
    public String deletarPatrimonio (@PathVariable("id") Long id, Model model) {
       if (patrimonioRepository.existsById(id)) {
        patrimonioRepository.deleteById(id);

        return "redirect:/patrimonios";
       }else {
           model.addAttribute("message", "Patrimonio não encontrado!");

           return "redirect:/patrimonios";
        }
    }
}
