package ua.com.owu.spring_rest_part1.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.owu.spring_rest_part1.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:application.properties")
@AllArgsConstructor
public class EmailService {

    private JavaMailSender javaMailSender;
    private Environment env;

    public void send(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessage.setFrom(new InternetAddress("vladyslav.bahlay@gmail.com"));
            helper.setTo(user.getEmail());
            helper.setText(
                    "<h1>Let's learn Java together right now!!!</h1> " +
                            "<h2>Have friends who study sociology in UKU and want to end their lives???" +
                            "<h2>We will teach you how to write clean code to spam their emails from your PC so it may entirely change their lives<h2>" +
                            "First lesson is free:) <br>" +
                            "<a href='https://owu.com.ua'>Visit our courses</a>",
                    true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

}
