package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.coding.Service.CodingTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/coding")
public class CodingTidController {

    @Autowired
    private CodingTidService service;

    @Async
    @PutMapping("/codezen/{tid}/{paid}")
    public ResponseEntity<?> setCodezenPaid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        CodezenModel model = service.checkCodezenTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Async
    @PutMapping("/code-quest/{tid}/{paid}")
    public ResponseEntity<?> setCodeQuestPaid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        CodeQuestModel model = service.checkCodeQuestTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Async
    @PutMapping("/web-minds/{tid}/{paid}")
    public ResponseEntity<?> setWebMindsPaid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        WebMindsModel model = service.checkWebMindsTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
