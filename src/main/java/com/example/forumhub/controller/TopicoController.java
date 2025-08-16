package com.example.forumhub.controller;

import com.example.forumhub.dto.DadosCadastroTopico;
import com.example.forumhub.dto.DadosDetalhamentoTopico;
import com.example.forumhub.model.Usuario;
import com.example.forumhub.service.TopicoService;
import com.example.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final UsuarioService usuarioService;

    @Autowired
    public TopicoController(TopicoService topicoService, UsuarioService usuarioService) {
        this.topicoService = topicoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<DadosDetalhamentoTopico> listar() {
        return topicoService.listar();
    }

    @PostMapping
    public DadosDetalhamentoTopico cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        // Mock do usuário logado, substitua pela autenticação real
        Usuario usuario = usuarioService.buscarPorUsername("usuarioExemplo");

        return topicoService.cadastrar(dados, usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        topicoService.excluir(id);
    }
}
