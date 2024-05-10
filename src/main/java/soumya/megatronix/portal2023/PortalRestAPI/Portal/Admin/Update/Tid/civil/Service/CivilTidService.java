package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.civil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.MegaArchRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.SetuBandhanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.TrackOTeasureRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.MegaArchService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.SetuBandhanService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.civil.TrackOTeasureService;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CivilTidService {

    //    civil Repository
    @Autowired
    private MrdRepository repo;
    @Autowired
    private SetuBandhanRepository setuBandhanRepository;
    @Autowired
    private SetuBandhanService setuBandhanService;
    @Autowired
    private TrackOTeasureRepository trackOTeasureRepository;

    @Autowired
    private MegaArchRepository megaArchRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TrackOTeasureService trackOTeasureService;
    @Autowired
    private MegaArchService megaArchService;

    public SetuBandhanModel checkSetuBandhanTid(
            String tid,
            boolean paid
    ) {
        Optional<SetuBandhanModel> model = setuBandhanRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);

            List<String> emails = setuBandhanService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Setu-Bandhan",
                    "Team",
                    emails.toArray(new String[0])
            );

            return setuBandhanRepository.save(model.get());
        }
    }

    public TrackOTeasureModel checkToTTid(
            String tid,
            boolean paid
    ) {
        Optional<TrackOTeasureModel> model = trackOTeasureRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String>emails=trackOTeasureService.getEmails(model.get().getTid());
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Track-O-Treasure",
                    "Team",
                    emails.toArray(new String[0])
            );

            return trackOTeasureRepository.save(model.get());
        }
    }

    public MegaArchModel checkMegaArchTid(
            String tid,
            boolean paid
    ) {
        Optional<MegaArchModel> model = megaArchRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPaid(paid);
            List<String>emails= megaArchService.getEmails(tid);
            emailService.sendEventRegistrationUpdateEmail(
                    tid,
                    "Mega-Arch",
                    "Team",
                    emails.toArray(new String[0])
            );
            return megaArchRepository.save(model.get());
        }
    }
}