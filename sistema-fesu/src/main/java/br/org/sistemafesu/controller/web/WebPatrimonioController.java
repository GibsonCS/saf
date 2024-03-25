package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Patrimonio;
import br.org.sistemafesu.repository.PatrimonioRepository;

@Controller
@RequestMapping("/patrimonios")
public class WebPatrimonioController {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @GetMapping
    public String getPatrimonios(Model model){
        model.addAttribute("listaPatrimonios", patrimonioRepository.findAll());
        Patrimonio patrimonio = new Patrimonio();
        model.addAttribute("patrimonio", patrimonio);

        return "lista-patrimonio";
    }

}
