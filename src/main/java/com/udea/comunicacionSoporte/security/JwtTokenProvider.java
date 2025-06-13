package com.udea.comunicacionSoporte.security;

import com.udea.comunicacionSoporte.entity.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final byte[] secretKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        this.secretKey = Base64.getDecoder().decode(secret);
    }

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreoUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String obtenerCorreoDesdeToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean esValido(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            parser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
