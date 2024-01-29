package com.example.semestral.Controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.semestral.Auth.JWTAuthtenticationConfig;
import com.example.semestral.JavaMail.ResetPassword;
import com.example.semestral.Model.Login;
import com.example.semestral.Model.Register;
import com.example.semestral.Model.Rpassword;
import com.example.semestral.Services.UserDB;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ResetPassword resetPassword;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("/register")
    public int InsertarRegistro(@RequestBody Register user) {
        return new UserDB().GuardarUsuario(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciar(@RequestBody Login log, HttpServletResponse response) {
        Boolean resultado = new UserDB().BuscarUsuario(log);
        int codtipo = new UserDB().BuscarTipo(log);
        String token;
        if (resultado && codtipo == 1) {
            token = jwtAuthtenticationConfig.getJWTToken(log.getUsername());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(30 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Usuario");
        } else if(resultado && codtipo == 2) {
            token = jwtAuthtenticationConfig.getProfe(log.getUsername());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(30 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Biologo");
        } else if (resultado && codtipo == 3) {
            token = jwtAuthtenticationConfig.getadmin(log.getUsername());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(30 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Administrador");
        }
            else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
    }

    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestParam String mail, HttpServletRequest servletRequest, HttpServletResponse response) {
        try {
            Boolean resultado = new UserDB().BuscarCorreo(mail);
            if (resultado) {
                Boolean resultado2 = new UserDB().BuscarTokenCorreo(mail);
                if (resultado2) {
                    return "Toke No Vencido";
                } else {
                    String passwordResetToken = UUID.randomUUID().toString();
                    new UserDB().Guardartoken(passwordResetToken, mail);
                    String url = passwordResetEmailLink(applicationUrl(servletRequest), passwordResetToken);
                    resetPassword.sendPasswordResetVerificationEmail(url, mail);
                    String token = jwtAuthtenticationConfig.gettoken(mail);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setMaxAge(24 * 60 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return null;
                }
            } else {
                return "Correo incorrecto";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "ERROR";
        }
    }
    //Agregar una verificacion que si hay ya un correo enviado no se pueda hacer nada
    @PostMapping("/reset-password")
    public boolean resetPassword(@RequestBody Rpassword rs) {
        Boolean tokenVerificationResult = new UserDB().BuscarToken(rs.getToken());
        return tokenVerificationResult;
    }

    @PostMapping("/reset-password-password")
    public void resetPassword2(@RequestBody Rpassword rs) {
        Boolean tokenVerificationResult = new UserDB().BuscarToken(rs.getToken());
        if (tokenVerificationResult) {
            new UserDB().CambioContra(rs.getToken(), rs.getPassword());
        }
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort();
    }

    private String passwordResetEmailLink(String applicationUrl, String token)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl + "/change_password?token="+token;
        return url;
    }
}
