package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.PessoaRepository;
import br.org.sistemafesu.repository.SalaRepository;

@Controller
@RequestMapping("/reservar")
public class WebLocacaoController {

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
        model.addAttribute("listaEquipamentos", equipamentoRepository.findAllByLocacaoIsNull());
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        model.addAttribute("locacao", new Locacao());

        return "alocacao";
    }

    @PostMapping()
    public RedirectView insertLocacao(@ModelAttribute("locacao") Locacao locacao,
            @RequestParam(required = false) Long equipamentoId) {
        if (locacao != null) {
            locacaoRepository.save(locacao);
        }

        if (equipamentoId != null) {
            equipamentoRepository.findById(equipamentoId).ifPresent(equipamento -> {
                equipamento.setPessoa(locacao.getPessoa());
                equipamento.setLocated(true);
                equipamento.setLocacao(locacao);
                equipamentoRepository.save(equipamento);
            });
        }

        return new RedirectView("/salas");
    }
}
