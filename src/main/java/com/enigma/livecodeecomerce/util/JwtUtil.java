package com.enigma.livecodeecomerce.util;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
@Getter @Setter
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expire}")
    private Integer jwtExpire;

    public String generateToken(String role, String id) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(role)
                .setId(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpire))
                .signWith(SignatureAlgorithm.HS256, jwtSecret);

        return jwtBuilder.compact();
    }

    public boolean validateToken(String token) {
        try {
            token = token.split(" ")[0];
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }

    public Map<String , String> getClaims(String token) {
        Map<String, String> tokenPayload = new HashMap<>();
        try {
            token = token.split(" ")[0];
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

            tokenPayload.put("role", claims.getSubject());
            tokenPayload.put("id", claims.getId());
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }

        return tokenPayload;
    }
}
