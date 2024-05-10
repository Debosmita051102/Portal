package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String email;
    private String subject;
    private String message;
}
