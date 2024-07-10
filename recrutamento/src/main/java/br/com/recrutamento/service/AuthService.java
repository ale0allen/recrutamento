package br.com.recrutamento.service;

import br.com.recrutamento.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Service
public class AuthService {

    @Value("${recrutamento.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Key key;

    @Autowired
    private JwtUtils jwtUtils;

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateJwtToken(Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }

    public boolean validateJwtToken(String authToken) {
        return jwtUtils.validateJwtToken(authToken);
    }
}
