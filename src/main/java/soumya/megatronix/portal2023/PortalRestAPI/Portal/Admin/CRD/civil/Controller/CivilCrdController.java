package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.civil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.civil.Service.CivilCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/civil")
public class CivilCrdController {

    @Autowired
    private CivilCrdService service;

    @GetMapping("/setu-bandhan")
    public ResponseEntity<?> setuBandhanCrd () {
        List<SetuBandhanModel> setuBandhanCRD = service.getSetuBandhanCRD();
        if (setuBandhanCRD != null) {
            return ResponseEntity.ok().body(setuBandhanCRD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tot")
    public ResponseEntity<?> totCrd () {
        List<?> toTCRD = service.getToTCRD();
        if (toTCRD != null) {
            return ResponseEntity.ok().body(toTCRD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mega-arch")
    public ResponseEntity<?> megaArchCrd () {
        List<MegaArchModel> megaArchCRD = service.getMegaArchCRD();
        if (megaArchCRD != null) {
            return ResponseEntity.ok().body(megaArchCRD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/setu-bandhan/{tid}/{played}")
    public ResponseEntity<?> updateSetuBandhanCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        SetuBandhanModel setuBandhanModel = service.updateSetuBandhanCRD(tid, played);
        if (setuBandhanModel != null) {
            return ResponseEntity.ok().body(setuBandhanModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tot/{tid}/{played}")
    public ResponseEntity<?> updateToTCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        TrackOTeasureModel trackOTeasureModel = service.updateToTCRD(tid, played);
        if (trackOTeasureModel != null) {
            return ResponseEntity.ok().body(trackOTeasureModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/mega-arch/{tid}/{played}")
    public ResponseEntity<?> updateMegaArchCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        MegaArchModel megaArchModel = service.updateMegaArchCRD(tid, played);
        if (megaArchModel != null) {
            return ResponseEntity.ok().body(megaArchModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
