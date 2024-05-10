package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.civil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.civil.Service.CivilTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/civil")
public class CivilTidController {

    @Autowired
    private CivilTidService service;

    @PutMapping("/setu-bandhan/{tid}/{paid}")
    public ResponseEntity<?> checkSetuBandhanTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        SetuBandhanModel model = service.checkSetuBandhanTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tot/{tid}/{paid}")
    public ResponseEntity<?> checkToTTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        TrackOTeasureModel model = service.checkToTTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/mega-arch/{tid}/{paid}")
    public ResponseEntity<?> checkMegaArchTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        MegaArchModel model = service.checkMegaArchTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
