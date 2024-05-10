package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.coding.Service.CodingCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/coding")
public class CodingCheckTidController {

    @Autowired
    private CodingCheckTidService service;

    @GetMapping("/codezen/{tid}")
    public ResponseEntity<?> checkCodezenTid (
            @PathVariable("tid") String tid
    ) {
        CodezenModel model = service.checkCodezenTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/code-quest/{tid}")
    public ResponseEntity<?> checkCodeQuestTid(
            @PathVariable("tid") String tid
    ) {
        CodeQuestModel model = service.checkCodeQuestTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/web-minds/{tid}")
    public ResponseEntity<?> checkWebMindsTid (
            @PathVariable("tid") String tid
    ) {
        WebMindsModel model = service.checkWebMindsTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
