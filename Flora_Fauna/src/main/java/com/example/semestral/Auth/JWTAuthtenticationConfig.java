package com.example.semestral.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.semestral.Auth.Constans.*;

@Configuration
public class JWTAuthtenticationConfig {

        public String getJWTToken(String username) {
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                                .commaSeparatedStringToAuthorityList("Usuario");

                String token = Jwts
                                .builder()
                                .setId("FloraFaunaUTP")
                                .setSubject(username)
                                .claim("authorities",
                                                grantedAuthorities.stream()
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

                return token;
        }

        public String getProfe(String username) {
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                                .commaSeparatedStringToAuthorityList("Biologo");

                String token = Jwts
                                .builder()
                                .setId("FloraFaunaUTP")
                                .setSubject(username)
                                .claim("authorities",
                                                grantedAuthorities.stream()
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

                return token;
        }

        public String getadmin(String username) {
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                                .commaSeparatedStringToAuthorityList("Administrador");

                String token = Jwts
                                .builder()
                                .setId("FloraFaunaUTP")
                                .setSubject(username)
                                .claim("authorities",
                                                grantedAuthorities.stream()
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

                return token;
        }

        public String gettoken(String username) {
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                                .commaSeparatedStringToAuthorityList("Token");

                String token = Jwts
                                .builder()
                                .setId("FloraFaunaUTP")
                                .setSubject(username)
                                .claim("authorities",
                                                grantedAuthorities.stream()
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                                .signWith(getSigningKey(SUPER_SECRET_KEY), SignatureAlgorithm.HS512).compact();

                return token;
        }
}
