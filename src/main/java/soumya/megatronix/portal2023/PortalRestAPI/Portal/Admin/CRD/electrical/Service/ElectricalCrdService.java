package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.electrical.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.ElectriQuestRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical.Electrical2Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ElectricalCrdService {

    //    electrical Repository
    @Autowired
    private ElectriQuestRepository electriQuestRepository;
    @Autowired
    private Electrical2Repository electrical2Repository;

    public ArrayList<ElectriQuest> getElectriQuestCrd () {
        ArrayList<ElectriQuest> models = new ArrayList<>();
        for (ElectriQuest model : electriQuestRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public ArrayList<Electrical2> getElectrical2Crd () {
        ArrayList<Electrical2> models = new ArrayList<>();
        for (Electrical2 model : electrical2Repository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public ElectriQuest updateElectriQuestCrd (
            String tid,
            boolean played
    ) {
        Optional<ElectriQuest> model = electriQuestRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return electriQuestRepository.save(model.get());
        }
    }

    public Electrical2 updateElectrical2Crd (
            String tid,
            boolean played
    ) {
        Optional<Electrical2> model = electrical2Repository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return electrical2Repository.save(model.get());
        }
    }

}
