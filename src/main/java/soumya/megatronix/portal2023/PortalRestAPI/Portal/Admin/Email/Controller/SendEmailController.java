package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto.EmailRequest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto.MultipleEmailRequest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Service.SendEmailService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("${admin.route}")
public class SendEmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @GetMapping("/sendEmail")
    @Async
    public CompletableFuture<ResponseEntity<String>> sendEmail (
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message
    ) {
        EmailRequest emailRequest = new EmailRequest(email, subject, message);

        return sendEmailService
                .sendEmail(emailRequest)
                .thenApply(success -> {
                    if (success)
                        return ResponseEntity.ok("Email sent successfully");
                    else
                        return ResponseEntity.badRequest().body("Email not sent");
                });
    }


    @GetMapping("/sendMultipleEmails")
    @Async
    public CompletableFuture<ResponseEntity<String>> sendMultipleEmails(
            @RequestParam("emails") List<String> emails,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message
    ) {
        MultipleEmailRequest multipleEmailRequest = new MultipleEmailRequest(emails, subject, message);

        return sendEmailService
                .sendEmail(multipleEmailRequest)
                .thenApply(success -> {
                    if (success)
                        return ResponseEntity.ok("Emails sent successfully");
                    else
                        return ResponseEntity.badRequest().body("Emails not sent");
                });
    }


}
