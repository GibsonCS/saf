package br.org.sistemafesu.controller;
import br.org.sistemafesu.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/salas")
public class SalaController {
    @Autowired
   private SalaRepository salaRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping()
    public String listarSala(Model model){
        model.addAttribute("listaSalas", salaRepository.findAll());
        model.addAttribute("sala", new Sala());
        return "lista-sala";
    }


    @PostMapping()
    public String postSala(Sala sala){
        salaRepository.save(sala);
        return "redirect:/salas";
    }

}
