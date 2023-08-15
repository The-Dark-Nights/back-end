package com.darknights.devigation.security.command.application.service;

import com.darknights.devigation.security.token.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CustomTokenService {

    @Value("${app.auth.tokenSecret}")
    private String JWT_SECRET;

    @Value("${app.auth.tokenExpirationMsec}")
    private int JWT_EXPIRATION_MS;

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .claim("role", userPrincipal.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getUserIdFromToken(String token) {

        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            //Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException ex) {
            System.out.println("잘못된 JWT 서명");
            throw new JwtException("잘못된 JWT 서명");
        } catch (ExpiredJwtException ex) {
            throw new JwtException("토큰 기한 만료 (유효 시간 : " + ex.getClaims().getExpiration() + ")");
        } catch (UnsupportedJwtException ex) {
            System.out.println("지원되지 않는 JWT 토큰");
            throw new JwtException("지원되지 않는 JWT 토큰");
        } catch (IllegalArgumentException ex) {
            System.out.println("잘못된 JWT 토큰");
            throw new JwtException("잘못된 JWT 토큰");
        }
    }

}
