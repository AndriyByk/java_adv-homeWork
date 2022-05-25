package ua.project.java_advhomework.services.implementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.services.ITokenBuilderService;

@Service
public class TokenBuilderService implements ITokenBuilderService {
    @Override
    public String getToken(Authentication authResult) {
        return Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, "ukraine".getBytes())
                .compact();
    }
}
