package br.org.sistemafesu.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.org.sistemafesu.entity.Curso;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.CursoRepository;
import br.org.sistemafesu.repository.PessoaRepository;

@Controller
public class WebCursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/cursos/novo")
    public String obterPaginaCadastroCurso(Model model) {
        model.addAttribute("curso", new Curso());

        return "formularioCurso";
    }

    @GetMapping("/cursos")
    public String obterListaCurso(Model model) {
        model.addAttribute("listaCursos", cursoRepository.findAll());

        return "listaCursos";
    }

    @PostMapping("/cursos")
    public String cadastrarCurso(Curso curso) {
        cursoRepository.save(curso);

        return "redirect:/cursos";
    }

    @GetMapping("/cursos/{id}")
    public String obterPaginaMatriculaCurso(@PathVariable("id") Long id, Model model) {
        model.addAttribute("curso", cursoRepository.findById(id).get());

        return "formularioMatricula";
    }

    @GetMapping("/cursos/confirmacao-inscricao")
    public String obterPaginaConfirmacaoInscricai() {

        return "confirmacaoInscricao";
    }

    @PutMapping("/cursos/{id}")
    // Recebe os parametros enviados pelo formárido de matricula.
    public String matricularPessoa(@PathVariable("id") Long id, @RequestParam("nome") String nome,
            @RequestParam("sobrenome") String sobreNome,
            @RequestParam("cpf") String cpf, @RequestParam("email") String email,
            @RequestParam("telefone") String telefone, RedirectAttributes redirectAttributes, Model model) {

        Curso curso = cursoRepository.findById(id).get();
        java.util.List<Pessoa> pessoas = curso.getPessoas();

        // Verificar se o cpf existe no curso
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                redirectAttributes.addFlashAttribute("error", "O CPF informado já está matriculado no curso.");
                return "redirect:/cursos/" + id;
            }
        }

        // Criar pessoa e matricular
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setSobrenome(sobreNome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        pessoa.getCursos().add(curso);
        pessoaRepository.save(pessoa);

        curso.getPessoas().add(pessoa);
        cursoRepository.save(curso);

        model.addAttribute("pessoa", pessoa);
        redirectAttributes.addFlashAttribute("success",
                " Confirmada sua inscrição para a(s) palestra(s) na Escola de Educação Financeira da Defensoria Pública. Informamos que a palestra será realizada na Av. Marechal Câmara, n.º 314, 4º andar, sala 2, Centro – RJ, na sede da Defensoria Pública. As modalidades online (formato webcast e videocast) serão disponibilizadas no Youtube da Defensoria Pública do Rio de Janeiro.\n"
                        + //
                        "Você pode consultar as palestras em que se encontra inscrito(a) clicando aqui 👇");

        return "redirect:/cursos/confirmacao-inscricao";
    }

    @GetMapping("/cursos/consulta-inscricao")
    public String obterPaginaConsultaInscrição() {

        return "formularioConsultaInscricao";
    }

    @PostMapping("/cursos/consulta-inscricao")
    public String consultarInscricao(@RequestParam("cpf") String cpf, Model model,
            RedirectAttributes redirectAttributes) {

        List<Pessoa> pessoas = pessoaRepository.findAll();

        // Verificar se o cpf existe no sistema
        if (pessoas.stream().filter(p -> p.getCpf().equalsIgnoreCase(cpf)).findFirst().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "O CPF informado não consta em nosso sistema.");

            return "redirect:/cursos/consulta-inscricao";
        }

        model.addAttribute("pessoa", pessoas.stream().filter(p -> p.getCpf().equalsIgnoreCase(cpf)).findFirst().get());

        return "cartaoConfirmacao";
    }
}
