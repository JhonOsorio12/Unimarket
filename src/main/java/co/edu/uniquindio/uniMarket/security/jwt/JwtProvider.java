package co.edu.uniquindio.uniMarket.security.jwt;

import co.edu.uniquindio.uniMarket.security.modelo.UserDetailsImpl;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Authentication authentication){
        UserDetailsImpl userRole = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles =
                userRole.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(userRole.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date( new Date().getTime() + expiration * 1000))
                .signWith(getKey(secret))
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJwt(token).getBody();
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            e.printStackTrace();
        }
        return false;
    }

    private Key getKey(String secret){
        byte [] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }


}
