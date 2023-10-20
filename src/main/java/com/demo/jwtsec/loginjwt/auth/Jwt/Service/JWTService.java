package com.demo.jwtsec.loginjwt.auth.Jwt.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails userDetails) {
        Map<String, Object> extraClaim = new HashMap<>();
        extraClaim.put("role" , userDetails.getAuthorities().toString());
        return getToken(extraClaim, userDetails); //Hashmap se usa para pares de clave:valor; Los vamos a utilizar para los claims
    }

    private String getToken(Map<String, Object> extraClaim, UserDetails userDetails) {
        return Jwts
                .builder()
                .claim("authorities",
                        extraClaim.get("role"))
                .setClaims(extraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))//Suma 1 dias;
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String getUsernameFromToken(String token) {

        return
                getClaim(token, Claims::getSubject); //En subject vamos a tener alojado el username;
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Obtener todos los claims del token;
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Metodo de tipo generico, para recuperar los claims;
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Metodo de tipo DATE para fecha expiracion;
    private Date getExpiration(String token){

        return getClaim(token, Claims::getExpiration);
    }

    //Comprueba si el token ya expiro, obteniendo la fecha de expiracion y comparandola con la fecha actual;
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
