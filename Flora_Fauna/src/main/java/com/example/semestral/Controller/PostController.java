package com.example.semestral.Controller;

import com.example.semestral.Model.Comentario;
import com.example.semestral.Model.Post;
import com.example.semestral.Services.PostDB;
import com.example.semestral.Services.UserDB;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.semestral.Auth.Constans.SUPER_SECRET_KEY;
import static com.example.semestral.Auth.Constans.getSigningKey;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {
    @PostMapping("/post")
    public int InsertarRegistro(@RequestBody Post post, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse(null);
        if (token != null) {
            Claims claims = Jwts.parser()
                        .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                        .parseClaimsJws(token)
                        .getBody();
            String sub = claims.getSubject();
            return new PostDB().GuardarPost(post, sub);
        } else {
            return 0;
        }
    }

    @PostMapping("/coment")
    public int InsertarComentario(@RequestBody Comentario coment, HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse(null);
        if (token != null) {
            Claims claims = Jwts.parser()
                        .setSigningKey(getSigningKey(SUPER_SECRET_KEY))
                        .parseClaimsJws(token)
                        .getBody();
            String sub = claims.getSubject();
            return new PostDB().GuardarComentario(coment, sub);
        } else {
            return 0;
        }
    }
}
