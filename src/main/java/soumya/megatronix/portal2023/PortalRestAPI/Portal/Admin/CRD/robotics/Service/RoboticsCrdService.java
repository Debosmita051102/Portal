package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.robotics.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsCrdService {

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

    @Async
    public CompletableFuture<ArrayList<LineTrekkerModel>> getLineTrekkerCrd () {
        ArrayList<LineTrekkerModel> models = new ArrayList<>();
        for(LineTrekkerModel model : lineTrekkerRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<RoboKlassikerModel>> getRoboKlassikerCrd () {
        ArrayList<RoboKlassikerModel> models = new ArrayList<>();
        for(RoboKlassikerModel model : roboKlassikerRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<TriathlonModel>> getTriathlonCrd() {
        ArrayList<TriathlonModel> models = new ArrayList<>();
        for(TriathlonModel model : triathlonRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<War8KgModel>> getWar8KgCrd() {
        ArrayList<War8KgModel> models = new ArrayList<>();
        for(War8KgModel model : war8KgRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<War15KgModel>> getWar15KgCrd () {
        ArrayList<War15KgModel> models = new ArrayList<>();
        for(War15KgModel model : war15KgRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<LineTrekkerModel> updateLineTrekkerCrd (
            String tid,
            boolean played
    ) {
        Optional<LineTrekkerModel> model = lineTrekkerRepository.findByTid(tid);
        if(model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(lineTrekkerRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<RoboKlassikerModel> updateRoboKlassikerCrd (
            String tid,
            boolean played
    ) {
        Optional<RoboKlassikerModel> model = roboKlassikerRepository.findByTid(tid);
        if(model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(roboKlassikerRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<TriathlonModel> updateTriathlonCrd (
            String tid,
            boolean played
    ) {
        Optional<TriathlonModel> model = triathlonRepository.findByTid(tid);
        if(model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(triathlonRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<War8KgModel> updateWar8KgCrd (
            String tid,
            boolean played
    ) {
        Optional<War8KgModel> model = war8KgRepository.findByTid(tid);
        if(model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(war8KgRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<War15KgModel> updateWar15KgCrd (
            String tid,
            boolean played
    ) {
        Optional<War15KgModel> model = war15KgRepository.findByTid(tid);
        if(model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(war15KgRepository.save(model.get()));
        }
    }

}
