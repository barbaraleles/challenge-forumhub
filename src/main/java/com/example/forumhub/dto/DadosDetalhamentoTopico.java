package com.example.forumhub.dto;

import com.example.forumhub.model.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, String curso,
                                      LocalDateTime dataCriacao) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso().getNome(), topico.getDataCriacao());
    }
}
