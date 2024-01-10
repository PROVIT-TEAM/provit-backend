package com.provit.global.email.controller;

import com.provit.global.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public void sendEmail(HttpServletRequest request) throws MessagingException {
        log.info(request.getParameter("email"));
        emailService.test1(request.getParameter("email"));
    }

    @GetMapping("/emailAuth")
    public ResponseEntity<?> emailAuth(HttpServletRequest request) {
        log.info(request.toString());
        log.info("api 호출 성공");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
