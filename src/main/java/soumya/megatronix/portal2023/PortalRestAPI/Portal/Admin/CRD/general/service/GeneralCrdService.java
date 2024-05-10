package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.general.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.BingeQuizRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.CarromRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general.TableTennisRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GeneralCrdService {

    @Autowired
    private BingeQuizRepository bingeQuizRepository;
    @Autowired
    private TableTennisRepository tableTennisRepository;
    @Autowired
    private CarromRepository carromRepository;

    @Async
    public CompletableFuture<ArrayList<BingeQuiz>> getBingeQuizPlayedStatus () {
        ArrayList<BingeQuiz> models = new ArrayList<>();
        for(BingeQuiz model : bingeQuizRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<TableTennis>> getTableTennisPlayedStatus () {
        ArrayList<TableTennis> models = new ArrayList<>();
        for(TableTennis model : tableTennisRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<ArrayList<Carrom>> getCarromPlayedStatus () {
        ArrayList<Carrom> models = new ArrayList<>();
        for(Carrom model : carromRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return CompletableFuture.completedFuture(models);
    }

    @Async
    public CompletableFuture<BingeQuiz> updateBingeQuizPlayedStatus (
            String tid,
            boolean played
    ) {
        Optional<BingeQuiz> model = bingeQuizRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(bingeQuizRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<TableTennis> updateTableTennisPlayedStatus (
            String tid,
            boolean played
    ) {
        Optional<TableTennis> model = tableTennisRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(tableTennisRepository.save(model.get()));
        }
    }

    @Async
    public CompletableFuture<Carrom> updateCarromPlayedStatus (
            String tid,
            boolean played
    ) {
        Optional<Carrom> model = carromRepository.findByTid(tid);

        if(model.isEmpty())
            throw new RuntimeException("No such TID found");
        else {
            model.get().setPlayed(played);
            return CompletableFuture.completedFuture(carromRepository.save(model.get()));
        }
    }


}
