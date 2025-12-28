package in.learn.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridEmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    public void sendOtpEmail(String toEmail, String otp) {

        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        String subject = "LearnSphere - Password Reset OTP";

        Content content = new Content(
                "text/plain",
                "Your OTP is: " + otp + "\n\nValid for 10 minutes.\n\nLearnSphere Team"
        );

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println("SendGrid Status: " + response.getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
