package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.general.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.BingeQuizRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.CarromRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.TableTennisRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GeneralCheckTidService {

    @Autowired
    private BingeQuizRepository bingeQuizRepository;
    @Autowired
    private TableTennisRepository tableTennisRepository;
    @Autowired
    private CarromRepository carromRepository;

    public BingeQuiz checkBingeQuizTid (
            String tid
    ) {
        Optional<BingeQuiz> model = bingeQuizRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else
            return model.get();
    }

    public TableTennis checkTableTennisTid (
            String tid
    ) {
        Optional<TableTennis> model = tableTennisRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else
            return model.get();
    }

    public Carrom checkCarromTid (
            String tid
    ) {
        Optional<Carrom> model = carromRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else
            return model.get();
    }
}
