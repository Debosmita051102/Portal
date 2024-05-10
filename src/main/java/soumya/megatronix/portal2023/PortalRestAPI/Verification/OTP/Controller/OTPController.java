package soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Model.OTPModel;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Service.OTPService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/megatronix/paridhi/user/")
public class OTPController {

    @Autowired
    private OTPService otpService;


    @Async
    @PostMapping("/profile/generate-otp")
    public CompletableFuture<ResponseEntity<?>> generateOtpforProfile (
            @RequestParam String email
    ) {

        CompletableFuture<String> otp = otpService.generateOTPForEmail(email);
        try {
            CompletableFuture<Boolean> emailSent = otpService.sendOTPByEmailforProfile(email, otp.get());

            boolean isEmailSent = emailSent.get();
            System.out.println(isEmailSent);
            if (isEmailSent) {
                return CompletableFuture.completedFuture(ResponseEntity.ok().body("OTP sent successfully"));
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Failed to send OTP"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.getMessage();
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(ex.getMessage()));
        }
    }
    @Async
    @PostMapping("/generate-otp")
    public CompletableFuture<ResponseEntity<?>> generateOtp (
            @RequestParam String name,
            @RequestParam String email
    ) {

        CompletableFuture<String> otp = otpService.generateOTPForEmail(email);
        try {
            CompletableFuture<Boolean> emailSent = otpService.sendOTPByEmail(email, otp.get(), name);

            boolean isEmailSent = emailSent.get();
            System.out.println(isEmailSent);
            if (isEmailSent) {
                return CompletableFuture.completedFuture(ResponseEntity.ok().body("OTP sent successfully"));
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Failed to send OTP"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.getMessage();
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(ex.getMessage()));
        }
    }

    @Async
    @PostMapping("/verify-otp")
    public CompletableFuture<ResponseEntity<?>> validateOtp (
            @RequestBody OTPModel otpModel
    ) {
        String email = otpModel.getEmail();
        String otp = otpModel.getOtp();

        System.out.println(email + " " + otp);

        CompletableFuture<Boolean> isValid = otpService.validateOTP(email, otp);

        try {
            System.out.println(isValid.get());
            if(isValid.get()) {
                return CompletableFuture.completedFuture(ResponseEntity.ok().body("OTP verified successfully"));
            } else {
                return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Invalid OTP"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.getMessage();
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(ex.getMessage()));
        }
    }
}
