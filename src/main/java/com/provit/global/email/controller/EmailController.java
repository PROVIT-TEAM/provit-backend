package com.provit.global.email.controller;

import com.provit.global.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, String> request) throws MessagingException {
        log.info(request.get("email"));
        emailService.sendEmail(request.get("email"));
        return new ResponseEntity<>("이메일 요청 성공", HttpStatus.OK);
    }

    @GetMapping("/emailVerify/{email}/{code}")
    public ResponseEntity<?> emailAuth(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("email") String email,
            @PathVariable("code") String code) {

        log.info(request.toString());
        if (emailService.verify(email, code)){
            return new ResponseEntity<>("이메일 인증 성공", HttpStatus.OK);
        }

        return new ResponseEntity<>("인증 실패",HttpStatus.BAD_REQUEST);
    }
}
