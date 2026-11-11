package br.insper.avaliacoes.tarefa;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criar(TarefaRequest request, String emailCriador) {
        if (emailCriador == null || emailCriador.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token sem o email do usuario");
        }
        if (request.titulo() == null || request.titulo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Titulo eh obrigatorio");
        }
        if (request.prioridade() == null || request.prioridade().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prioridade eh obrigatoria");
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setEmailCriador(emailCriador);
        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setPrioridade(request.prioridade());
        return tarefaRepository.save(tarefa);
    }

    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa nao encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> listar() {return tarefaRepository.findAll();}

}
