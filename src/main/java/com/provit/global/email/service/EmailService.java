package com.provit.global.email.service;

import com.provit.domain.member.Member;
import com.provit.domain.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;

    public ResponseEntity<Map<String, Object>> sendEmail(String email) throws MessagingException {
        Member existMeber = memberRepository.findByUseraccount(email).orElse(null);
        if (existMeber != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "이미 사용 중인 이메일입니다."));
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String code = createCode();
        String title = "SMTP 테스트";
        String text = "<div>PROVIT 회원 가입 인증 이메일 입니다.</div>"
                + "<p>인증번호: " + code + "</p>"
                + "<a href='http://localhost:8080/emailAuth' target='_blank' style='\n" +
                "    display: inline-block;\n" +
                "    padding: 10px 20px;\n" +
                "    background-color: #ffffff;\n" +
                "    color: #000000;\n" +
                "    text-decoration: none;\n" +
                "    border: 1px solid #000000;\n" +
                "    border-radius: 5px;\n" +
                "'>이메일 인증 확인</a>";

        helper.setTo(email);
        helper.setSubject(title);
        helper.setText(text, true);
        javaMailSender.send(mimeMessage);

        return ResponseEntity.ok().body(Map.of("message", "전송완료"));
    }
    public SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
    public String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            return "MemberService.createCode() exception occur";
        }
    }
}