package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import br.org.sistemafesu.service.LocacaoService;
import br.org.sistemafesu.service.SalaService;

@RequestMapping("/reservas")
@Controller
public class WebReservasController {

    @Autowired
    private SalaService salaService;

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listaSalas", salaService.getSortedSalas());

        return "reservas";
    }

    @DeleteMapping("{id}")
    public RedirectView deletarReserva(@PathVariable("id") Long id) {
        locacaoService.deleteWithTreatment(id);

        return new RedirectView("/reservas");
    }
}
