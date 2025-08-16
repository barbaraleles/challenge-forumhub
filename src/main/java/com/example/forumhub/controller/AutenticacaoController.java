package com.example.forumhub.controller;

import com.example.forumhub.dto.LoginRequest;
import com.example.forumhub.dto.TokenResponse;
import com.example.forumhub.model.Usuario;
import com.example.forumhub.security.TokenService;
import com.example.forumhub.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager,
                                  UsuarioService usuarioService,
                                  TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody LoginRequest loginRequest) {
        try {
            // Cria token de autenticação do Spring Security
            var authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password()
            );

            authenticationManager.authenticate(authToken);

            // Busca usuário no banco
            Usuario usuario = usuarioService.buscarPorUsername(loginRequest.username());

            // Gera JWT
            String token = tokenService.gerarToken(usuario.getUsername());

            return ResponseEntity.ok(new TokenResponse(token));

        } catch (AuthenticationException e) {
            // Retorna 401 se falhar a autenticação
            return ResponseEntity.status(401).build();
        }
    }
}
