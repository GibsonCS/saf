package br.org.sistemafesu.controller;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.PessoaRepository;
import br.org.sistemafesu.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reservar")
public class LocacaoController {


    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping()
    public String listarItens(Model model) {
        model.addAttribute("listaSalas", salaRepository.findAll());
        model.addAttribute("listaEquipamentos", equipamentoRepository.findAll());
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        model.addAttribute("locacao", new Locacao());
        return "alocacao";
    }

    @PostMapping()
    public String insertLocacao(@ModelAttribute("locacao") Locacao locacao, Long equipamentoId){
        locacaoRepository.save(locacao);

        equipamentoRepository
        .findById(equipamentoId)
        .ifPresent(equipamento -> {
            equipamento.setLocacao(locacao);

            equipamentoRepository.save(equipamento);
        });

        return "redirect:/reservar";
    }
}
