package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.civil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CivilCheckTidService {

    //    civil Repository
    @Autowired
    private SetuBandhanRepository setuBandhanRepository;
    @Autowired
    private TrackOTeasureRepository trackOTeasureRepository;
    @Autowired
    private MegaArchRepository megaArchRepository;

    public SetuBandhanModel checkSetuBandhanTid(
            String tid
    ) {
        Optional<SetuBandhanModel> model = setuBandhanRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }

    public TrackOTeasureModel checkToTTid(
            String tid
    ) {
        Optional<TrackOTeasureModel> model = trackOTeasureRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }

    public MegaArchModel checkMegaArchTid(
            String tid
    ) {
        Optional<MegaArchModel> model = megaArchRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }
}