package br.org.sistemafesu.controller;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class LocacaoController {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private SalaRepository salaRepository;



    @RequestMapping(value = "/reservar", method = RequestMethod.POST)
    public String insetLocacao(Locacao locacao){
        locacaoRepository.save(locacao);
        return "redirect:/reservar";
    }

    /*public  String insertSala(long idSala){
       Sala sala = salaRepository.findById(idSala);
    }*/


    @RequestMapping(value = "/reservar", method = RequestMethod.GET)
    public String listarSalas(Model model){
        model.addAttribute("listaSalas", salaRepository.findAll());
        return "alocacao";
    }



}
