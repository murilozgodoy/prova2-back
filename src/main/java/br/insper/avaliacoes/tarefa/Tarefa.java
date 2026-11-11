package br.insper.avaliacoes.tarefa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tarefa")
@Data
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @Column(nullable = false)
    private String prioridade;

    @Column(nullable = false)
    private String emailCriador;
}
