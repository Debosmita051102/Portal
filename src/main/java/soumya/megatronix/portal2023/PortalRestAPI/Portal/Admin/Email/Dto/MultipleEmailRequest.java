package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Email.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultipleEmailRequest {

    private List<String> emails;
    private String subject;
    private String message;
}
