package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@Table(name = "valorant_lan")
@NoArgsConstructor
@Async
public class ValorantLan {
    public ValorantLan(String teamnname, String gid1, String gid2, String gid3, String gid4, String gid5, String gid6, String number1){
        this.teamname = teamnname;
        this.gid1=gid1;
        this.gid2=gid2;
        this.gid3=gid3;
        this.gid4=gid4;
        this.gid5=gid5;
        this.gid6=gid6;
        this.number1 = number1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String teamname;

    @Column(name = "selectedgamingevent" )
    private  String selectedgamingevent = "valorant_lan";

    @NotNull
    private String gid1;
    @NotNull
    private String gid2;
    @NotNull
    private String gid3;
    @NotNull
    private String gid4;
    @NotNull
    private String gid5;

    @Column(nullable = true)
    private String gid6;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String number1;
    private String tid;
    private boolean played=false;
    private boolean paid=false;
}