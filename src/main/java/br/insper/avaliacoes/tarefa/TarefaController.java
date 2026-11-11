package br.insper.avaliacoes.tarefa;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private static final String NAMESPACE = "https://social-insper.com/";

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    //admin e userso tem q estarem autenticados
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@AuthenticationPrincipal Jwt jwt, @RequestBody TarefaRequest request) {
        String email = jwt.getClaimAsString(NAMESPACE + "email");
        return tarefaService.criar(request, email);
    }

    //admin e userso tem q estarem autenticados
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    //admin e userso tem q estarem autenticados
    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listar();
    }
}
