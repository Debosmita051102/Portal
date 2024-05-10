package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "mega_arch")
@NoArgsConstructor
@Async
public class MegaArchModel {

    public MegaArchModel(String gid1, String gid2, String gid3, String gid4, String gid5, String number1){
        this.gid1=gid1;
        this.gid2=gid2;
        this.gid3=gid3;
        this.gid4=gid4;
        this.gid5=gid5;
        this.number1 = number1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "selectedcivilevent" )
    private  String selectedcivilevent = "Mega Arch";

    private String gid1;

    @Column(nullable = true)
    private String gid2;

    @Column(nullable = true)
    private String gid3;

    @Column(nullable = true)
    private String gid4;

    @Column(nullable = true)
    private String gid5;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String number1;
    private String tid;
    private boolean played=false;
    private boolean paid=false;
}