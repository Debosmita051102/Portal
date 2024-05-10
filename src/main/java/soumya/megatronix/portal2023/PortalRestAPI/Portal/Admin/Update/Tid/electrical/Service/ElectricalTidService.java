package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.electrical.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.ElectriQuestRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.Electrical2Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.electrical.ElectriQuestService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.electrical.Electrical2Service;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ElectricalTidService {

    //    electrical Repository
    @Autowired
    private ElectriQuestRepository electriQuestRepository;
    @Autowired
    private ElectriQuestService electriQuestService;
    @Autowired
    private Electrical2Repository electrical2Repository;
    @Autowired
    private Electrical2Service electrical2Service;
    @Autowired
    private EmailService emailService;

    public ElectriQuest checkElectriQuestTid (
            String tid,
            boolean paid
    ) {
        Optional<ElectriQuest> model = electriQuestRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String> emails=electriQuestService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Electri-Quest",
                    "Team",
                    emails.toArray(new String[0])
            );
            return electriQuestRepository.save(model.get());
        }
    }

    public Electrical2 checkElectrical2Tid(
            String tid,
            boolean paid
    ) {
        Optional<Electrical2> model = electrical2Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String> emails=electrical2Service.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Electrical-2",
                    "Team",
                    emails.toArray(new String[0])
            );
            return electrical2Repository.save(model.get());
        }
    }
}
