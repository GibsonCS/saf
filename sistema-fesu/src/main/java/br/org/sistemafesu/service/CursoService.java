package br.org.sistemafesu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Curso;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.CursoRepository;
import br.org.sistemafesu.repository.PessoaRepository;

@Service
public class CursoService {

    @Autowired
    private  CursoRepository cursoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa matricularPessoaNoCurso(Curso curso, String nome, String sobreNome, String cpf, String email, String telefone) {
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

        return pessoa;
    }

    public String verificaMatriculaNoCurso(Curso curso, String cpf) {
        for (Pessoa pessoa : curso.getPessoas()){
            if (pessoa.getCpf().equals(cpf)) {
                return "O CPF informado já está matriculado no curso.";
            }
        }
        return null;
    }
}
