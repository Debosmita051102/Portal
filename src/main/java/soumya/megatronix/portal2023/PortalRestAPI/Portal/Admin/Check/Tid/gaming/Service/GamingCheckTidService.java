package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.gaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GamingCheckTidService {

    //    gaming Repository
    @Autowired
    private BgmiLanRepository gaming1Repository;
    @Autowired
    private ValorantLanRepository gaming2Repository;
    @Autowired
    private PesLanRepository gaming3Repository;

    public BgmiLan checkBgmiLanTid(
            String tid
    ) {
        Optional<BgmiLan> model = gaming1Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }

    public ValorantLan checkValorantLanTid(
            String tid
    ) {
        Optional<ValorantLan> model = gaming2Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }

    public PesLan checkPesLanTid(
            String tid
    ) {
        Optional<PesLan> model = gaming3Repository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return model.get();
        }
    }
}
