package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.civil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.MegaArchRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.SetuBandhanRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil.TrackOTeasureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CivilCrdService {

    //    civil Repository
    @Autowired
    private SetuBandhanRepository setuBandhanRepository;
    @Autowired
    private TrackOTeasureRepository trackOTeasureRepository;
    @Autowired
    private MegaArchRepository megaArchRepository;

    @Async
    public CompletableFuture<List<SetuBandhanModel>> getSetuBandhanCRD () {
        ArrayList<SetuBandhanModel> models = new ArrayList<>();
        for (SetuBandhanModel model : setuBandhanRepository.findAll()) {
            if (!model.isPlayed()) {
                models.add(model);
            }
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<List<TrackOTeasureModel>> getToTCRD () {
        ArrayList<TrackOTeasureModel> models = new ArrayList<>();
        for (TrackOTeasureModel model : trackOTeasureRepository.findAll()) {
            if (!model.isPlayed()) {
                models.add(model);
            }
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<List<MegaArchModel>> getMegaArchCRD () {
        ArrayList<MegaArchModel> models = new ArrayList<>();
        for (MegaArchModel model : megaArchRepository.findAll()) {
            if (!model.isPlayed()) {
                models.add(model);
            }
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<SetuBandhanModel> updateSetuBandhanCRD(
            String tid,
            boolean play
    ) {
        Optional<SetuBandhanModel> model = setuBandhanRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPlayed(play);
            return CompletableFuture.completedFuture(setuBandhanRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<TrackOTeasureModel> updateToTCRD(
            String tid,
            boolean play
    ) {
        Optional<TrackOTeasureModel> model = trackOTeasureRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPlayed(play);
            return CompletableFuture.completedFuture(trackOTeasureRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<MegaArchModel> updateMegaArchCRD(
            String tid,
            boolean play
    ) {
        Optional<MegaArchModel> model = megaArchRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            model.get().setPlayed(play);
            return CompletableFuture.completedFuture(megaArchRepository.save(model.get()));
        }
    }
}