package br.org.sistemafesu.controller.web;


import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
public class TesteController  {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/drive")
    public void capturarMeuIP(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(ip);
        pessoaRepository.save(pessoa);
    }

}
