package br.ufrn.isaackennedy.authserver.security;

import br.ufrn.isaackennedy.authserver.dto.CredentialsDTO;
import br.ufrn.isaackennedy.authserver.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends CPFAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTUtil util;
    private PersonRepository repository;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil util,
                                   PersonRepository repository) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.util = util;
        this.repository = repository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialsDTO credenciais = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            CPFAuthenticationToken authenticationToken =
                    new CPFAuthenticationToken(credenciais.getCpf(), credenciais.getCpf(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String cpf = (String) authResult.getPrincipal();
        String name = repository.findByCpf(cpf).getName();
        String token = util.generateToken(cpf);
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write("{\"token\": \"Bearer " + token + "\", \"nome\": \""+ name +"\", \"cpf\": \""
                + Hashing.sha256().hashString(cpf, StandardCharsets.UTF_8) + "\"}");
    }

    private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"CPF inválido\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
