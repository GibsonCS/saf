package br.org.sistemafesu.controller;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping()
    public String listarEquipamentos(Model model){
        model.addAttribute("listaEquipamentos", equipamentoRepository.findAll());
        model.addAttribute("equipamento", new Equipamento());
        return "lista-equipamento";
    }

    @PostMapping()
    public String Form(Equipamento equipamento){
        equipamentoRepository.save(equipamento);
        return "redirect:/equipamentos";
    }
    @Override
    public String toString() {
        return "EquipamentoController{" +
                "equipamentoRepository=" + equipamentoRepository +
                '}';
    }
}
