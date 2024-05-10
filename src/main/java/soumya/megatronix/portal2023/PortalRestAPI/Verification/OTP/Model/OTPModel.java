package soumya.megatronix.portal2023.PortalRestAPI.Verification.OTP.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "otp_info")
@NoArgsConstructor
public class OTPModel {

    public  OTPModel(String email, String phoneNo, String otp, LocalDateTime generatedAt){
        this.email=email;
        this.phoneNo=phoneNo;
        this.otp=otp;
        this.generatedAt=generatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phoneNo;

    private String otp;
    private LocalDateTime generatedAt;
}
