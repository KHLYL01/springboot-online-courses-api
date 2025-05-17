package com.example.alphaapi.service.impl;

import com.example.alphaapi.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {


//    private final JavaMailSender javaMailSender;
//
////    @Value("${spring.mail.username}")
////    private String fromEmailId;
//
    @Override
    public void sendMail(String receiver, String body, String subject) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("Alpha");
//        simpleMailMessage.setTo(receiver);
//        simpleMailMessage.setText(body);
//        simpleMailMessage.setSubject(subject);
//
//        javaMailSender.send(simpleMailMessage);
    }
}
