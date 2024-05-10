package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.electrical.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.electrical.Service.ElectricalCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.Electrical2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/electrical")
public class ElectricalCrdController {

    @Autowired
    private ElectricalCrdService service;

    @GetMapping("/electri-quest")
    public ResponseEntity<?> electriQuestCrd () {
        List <ElectriQuest > electriQuest = service.getElectriQuestCrd();
        if (electriQuest != null) {
            return ResponseEntity.ok().body(electriQuest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/electrical2")
    public ResponseEntity<?> electrical2Crd () {
        List< Electrical2 > electrical2 = service.getElectrical2Crd();
        if (electrical2 != null) {
            return ResponseEntity.ok().body(electrical2);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/electri-quest/{tid}/{played}")
    public ResponseEntity<?> updateElectriQuestCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        ElectriQuest electriQuest = service.updateElectriQuestCrd(tid, played);
        if (electriQuest != null) {
            return ResponseEntity.ok().body(electriQuest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/electrical2/{tid}/{played}")
    public ResponseEntity<?> updateElectrical2Crd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        Electrical2 electrical2 = service.updateElectrical2Crd(tid, played);
        if (electrical2 != null) {
            return ResponseEntity.ok().body(electrical2);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
