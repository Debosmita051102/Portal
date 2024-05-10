package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.coding.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding.CodeQuestRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding.CodezenRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding.WebMindsRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CodingCrdService {

    //    coding Repository
    @Autowired
    private CodezenRepository codezenRepository;
    @Autowired
    private CodeQuestRepository codeQuestRepository;
    @Autowired
    private WebMindsRepository webMindsRepository;

    public ArrayList<CodezenModel> getCodezenCrd () {
        ArrayList<CodezenModel> models = new ArrayList<>();
        for (CodezenModel model : codezenRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public ArrayList<CodeQuestModel> getCodeQuestCrd () {
        ArrayList<CodeQuestModel> models = new ArrayList<>();
        for (CodeQuestModel model : codeQuestRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public ArrayList<WebMindsModel> getWebMindsCrd () {
        ArrayList<WebMindsModel> models = new ArrayList<>();
        for (WebMindsModel model : webMindsRepository.findAll()) {
            if(!model.isPlayed())
                models.add(model);
        }
        return models;
    }

    public CodezenModel updateCodezenCrd (
            String tid,
            boolean played
    ) {
        Optional<CodezenModel> model = codezenRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return codezenRepository.save(model.get());
        }
    }

    public CodeQuestModel updateCodeQuestCrd (
            String tid,
            boolean played
    ) {
        Optional< CodeQuestModel > model = codeQuestRepository.findByTid(tid);
        if ( model.isEmpty() )
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return codeQuestRepository.save(model.get());
        }
    }

    public WebMindsModel updateWebMindsCrd (
            String tid,
            boolean played
    ) {
        Optional<WebMindsModel> model = webMindsRepository.findByTid(tid);
        if (model.isEmpty())
            throw new RuntimeException("No such Tid found");
        else {
            model.get().setPlayed(played);
            return webMindsRepository.save(model.get());
        }
    }
}
