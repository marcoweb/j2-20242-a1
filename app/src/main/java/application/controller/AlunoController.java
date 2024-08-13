package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Aluno;
import application.repository.AlunoRepository;

@RestController
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping("/alunos")
    public Iterable<Aluno> list() {
        return alunoRepo.findAll();
    }

    @PostMapping("/alunos")
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }
}
