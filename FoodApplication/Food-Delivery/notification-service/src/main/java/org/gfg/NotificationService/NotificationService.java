package org.gfg.NotificationService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfg.utils.CommonConstants;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = {CommonConstants.USER_CREATION_TOPIC}, groupId = "notification-group")
    public void sendNotification(String message) throws JsonProcessingException {
        JSONObject jsonObject  = objectMapper.readValue(message, JSONObject.class);
        String name =(String) jsonObject.get(CommonConstants.USER_CREATION_NAME);
        String email =(String) jsonObject.get(CommonConstants.USER_CREATION_EMAIL);
        String body = "Hello, " +name +"!\n\n"
                + "We want to welcome you on our platform, ${firstName}. "
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "Food Delivery Application";

        sendEmail(email, "WELCOME to the platform",body);
        System.out.println("name of the user is " +name);
    }
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
