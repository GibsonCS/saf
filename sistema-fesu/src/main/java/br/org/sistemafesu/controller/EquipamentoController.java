package br.org.sistemafesu.controller;

import br.org.sistemafesu.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @RequestMapping (value = "/cadastrar-equipamentos", method = RequestMethod.GET)
    public String form(){
        return "cadastro-equipamento";
    }


}
