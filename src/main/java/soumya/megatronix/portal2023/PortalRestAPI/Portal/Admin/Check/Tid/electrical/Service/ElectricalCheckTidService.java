package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.electrical.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ElectricalCheckTidService {
    
    //    electrical Repository
    @Autowired
    private ElectriQuestRepository electriQuestRepository;
    @Autowired
    private Electrical2Repository electrical2Repository;

    @Async
    public CompletableFuture<ElectriQuest> checkElectriQeustTid(
            String tid
    ) {
        Optional<ElectriQuest> model = electriQuestRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<Electrical2> checkElectrical2Tid(
            String tid
    ) {
        Optional<Electrical2> model = electrical2Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }
}
