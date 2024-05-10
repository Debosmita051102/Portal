package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.civil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.civil.Service.CivilCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/civil")
public class CivilCheckTidController {

    @Autowired
    private CivilCheckTidService service;

    @GetMapping("/setu-bandhan/{tid}")
    public ResponseEntity<?> checkSetuBandhanTid(
            @PathVariable("tid") String tid
    ) {
        SetuBandhanModel model = service.checkSetuBandhanTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tot/{tid}")
    public ResponseEntity<?> checkToTTid(
            @PathVariable("tid") String tid
    ) {
        TrackOTeasureModel model = service.checkToTTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mega-arch/{tid}")
    public ResponseEntity<?> checkMegaArchTid(
            @PathVariable("tid") String tid
    ) {
        MegaArchModel model = service.checkMegaArchTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
