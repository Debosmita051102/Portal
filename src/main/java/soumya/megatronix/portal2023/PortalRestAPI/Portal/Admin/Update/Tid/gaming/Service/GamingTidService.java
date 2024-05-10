package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.gaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.BgmiLanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.ValorantLanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.PesLanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.BgmiLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.PesLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.gaming.ValorantLanService;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GamingTidService {

    //    gaming Repository
    @Autowired
    private BgmiLanRepository gaming1Repository;
    @Autowired
    private ValorantLanRepository gaming2Repository;
    @Autowired
    private PesLanRepository gaming3Repository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BgmiLanService gaming1Service;
    @Autowired
    private ValorantLanService gaming2Service;
    @Autowired
    private PesLanService gaming3Service;

    public BgmiLan checkBgmiLanTid(
            String tid,
            boolean paid
    ) {
        Optional<BgmiLan> model = gaming1Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String> emails=gaming1Service.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Bgmi-Lan",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );
            return gaming1Repository.save(model.get());
        }
    }

    public ValorantLan checkValorantLanTid(
            String tid,
            boolean paid
    ) {
        Optional<ValorantLan> model = gaming2Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String> emails=gaming2Service.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Pes-Lan",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );
            return gaming2Repository.save(model.get());
        }
    }

    public PesLan checkPesLanTid(
            String tid,
            boolean paid
    ) {
        Optional<PesLan> model = gaming3Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String> emails=gaming3Service.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Valorant-Lan",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );
            return gaming3Repository.save(model.get());
        }
    }
}
