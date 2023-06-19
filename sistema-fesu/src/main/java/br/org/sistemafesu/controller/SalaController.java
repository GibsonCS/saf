package br.org.sistemafesu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;

@Controller
public class SalaController {
    @Autowired
   private SalaRepository salaRepository;

    @RequestMapping(value = "/cadastrar-sala", method = RequestMethod.GET)
    public String form(){
        return "sala";
    }

    @RequestMapping(value = "/cadastrar-sala", method = RequestMethod.POST)
    public String form(Sala sala){
        salaRepository.save(sala);
        return "redirect:/salas";
    }

    @RequestMapping("/salas")
    public String listarSala(Model model){
        model.addAttribute("listaSalas", salaRepository.findAll());
        model.addAttribute("sala", new Sala());
        return "lista-sala";
    }

}
