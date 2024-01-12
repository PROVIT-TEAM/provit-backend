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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, String> request) throws MessagingException {
        /*
        * 요청 들어온 request uri를 받아서, 인라인 태그로 걸어주고 사용하면 될 듯
        * */
        log.info(request.get("email"));
        return emailService.sendEmail(request.get("email"));
    }

    @GetMapping("/emailAuth")
    public ResponseEntity<?> emailAuth(HttpServletRequest request) {
        log.info(request.toString());
        log.info("api 호출 성공");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
