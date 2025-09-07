package br.org.sistemafesu.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.service.LocacaoService;
import br.org.sistemafesu.service.SalaService;

@Controller
@RequestMapping("/salas")
public class WebSalaController {
    private final SalaService salaService;

    public WebSalaController(SalaService salaService, LocacaoService locacaoService) {
        this.salaService = salaService;

    }

    @GetMapping()
    public String listarSala(Model model) {
        model.addAttribute("listaSalas", salaService.getSortedSalas());
        model.addAttribute("sala", new Sala());

        return "lista-sala";
    }

    @PostMapping()
    public RedirectView postSala(Sala sala) {
        salaService.save(sala);

        return new RedirectView("/salas");
    }

    @DeleteMapping("{id}")
    public RedirectView deletarSaLa(@PathVariable("id") Long id) {
        salaService.deleteById(id);

        return new RedirectView("/salas");
    }
}
