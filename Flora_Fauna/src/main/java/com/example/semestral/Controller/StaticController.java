package com.example.semestral.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.semestral.Model.Rpassword;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class StaticController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/register")
    public String Register() {
        return "Registro.html";
    }

    @GetMapping("/login")
    public String login() {
        return "Login.html";
    }

    @GetMapping("/reset_password")
    public String Contrasena() {
        return "PasswordReset.html";
    }

    @GetMapping("/change_password")
    public String ContrasenaCambio(@RequestParam(required = false) String token, HttpServletRequest request) {
        if (token != null) {
            String url = "http://"+ request.getServerName() + ":" + request.getServerPort() +"/auth/reset-password";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
    
            Rpassword rp = new Rpassword();
            rp.setToken(token);
            HttpEntity<Rpassword> rq = new HttpEntity<>(rp, headers);
            ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, rq, Boolean.class);
            boolean resultado = responseEntity.getBody();
            if (resultado) {
                return "PasswordResetConfirm.html";
            } else {
                return "redirect:login";
            }
        } else {
            return "redirect:login";
        }
    }
}
