package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Async
@Getter
@Setter
@Table(name = "profile")
@NoArgsConstructor
public class ProfileModel {

    public ProfileModel (
            String name, String college, String year, String department, String roll,
            String email, String phoneNumber, String gid, boolean paid,

            String megaArchTid, boolean megaArchPaid,
            String setuBandhanTid, boolean setuBandhanPaid,
            String trackoteasureTid, boolean trackoteasurePaid,

            String codezenTid, boolean codezenPaid,
            String codeQuestTid, boolean codeQuestPaid,
            String webMindsTid, boolean webMindsPaid,

            String electriQuestTid, boolean electriQuestPaid,
            String electrical2Tid, boolean electrical2Paid,

            String bgmiLanTid, boolean bgmiLanPaid,
            String valorantLanTid, boolean valorantLanPaid,
            String pesLanTid, boolean pesLanPaid,

            String bingeQuizTid, boolean bingeQuizPaid,
            String tableTennisTid, boolean tableTennisPaid,
            String carromTid, boolean carromPaid,

            String lineTrekkerTid, boolean lineTrekkerPaid,
            String triathlonTid, boolean triathlonPaid,
            String roboKlassikerTid, boolean roboKlassikerPaid,
            String roboWar8kgTid, boolean roboWar8kgPaid,
            String roboWar15kgTid, boolean roboWar15kgPaid
    ) {
        this.name = name;
        this.college = college;
        this.year = year;
        this.department = department;
        this.roll = roll;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gid = gid;
        this.paid = paid;
        this.megaArchTid = megaArchTid;
        this.megaArchPaid = megaArchPaid;
        this.setuBandhanTid = setuBandhanTid;
        this.setuBandhanPaid = setuBandhanPaid;
        TrackoteasureTid = trackoteasureTid;
        this.trackoteasurePaid = trackoteasurePaid;
        this.codezenTid = codezenTid;
        this.codezenPaid = codezenPaid;
        this.codeQuestTid = codeQuestTid;
        this.codeQuestPaid = codeQuestPaid;
        this.webMindsTid = webMindsTid;
        this.webMindsPaid = webMindsPaid;
        this.electriQuestTid = electriQuestTid;
        this.electriQuestPaid = electriQuestPaid;
        this.electrical2Tid = electrical2Tid;
        this.electrical2Paid = electrical2Paid;
        this.bgmiLanTid = bgmiLanTid;
        this.bgmiLanPaid = bgmiLanPaid;
        this.valorantLanTid = valorantLanTid;
        this.valorantLanPaid = valorantLanPaid;
        this.pesLanTid = pesLanTid;
        this.pesLanPaid = pesLanPaid;
        this.bingeQuizTid = bingeQuizTid;
        this.bingeQuizPaid = bingeQuizPaid;
        this.tableTennisTid = tableTennisTid;
        this.tableTennisPaid = tableTennisPaid;
        this.carromTid = carromTid;
        this.carromPaid = carromPaid;
        this.lineTrekkerTid = lineTrekkerTid;
        this.lineTrekkerPaid = lineTrekkerPaid;
        this.triathlonTid = triathlonTid;
        this.triathlonPaid = triathlonPaid;
        this.roboKlassikerTid = roboKlassikerTid;
        this.roboKlassikerPaid = roboKlassikerPaid;
        this.roboWar8kgTid = roboWar8kgTid;
        this.roboWar8kgPaid = roboWar8kgPaid;
        this.roboWar15kgTid = roboWar15kgTid;
        this.roboWar15kgPaid = roboWar15kgPaid;
    }
//            this.roboWar8kgTid=roboWar8kgTid;
//            this.roboWar15kgTid=roboWar15kgTid;
//
//
//        }

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

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String phoneNumber;

    private String gid;
    private boolean paid;

    //civil
    @Nullable
    private String megaArchTid;
    private boolean megaArchPaid;
    @Nullable
    private String setuBandhanTid;
    private boolean setuBandhanPaid;
    @Nullable
    private String TrackoteasureTid;
    private boolean trackoteasurePaid;

    //coding
    @Nullable
    private String codezenTid;
    private boolean codezenPaid;
    @Nullable
    private String codeQuestTid;
    private boolean codeQuestPaid;
    @Nullable
    private String webMindsTid;
    @Nullable
    private boolean webMindsPaid;

    //electrical
    @Nullable
    private String electriQuestTid;
    private boolean electriQuestPaid;
    @Nullable
    private String electrical2Tid;
    private boolean electrical2Paid;

    //gaming
    @Nullable
    private String bgmiLanTid;
    private boolean bgmiLanPaid;
    @Nullable
    private String valorantLanTid;
    private boolean valorantLanPaid;
    @Nullable
    private String pesLanTid;
    private boolean pesLanPaid;

    //general
    @Nullable
    private String bingeQuizTid;
    private boolean bingeQuizPaid;
    @Nullable
    private String tableTennisTid;
    private boolean tableTennisPaid;
    @Nullable
    private String carromTid;
    private boolean carromPaid;

    //robotics
    @Nullable
    private String lineTrekkerTid;
    private boolean lineTrekkerPaid;
    @Nullable
    private String triathlonTid;
    private boolean triathlonPaid;
    @Nullable
    private String roboKlassikerTid;
    private boolean roboKlassikerPaid;
    @Nullable
    private String roboWar8kgTid;
    private boolean roboWar8kgPaid;
    @Nullable
    private String roboWar15kgTid;
    private boolean roboWar15kgPaid;
}
