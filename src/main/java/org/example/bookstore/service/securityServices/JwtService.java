package org.example.bookstore.service.securityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.bookstore.model.MyUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
//    @Value("${secret}")
    private static final String secret="+AZdVekjEpe0J7JTfmSvsxcRy0E43g7QcMx/QSdkQSSNQavo7IK6ZTEa9KjLFJTFSaHthSveba8bF92ehJ/j6A==";
    private static final long validity = TimeUnit.MINUTES.toMillis(60);

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(validity)))
                .signWith(generateKey())
                .compact();
    }
    public SecretKey generateKey(){
        byte [] decodedKey= Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUserName(String jwt){
        Claims claims=getClaims(jwt);
        return claims.getSubject();
    }
    private Claims getClaims(String jwt){
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
    public boolean isTokenValid(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
    public long getExpirationTime(String token){
        return getClaims(token).getExpiration().getTime();
    }
}
