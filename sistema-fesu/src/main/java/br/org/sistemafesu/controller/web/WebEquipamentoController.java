package br.org.sistemafesu.controller.web;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.service.EquipamentoService;

@Controller
@RequestMapping("/equipamentos")
public class WebEquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping()
    public String listarEquipamentos(Model model) {
        model.addAttribute("listaEquipamentos", equipamentoService.obterEquipamentos()
            .stream()
            .sorted(Comparator .comparing(Equipamento::getNomeEquipamento)));
        model.addAttribute("equipamento", new Equipamento());

        return "lista-equipamento";
    }

    @PostMapping
    public String cadastrarEquipamento(Equipamento equipamento) {
        equipamentoService.criarEquipamento(equipamento);

        return "redirect:/equipamentos";
    }

    @DeleteMapping("{id}")
    public RedirectView deletarEquipamento(@PathVariable("id") Long id) {
        equipamentoService.deleteLocacao(id);
        equipamentoService.deletarEquipamentoPorId(id);

        return new RedirectView("/equipamentos");
    }
}
