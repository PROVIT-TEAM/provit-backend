package provit.backend.application.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String title, String text) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject(title);
        helper.setText(text, true);

        javaMailSender.send(mimeMessage);
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

    public void test1() throws MessagingException {
        /*Redis 안쓰면 인증번호 저장 어떻게 하지,,,session?*/
        String toEmail = "lwj6869@naver.com";
        String code = createCode();
        String title = "SMTP 테스트";
        String text = "<div>PROVIT 회원 가입 인증 이메일 입니다.</div>"
                + "<br>" + code + "</br>"
                + "<a href='http://localhost:9090/user/emailConfirm' target='_blank'>이메일 인증 확인</a>";

        sendEmail(toEmail, title, text);
    }
}