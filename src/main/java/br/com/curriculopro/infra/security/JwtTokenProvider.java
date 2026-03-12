package br.com.curriculopro.infra.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {
    private final String SECRET = "minha-chave-super-secreta-com-32-caracteres-minimo";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String gerarToken(String username, String name, Long id) {
        long EXPIRATION = 28800000;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .claim("name", name)
                .claim("id", id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extrairUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}