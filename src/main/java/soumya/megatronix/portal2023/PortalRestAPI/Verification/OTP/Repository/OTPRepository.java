package soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Model.OTPModel;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTPModel, Long> {
    Optional<OTPModel> findByEmailAndOtp(String email, String otp);
    Optional<OTPModel> findByEmail(String email);
    Optional<OTPModel> findByPhoneNoAndOtp(String phoneNo, String otp);
    Optional<OTPModel> findByPhoneNo(String phoneNo);
}
