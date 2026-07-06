package br.com.curriculopro.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private static final String SECRET = "12345678901234567890123456789012345678901234567890";

    @Test
    void deveGerarTokenComClaimsCorretos() {

        JwtTokenProvider provider = new JwtTokenProvider(SECRET);

        String token = provider.gerarToken(
                "usuario@teste.com",
                "João Silva",
                1L);

        assertNotNull(token);
        assertFalse(token.isBlank());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("usuario@teste.com", claims.getSubject());
        assertEquals("João Silva", claims.get("name"));
        assertEquals(1, ((Number) claims.get("id")).longValue());
        assertNotNull(claims.getExpiration());
    }
}