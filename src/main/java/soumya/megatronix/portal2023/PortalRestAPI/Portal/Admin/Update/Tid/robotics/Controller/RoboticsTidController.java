package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.robotics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.robotics.Service.RoboticsTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/robotics")
public class RoboticsTidController {

    @Autowired
    private RoboticsTidService service;

    @PutMapping("/line-trekker/{tid}/{paid}")
    public ResponseEntity<?> checkLineTrekkerTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        LineTrekkerModel model = service.checkLineTrekkerTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/robo-klassiker/{tid}/{paid}")
    public ResponseEntity<?> checkRoboKlassikerTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        RoboKlassikerModel model = service.checkRoboKlassikerTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/triathlon/{tid}/{paid}")
    public ResponseEntity<?> checkTriathlonTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        TriathlonModel model = service.checkTriathlonTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/war-8kg/{tid}")
    public ResponseEntity<?> checkWar8kgTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        War8KgModel model = service.checkWar8kgTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/war-15kg/{tid}/{paid}")
    public ResponseEntity<?> checkWar15KgTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        War15KgModel model = service.checkWar15KgTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
