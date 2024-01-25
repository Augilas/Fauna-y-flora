/*package com.example.semestral.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.semestral.Auth.JwtToken;
import com.example.semestral.Model.Perfil;
import com.example.semestral.Services.PerfilDB;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @PostMapping("/perfil")
    public ResponseEntity<Perfil> InsertarRegistro(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        ObjectMapper objectMapper = new ObjectMapper();
        Perfil perfil = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String valorCookie = cookie.getValue();
                    String[] split_string = valorCookie.split("\\.");
                    String payload = new String(decoder.decode(split_string[1]));
                    try {
                        JwtToken jwtToken = objectMapper.readValue(payload, JwtToken.class);
                        List<String> authorities = jwtToken.getAuthorities();
                        if (authorities != null && !authorities.isEmpty()) {
                            String authority = authorities.get(0);
                            perfil = new PerfilDB().BuscarUsuario(jwtToken.getSub(), authority);
                        } else {
                            System.out.println("No se encontraron authorities en el token.");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
        if (perfil != null) {
            return ResponseEntity.ok().body(perfil); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
*/
