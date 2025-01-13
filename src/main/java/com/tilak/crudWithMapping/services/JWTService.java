package com.tilak.crudWithMapping.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String secreatkey="";
    private final long accessTokenValidity = 1000 * 60*5; // 5 minutes
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7 days
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk= keyGen.generateKey();
            secreatkey=Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String username ,String email,String phoneNumber,String role,long validity,String type) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("Email",email);
        claims.put("Phone Number",phoneNumber);
        claims.put("Role",role);
        claims.put("Type",type);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+validity))
                .and()
                .signWith(getKey())
                .compact();


    }



    private SecretKey getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secreatkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



    public String generateAccessToken(String username, String email, String phoneNumber, String role) {
        return generateToken(username,email, phoneNumber,role,accessTokenValidity,"ACCESS");
    }

    public String generateRefreshToken(String username, String email, String phoneNumber, String role) {
        return generateToken(username,email, phoneNumber,role,refreshTokenValidity,"REFRESH");
    }
}
