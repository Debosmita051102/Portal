package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.robotics.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsCheckTidService {
    
    //    robotics repository
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
    public CompletableFuture<LineTrekkerModel> checkLineTrekkerTid(
            String tid
    ) {
        Optional<LineTrekkerModel> model = lineTrekkerRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<RoboKlassikerModel> checkRoboKlassikerTid(
            String tid
    ) {
        Optional<RoboKlassikerModel> model = roboKlassikerRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<TriathlonModel> checkTriathlonTid(
            String tid
    ) {
        Optional<TriathlonModel> model = triathlonRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<War8KgModel> checkWar8kgTid(
            String tid
    ) {
        Optional<War8KgModel> model = war8KgRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<War15KgModel> checkWar15KgTid(
            String tid
    ) {
        Optional<War15KgModel> model = war15KgRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }
}
