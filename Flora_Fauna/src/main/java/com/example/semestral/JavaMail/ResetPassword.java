package com.example.semestral.JavaMail;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class ResetPassword {
    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetVerificationEmail(String url, String mail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Cambio de contraseña ";
        String senderName = "Flora y Fauna UTP";
        
        String mailContent = "<h1> Flora y Fauna UTP </h1>"+
                "<p>Una solicitud para cambiar tu contraseña de acceso al sitio web de Flora y Fauna UTP ha sido recibida.</p><br>"+"" +
                "<p>Por favor, presione el enlace a continuación para cambiar su contraseña.</p><br>"+
                "<p>Ingresa en <a href=\"" + url + "\">Cambio de contraseña</a></p><br>" +
                "<p>De no haber enviado esta solicitud, ignore este mensaje.</p>";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom("graduacionsupp@gmail.com", senderName);
        messageHelper.setTo(mail);
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        
        mailSender.send(message);
    }

}
