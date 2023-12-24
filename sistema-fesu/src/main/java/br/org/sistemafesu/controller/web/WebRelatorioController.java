package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.service.LocacaoService;

@Controller
@RequestMapping({"/relatorio", "/relatorio/"})
public class WebRelatorioController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public String relatorio() {
        return "redirect:/";
    }

    @GetMapping({"/reservas"})
    public String reservas(Model model) {

        model.addAttribute("locacoes", locacaoService.findAllLocacaoDeleted());

        return "historico-reservas";
    }

}
