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
    private final LocacaoService locacaoService;

    public WebSalaController(SalaService salaService, LocacaoService locacaoService) {
        this.salaService = salaService;
        this.locacaoService = locacaoService;
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

    @DeleteMapping("/reserva/{id}")
    public RedirectView deletarReservaSala(@PathVariable Long id) {
        locacaoService.deleteWithTreatment(id);

        return new RedirectView("/salas");
    }

    @DeleteMapping("{id}")
    public RedirectView deletarSaLa(@PathVariable Long idSala) {
        salaService.deleteById(idSala);

        return new RedirectView("/salas");
    }
}
