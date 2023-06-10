package br.org.sistemafesu.controller;
import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "redirect:/cadastrar-sala";
    }


}
