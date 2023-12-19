package br.org.sistemafesu.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("listaSalas", salaService.getAll());
        // model.addAttribute("equipamentos", )
        model.addAttribute("sala", new Sala());

        return "lista-sala";
    }

    @PostMapping()
    public String postSala(Sala sala) {
        salaService.save(sala);

        return "redirect:/salas";
    }

    @DeleteMapping("/reserva/{id}")
    public String deletarReservaSala(@PathVariable("id") Long id) {
        locacaoService.deleteWithTreatment(id);

        return "redirect:/salas";
    }

    @DeleteMapping("{id}")
    public String deletarSaLa(@PathVariable("id") Long idSala){
        salaService.deleteById(idSala);
        return "redirect:/salas";
    }
}
