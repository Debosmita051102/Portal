package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "Mrd")
@NoArgsConstructor
@Async
public class MrdModel {

    public MrdModel(String name, String college, String year, String department, String roll, String email, String phoneNumber){
        this.name=name;
        this.college=college;
        this.year=year;
        this.department=department;
        this.roll=roll;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String roll;

    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "phone Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String phoneNumber;

    private String gid;

    private  boolean paid=false;

    private boolean isEmailVerified=false;

}
