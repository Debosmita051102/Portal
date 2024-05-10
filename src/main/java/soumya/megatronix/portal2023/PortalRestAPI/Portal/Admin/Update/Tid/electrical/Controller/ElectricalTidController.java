package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.electrical.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.electrical.Service.ElectricalTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/electrical")
public class ElectricalTidController {

    @Autowired
    private ElectricalTidService service;

    @PutMapping("/electri-quest/{tid}/{paid}")
    public ResponseEntity<?> checkElectriQuestTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        ElectriQuest model = service.checkElectriQuestTid(tid, paid);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/electrical2/{tid}/{paid}")
    public ResponseEntity<?> checkElectrical2Tid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        Electrical2 model = service.checkElectrical2Tid(tid, paid);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
