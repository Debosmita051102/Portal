package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto.EmailRequest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto.MultipleEmailRequest;

import java.util.concurrent.CompletableFuture;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public CompletableFuture<Boolean> sendEmail(EmailRequest emailRequest) {
        return sendEmailInternal(emailRequest.getEmail(), emailRequest.getSubject(), emailRequest.getMessage());
    }

    @Async
    public CompletableFuture<Boolean> sendEmail(MultipleEmailRequest multipleEmailRequest) {
        String[] emails = multipleEmailRequest.getEmails().toArray(new String[0]);
        return sendEmailInternal(emails, multipleEmailRequest.getSubject(), multipleEmailRequest.getMessage());
    }


    @Async
    public CompletableFuture<Boolean> sendEmailInternal(String to, String subject, String body) {
        return sendEmailInternal(new String[]{to}, subject, body);
    }

    @Async
    public CompletableFuture<Boolean> sendEmailInternal(String[] to, String subject, String body) {

        for (String email : to) {
            sendEmail(email, subject, body);
        }

        return CompletableFuture.completedFuture(true)
                .exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return false;
                });
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(body);
//
//        System.out.println("Sending email to: " + to + ", Subject: " + subject + ", Body: " + body);
//
//        javaMailSender.send(mailMessage);
    }

}
