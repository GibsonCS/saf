package br.org.sistemafesu.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.service.EquipamentoService;

@Controller
@RequestMapping("/equipamentos")
public class WebEquipamentoController {
    private final EquipamentoService equipamentoService;

    public WebEquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping()
    public String listarEquipamentos(Model model){
        model.addAttribute("listaEquipamentos", equipamentoService.getAll());
        model.addAttribute("equipamento", new Equipamento());
        return "lista-equipamento";
    }

    @PostMapping()
    public String Form(Equipamento equipamento){
        equipamentoService.save(equipamento);
        return "redirect:/equipamentos";
    }

    @Override
    public String toString() {
        return "EquipamentoController{" +
                "equipamentoRepository=" + equipamentoService +
                '}';
    }
}
