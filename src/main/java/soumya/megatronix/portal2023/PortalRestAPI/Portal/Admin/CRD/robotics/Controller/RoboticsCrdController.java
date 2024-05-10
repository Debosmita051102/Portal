package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.robotics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.robotics.Service.RoboticsCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/robotics")
public class RoboticsCrdController {

    @Autowired
    private RoboticsCrdService service;

    @GetMapping("/line-trekker")
    public ResponseEntity<?> lineTrekkerCrd () {
        List< LineTrekkerModel > model = service.getLineTrekkerCrd();
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/robo-klassiker")
    public ResponseEntity<?> roboKlassikerCrd () {
        List< RoboKlassikerModel > model = service.getRoboKlassikerCrd();
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/triathlon")
    public ResponseEntity<?> triathlonCrd () {
        List< TriathlonModel > model = service.getTriathlonCrd();
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/war-8kg")
    public ResponseEntity<?> war8kgCrd () {
        List< War8KgModel > model = service.getWar8KgCrd();
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/war-15kg")
    public ResponseEntity<?> war15KgCrd () {
        List< War15KgModel > model = service.getWar15KgCrd();
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/line-trekker/{tid}/{played}")
    public ResponseEntity<?> updateLineTrekkerCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        LineTrekkerModel model = service.updateLineTrekkerCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/robo-klassiker/{tid}/{played}")
    public ResponseEntity<?> updateRoboKlassikerCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        RoboKlassikerModel model = service.updateRoboKlassikerCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/triathlon/{tid}/{played}")
    public ResponseEntity<?> updateTriathlonCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        TriathlonModel model = service.updateTriathlonCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/war-8kg/{tid}/{played}")
    public ResponseEntity<?> updateWar8KgCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        War8KgModel model = service.updateWar8KgCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/war-15kg/{tid}/{played}")
    public ResponseEntity<?> updateWar15KgCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        War15KgModel model = service.updateWar15KgCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
