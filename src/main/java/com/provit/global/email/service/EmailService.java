package com.provit.global.email.service;

import com.provit.domain.member.Member;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.global.email.Email;
import com.provit.global.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;
    private final EmailRepository emailRepository;
    private final SpringTemplateEngine templateEngine;

    public ResponseEntity<Map<String, Object>> sendEmail(String email) throws MessagingException, IOException {
        Member existMeber = memberRepository.findByEmail(email).orElse(null);
        if (existMeber != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "이미 사용 중인 이메일입니다."));
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        /**
         * 추후에 email, code값을 encoding 하는게 좋을것 같다.
         * */
        String verificationCode = createCode();
        String title = "PROVIT 회원가입";
        String htmlContent = generateHtmlContent("provit-email", verificationCode);
//        String text = "<div>PROVIT 회원 가입 인증 이메일 입니다.</div>"
//                + "<a href='http://localhost:3000/email/"+email+"/"+code+"' target='_blank' style='\n" +
//                "    display: inline-block;\n" +
//                "    padding: 10px 20px;\n" +
//                "    background-color: #ffffff;\n" +
//                "    color: #000000;\n" +
//                "    text-decoration: none;\n" +
//                "    border: 1px solid #000000;\n" +
//                "    border-radius: 5px;\n" +
//                "'>이메일 인증 확인</a>";

        helper.setTo(email);
        helper.setSubject(title);
        helper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
        
        //이메일 + code 저장
        emailRepository.save(Email.builder()
                .email(email)
                .code(verificationCode)
                .build());

        return ResponseEntity.ok().body(Map.of("message", "전송완료"));
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

    @Transactional
    public boolean verify(String email, String code){
        Email target = emailRepository.findByEmail(email).orElse(null);
        if (target.getCode().equals(code)){
            emailRepository.delete(target);
            return true;
        }

        return false;
    }
    private String generateHtmlContent(String templateName, String verificationCode) {
        Context context = new Context();
        context.setVariable("verificationCode", verificationCode);
        return templateEngine.process(templateName, context);
    }
}
