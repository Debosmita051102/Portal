package soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/registration")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Async
    @GetMapping("/success")
    public CompletableFuture<ResponseEntity<String>> sendGidEmail (
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String gid
    ) {
        return emailService.sendRegistrationMail(email, gid, name).thenApply(success -> {
            if (success) {
                return ResponseEntity.ok().body("Email sent successfully");
            } else {
                return ResponseEntity.badRequest().body("Email not sent");
            }
        });
    }
}
