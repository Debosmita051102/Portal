package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "setu_bandhan_ToT_Combo")
@NoArgsConstructor
@Async
public class SetuBandhanAndToTComboModel {
  public SetuBandhanAndToTComboModel ( String gid1, String gid2, String gid3, String number1 ) {
    this.gid1 = gid1;
    this.gid2 = gid2;
    this.gid3 = gid3;
    this.number1 = number1;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String gid1;

  @Column(nullable = true)
  private String gid2;

  @Column(nullable = true)
  private String gid3;

  private String number1;
  private List<String> tid;
  private boolean played = false;
  private boolean paid = false;
}
