package br.eti.gadelha.nutrition.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTGenerator {

    final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        Date expireDate = new Date(new Date().getTime() + SecurityConstants.JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
    }
    public String getUsernameFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        System.out.println("validateToken");
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("validateToken, exception: " + ex);
            return false;
        }
    }
}