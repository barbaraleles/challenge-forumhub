package com.example.forumhub.service;

import com.example.forumhub.dto.DadosCadastroTopico;
import com.example.forumhub.dto.DadosDetalhamentoTopico;
import com.example.forumhub.model.Curso;
import com.example.forumhub.model.Topico;
import com.example.forumhub.model.Usuario;
import com.example.forumhub.repository.CursoRepository;
import com.example.forumhub.repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados, Usuario autor) {
        // Busca ou cria o curso
        Curso curso = cursoRepository.findByNome(dados.cursoNome())
                .orElseGet(() -> cursoRepository.save(new Curso(null, dados.cursoNome(), null)));

        // Cria novo tópico
        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        // Retorna DTO de detalhamento
        return new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                curso.getNome(),
                topico.getDataCriacao()
        );
    }

    public List<DadosDetalhamentoTopico> listar() {
        return topicoRepository.findAll()
                .stream()
                .map(t -> new DadosDetalhamentoTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensagem(),
                        t.getCurso().getNome(),
                        t.getDataCriacao()
                ))
                .toList();
    }

    public void excluir(Long id) {
        topicoRepository.deleteById(id);
    }
}
