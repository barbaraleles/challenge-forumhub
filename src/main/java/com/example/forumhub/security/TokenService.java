package com.example.forumhub.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private final Key chaveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 1 dia de expiração
    private final long expiracao = 1000 * 60 * 60 * 24;

    public String gerarToken(String username) {
        Date agora = new Date();
        Date expiracaoToken = new Date(agora.getTime() + expiracao);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(agora)
                .setExpiration(expiracaoToken)
                .signWith(chaveSecreta)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(chaveSecreta)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
