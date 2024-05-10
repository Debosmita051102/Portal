package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.coding.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CodingCheckTidService {

    //    coding Repository
    @Autowired
    private CodezenRepository codezenRepository;
    @Autowired
    private CodeQuestRepository codeQuestRepository;
    @Autowired
    private WebMindsRepository webMindsRepository;


    @Async
    public CompletableFuture<CodezenModel> checkCodezenTid(
            String tid
    ) {
        Optional<CodezenModel> model = codezenRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<CodeQuestModel> checkCodeQuestTid(
            String tid
    ) {
        Optional<CodeQuestModel> model = codeQuestRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }

    @Async
    public CompletableFuture<WebMindsModel> checkWebMindsTid(
            String tid
    ) {
        Optional<WebMindsModel> model = webMindsRepository.findByTid(tid);
        if (model.isEmpty()) {
            throw new RuntimeException("No such TID found");
        } else {
            return CompletableFuture.completedFuture(model.get());
        }
    }
}