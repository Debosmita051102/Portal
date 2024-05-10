package soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Model.OTPModel;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Repository.OTPRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class OTPService {

    private static final int MAX_OTP_ATTEMPTS = 3;
    private static final int MAX_OTP_ATTEMPTS_WINDOW_MINUTES = 1;
    private static final int OTP_EXPIRY_MINUTES = 1;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private CacheManager cacheManager;

    @Async
    public CompletableFuture<String> generateOTPForEmail(String email) {

        Optional<OTPModel> existingOtpInfo = otpRepository.findByEmail(email);

        if(existingOtpInfo.isPresent()) {
            LocalDateTime presentTime = LocalDateTime.now();
            LocalDateTime lastGeneratedAt = existingOtpInfo.get().getGeneratedAt();

            if (getOtpAttempts(email) >= MAX_OTP_ATTEMPTS) {
                if (lastGeneratedAt.plusMinutes(MAX_OTP_ATTEMPTS_WINDOW_MINUTES).isBefore(presentTime)) {
                    resetOtpAttempts(email);
                } else {
                    throw new RuntimeException("Maximum OTP attempts reached. Please try again after " + (MAX_OTP_ATTEMPTS_WINDOW_MINUTES - (presentTime.getMinute() - lastGeneratedAt.getMinute())) + " minutes.");
                }
            }
        }

        String otp = null;

        if(existingOtpInfo.isPresent()) {
            if(isOtpExpired(existingOtpInfo.get().getGeneratedAt())) {
                otpRepository.delete(existingOtpInfo.get());
                otp = generateNewOTP(email);
            } else {
                existingOtpInfo.get().setGeneratedAt(LocalDateTime.now());
                otp = existingOtpInfo.get().getOtp();
            }
        } else {
            otp = generateNewOTP(email);
        }

        incrementOtpAttempts(email);
        return CompletableFuture.completedFuture(otp);
    }

    @Async
    public CompletableFuture<Boolean> sendOTPByEmail (String email, String otp, String name) {
        String subject = "OTP for Email verification";
        String message = "<html><body>" +
                "<h3>Hello " + name + ",</h3>" +
                "<p>" +
                "<br>" +
                "Your OTP for Email verification is: <strong>" + otp + "</strong><br>" +
                "<br>" +
                "This OTP is valid for 5 minutes only.<br>" +
                "<br>" +
                "Best Regards,<br>" +
                "<strong>Team Megatronix</strong>." +
                "</p>" +
                "</body></html>";

        return emailService.sendEmail(email, subject, message)
                .thenApplyAsync(result -> true)
                .exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return false;
                });
    }

    @Async
    public CompletableFuture<Boolean> sendOTPByEmailforProfile (String email, String otp) {
        String subject = "OTP for Email verification";
        String message = "<html><body>" +
                "<h3>Hello ,</h3>" +
                "<p>" +
                "<br>" +
                "Your OTP for Email verification is: <strong>" + otp + "</strong><br>" +
                "<br>" +
                "This OTP is valid for 5 minutes only.<br>" +
                "<br>" +
                "Best Regards,<br>" +
                "<strong>Team Megatronix</strong>." +
                "</p>" +
                "</body></html>";

        return emailService.sendEmail(email, subject, message)
                .thenApplyAsync(result -> true)
                .exceptionally(ex -> {
                    System.out.println(ex.getMessage());
                    return false;
                });
    }

    public CompletableFuture<Boolean> validateOTP (String email, String otp) {

        Optional<OTPModel> otpModel = otpRepository.findByEmailAndOtp(email, otp);

        if(otpModel.isPresent()) {
            if(isOtpExpired(otpModel.get().getGeneratedAt())) {
                otpRepository.delete(otpModel.get());
                return CompletableFuture.completedFuture(false);
            } else {
                otpRepository.delete(otpModel.get());
                return CompletableFuture.completedFuture(true);
            }
        } else {
            throw new RuntimeException("Invalid OTP. Please try again.");
        }
    }

    private void incrementOtpAttempts (String email) {
        Cache cache = cacheManager.getCache("otpAttempts");
        Integer attempts = cache.get(email, Integer.class);
        if(attempts == null) {
            attempts = 1;
        } else {
            attempts++;
        }
        cache.put(email, attempts);
    }

    private void resetOtpAttempts (String email) {
        Cache cache = cacheManager.getCache("otpAttempts");
        cache.evict(email);
    }

    private int getOtpAttempts (String email) {
        Cache cache = cacheManager.getCache("otpAttempts");
        Integer attempts = cache.get(email, Integer.class);
        return attempts == null ? 0 : attempts;
    }

    private String generateNewOTP(String email) {
        String otp = String.valueOf( (int) (Math.random() * 900_000 + 100_000) );
        OTPModel otpModel = new OTPModel();
        otpModel.setEmail(email);
        otpModel.setOtp(otp);
        otpModel.setGeneratedAt(LocalDateTime.now());
        otpRepository.save(otpModel);
        return otp;
    }

    private boolean isOtpExpired (LocalDateTime generatedAt) {
        return generatedAt.plusMinutes(OTP_EXPIRY_MINUTES).isBefore(LocalDateTime.now());
    }
}
