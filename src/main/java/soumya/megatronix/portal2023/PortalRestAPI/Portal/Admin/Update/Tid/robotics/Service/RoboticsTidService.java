package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.robotics.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsTidService {
    
    //    robotics
    @Autowired
    private LineTrekkerRepository lineTrekkerRepository;
    @Autowired
    private RoboKlassikerRepository roboKlassikerRepository;
    @Autowired
    private TriathlonRepository triathlonRepository;
    @Autowired
    private War8KgRepository war8KgRepository;
    @Autowired
    private War15KgRepository war15KgRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private LineTrekkerService lineTrekkerService;
    @Autowired
    private RoboKlassikerService roboKlassikerService;
    @Autowired
    private TriathlonService triathlonService;
    @Autowired
    private War8KgService war8KgService;
    @Autowired
    private War15KgService war15KgService;

    @Async
    public CompletableFuture<LineTrekkerModel> checkLineTrekkerTid(
            String tid,
            boolean paid
    ) {
        Optional<LineTrekkerModel> model = lineTrekkerRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = lineTrekkerService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Line-Trekker",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );

            return CompletableFuture.completedFuture(lineTrekkerRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<RoboKlassikerModel> checkRoboKlassikerTid(
            String tid,
            boolean paid
    ) {
        Optional<RoboKlassikerModel> model = roboKlassikerRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = roboKlassikerService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Robo-Klassiker",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );

            return CompletableFuture.completedFuture(roboKlassikerRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<TriathlonModel> checkTriathlonTid(
            String tid,
            boolean paid
    ) {
        Optional<TriathlonModel> model = triathlonRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = triathlonService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Triathlon",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );

            return CompletableFuture.completedFuture(triathlonRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<War8KgModel> checkWar8kgTid(
            String tid,
            boolean paid
    ) {
        Optional<War8KgModel> model = war8KgRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = war8KgService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Throne-Of-Bots 8Kg",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );

            return CompletableFuture.completedFuture(war8KgRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<War15KgModel> checkWar15KgTid(
            String tid,
            boolean paid
    ) {
        Optional<War15KgModel> model = war15KgRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = war15KgService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Throne-Of-Bots 15Kg",
                    model.get().getTeamname(),
                    emails.toArray(new String[0])
            );

            return CompletableFuture.completedFuture(war15KgRepository.save(model.get()));
        }
    }
}
