package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.electrical.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.electrical.Service.ElectricalCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/electrical")
public class ElectricalCheckTidController {

    @Autowired
    private ElectricalCheckTidService service;

    @GetMapping("/electri-quest/{tid}")
    public ResponseEntity<?> checkElectriQuestTid (
            @PathVariable("tid") String tid
    ) {
        ElectriQuest model = service.checkElectriQeustTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/electrical2/{tid}")
    public ResponseEntity<?> checkElectrical2Tid(
            @PathVariable("tid") String tid
    ) {
        Electrical2 model = service.checkElectrical2Tid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
