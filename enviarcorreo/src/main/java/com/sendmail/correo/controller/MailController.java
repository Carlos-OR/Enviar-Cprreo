package com.sendmail.correo.controller;

import com.sendmail.correo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/correo")
public class MailController {

    @Autowired
    private MailService mailService;

    public boolean result = false;

    @GetMapping({"/enviarcorreo", "/"})
    public ModelAndView enviarcorreo(){
        ModelAndView modelAndView = new ModelAndView("MAIL_VIEW");

        return modelAndView;
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("subject") String subject, @RequestParam("body") String body) {

        String message = body + "\nDATOS DE CONTACTO" + "\nNombre: " + name + "\nE-mail: " + mail;
        mailService.sendMail(name, subject, message);

        return "vista";
    }

    /*@PostMapping("/subirfoto")
    public ResponseEntity<?> subir(Model model, @RequestParam MultipartFile file) {

        if(file.isEmpty()){
            return new ResponseEntity<Object>("Seleccione un Archivo", HttpStatus.OK);
        }

        try {
            mailService.subirArchivo(file);

            model.addAttribute("resultado", 1);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("resultado", 0);
        }

        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
    }*/
}
