package provit.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@SpringBootTest
public class SmtpTest {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String title, String text){
        SimpleMailMessage simpleMailMessage = createEmailForm(toEmail, title, text);
        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            System.out.print(toEmail+","+title+","+text);
        }
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

    @Test
    @Transactional
    public void test1(){
        /*Redis 안쓰면 인증번호 저장 어떻게 하지,,,session?*/
        String toEmail = "lwj6869@naver.com";
        String title = "SMTP 테스트";
        String text = "<div>PROVIT 회원 가입 인증 이메일 입니다.</div>"
                +"<br>"+createCode()+"</br>";

        sendEmail(toEmail, title, text);
    }
}
