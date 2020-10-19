package com.sendmail.correo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private SimpleMailMessage simpleMailMessage;

    public void sendMail(String de, String subject, String mensaje) {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(de);
            helper.setTo("illeniumreveri111@gmail.com");
            helper.setSubject(subject);

            helper.setText(mensaje);

            FileSystemResource file = new FileSystemResource("C:\\Users\\Carlos Orellana\\Music\\Deezloader Music\\Subtronics - String Theory\\02 - Clockwork.mp3");
            helper.addAttachment(file.getFilename(), file);

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        javaMailSender.send(message);
    }

    /*public void sendMail(String de, String asunto, String mensaje){
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(de);
        mail.setTo("illeniumreveri111@gmail.com");
        mail.setSubject(asunto);
        mail.setText(mensaje);

        javaMailSender.send(mail);

    }

    public String rutaArchivo = "//src//main//resource//archivo//";

    public void subirArchivo(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(rutaArchivo = multipartFile.getOriginalFilename());
            Files.write(path, bytes);

        }
    }*/
}
