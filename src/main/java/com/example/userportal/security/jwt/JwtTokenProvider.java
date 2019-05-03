package com.example.userportal.security.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
  private static final String AUTHORITIES_KEY = "auth";
  @Value("${jwt.jwtSecret}")
  private String jwtSecret;

  @Value("${jwt.jwtExpirationInMs}")
  private long jwtExpirationInMs;

  @Value("${jwt.jwtExpirationInMsForRememberMe}")
  private long jwtExpirationInMsForRememberMe;

  public String createToken(Authentication authentication, boolean rememberMe) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

    long now = (new Date()).getTime();
    Date expiration;
    if (rememberMe) {
      expiration = new Date(now + this.jwtExpirationInMsForRememberMe);
    } else {
      expiration = new Date(now + this.jwtExpirationInMs);
    }

    return Jwts.builder()
            .setSubject(userPrincipal.getId().toString())
            .setIssuedAt(new Date())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .setExpiration(expiration)
            .compact();
  }

  public Integer getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return Integer.parseInt(claims.getSubject());
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }
}
