package br.org.sistemafesu.controller;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocacaoController {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @RequestMapping("/reservar")
    public String rendezizarPaginaAlocacao(){
        return "alocacao";
    }

}
