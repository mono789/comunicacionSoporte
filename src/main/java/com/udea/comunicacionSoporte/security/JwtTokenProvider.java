package com.udea.comunicacionSoporte.security;

import com.udea.comunicacionSoporte.entity.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class JwtTokenProvider {

    private final Key secretKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreoUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerCorreoDesdeToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        return parser.parseClaimsJws(token).getBody().getSubject();
    }

    public boolean esValido(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            parser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
