package br.com.curriculopro.infra.security;
import br.com.curriculopro.application.usecases.CreateUser;
import br.com.curriculopro.domain.entities.User;
import br.com.curriculopro.domain.enums.PROVIDER;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtProvider;
    private final CreateUser createUser;

    public OAuth2SuccessHandler(JwtTokenProvider jwtProvider, CreateUser createUser) {
        this.jwtProvider = jwtProvider;
        this.createUser  = createUser;
    }

    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        assert user != null;

        String email = user.getAttribute("email");
        String name  = user.getAttribute("name");

        User newUser = new User(name, email, "USER", PROVIDER.GOOGLE);
        createUser.create(newUser);
        String token = jwtProvider.gerarToken(email, name, newUser.getId());

        String urlRedirect = "http://localhost:4200/token/" + token;
        response.sendRedirect(urlRedirect);
    }
}