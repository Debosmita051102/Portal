package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.robotics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.robotics.Service.RoboticsCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/robotics")
public class RoboticsCheckTidController {

    @Autowired
    private RoboticsCheckTidService service;

    @GetMapping("/line-trekker/{tid}")
    public ResponseEntity<?> checkLineTrekkerTid (
            @PathVariable("tid") String tid
    ) {
        LineTrekkerModel model = service.checkLineTrekkerTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/robo-klassiker/{tid}")
    public ResponseEntity<?> checkRoboKlassikerTid(
            @PathVariable("tid") String tid
    ) {
        RoboKlassikerModel model = service.checkRoboKlassikerTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/triathlon/{tid}")
    public ResponseEntity<?> checkTriathlonTid (
            @PathVariable("tid") String tid
    ) {
        TriathlonModel model = service.checkTriathlonTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/war-8kg/{tid}")
    public ResponseEntity<?> checkWar8kgTid (
            @PathVariable("tid") String tid
    ) {
        War8KgModel model = service.checkWar8kgTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/war-15kg/{tid}")
    public ResponseEntity<?> checkWar15KgTid (
            @PathVariable("tid") String tid
    ) {
        War15KgModel model = service.checkWar15KgTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
