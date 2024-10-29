package javaweb.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
//    @Autowired
//    private MyUserRepository userRepository;

    public String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    @Async
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("khaai142003@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Mã OTP của bạn");
        message.setText("Mã OTP của bạn là: " + otp);
        javaMailSender.send(message);
    }
}