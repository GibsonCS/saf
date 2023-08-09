package br.org.sistemafesu.controller;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "cadastrar-equipamentos", method = RequestMethod.POST)
    public String Form(Equipamento equipamento){
        equipamentoRepository.save(equipamento);
        return "redirect:/equipamentos";
    }

    @RequestMapping("/equipamentos")
    public String listarEquipamentos(Model model){
        model.addAttribute("listaEquipamentos", equipamentoRepository.findAll());
        model.addAttribute("equipamento", new Equipamento());
        return "lista-equipamento";
    }

    @Override
    public String toString() {
        return "EquipamentoController{" +
                "equipamentoRepository=" + equipamentoRepository +
                '}';
    }
}
