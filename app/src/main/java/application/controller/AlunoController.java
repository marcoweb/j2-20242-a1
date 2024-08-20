package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import application.model.Aluno;
import application.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping
    public Iterable<Aluno> list() {
        return alunoRepo.findAll();
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    @GetMapping("/{id}")
    public Aluno details(@PathVariable long id) {
        Optional<Aluno> resultado = alunoRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno Não Encontrado"
            );
        }
        return resultado.get();
    }

    @PutMapping("/{id}")
    public Aluno put(
        @PathVariable long id,
        @RequestBody Aluno novosDados) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno Não Encontrado"
            );
        }

        if(novosDados.getNome().isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Nome de Aluno Inválido"
            );
        }

        resultado.get().setNome(novosDados.getNome());

        return alunoRepo.save(resultado.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!alunoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno Não Encontrado"
            );
        }

        alunoRepo.deleteById(id);
    }
}
