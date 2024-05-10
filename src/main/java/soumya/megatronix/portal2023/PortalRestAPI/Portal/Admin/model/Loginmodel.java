package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;

@Data
@Async
@NoArgsConstructor
@Getter
@Setter
public class Loginmodel {
    private String username;
    private String password;

    public Loginmodel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
