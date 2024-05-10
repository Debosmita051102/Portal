package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.gaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.gaming.Service.GamingCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/gaming")
public class GamingCrdController {

    @Autowired
    private GamingCrdService service;

    @GetMapping("/bgmi-lan")
    public ResponseEntity<?> bgmiLanCrd () {
        List< BgmiLan > models = service.getBgmiLanCrd();
        if (models != null) {
            return ResponseEntity.ok().body(models);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/valorant-lan")
    public ResponseEntity<?> valorantLanCrd () {
        List< ValorantLan > models = service.getValorantLanCrd();
        if (models != null) {
            return ResponseEntity.ok().body(models);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pes-lan")
    public ResponseEntity<?> pesLanCrd () {
        List< PesLan > models = service.getPesLanCrd();
        if (models != null) {
            return ResponseEntity.ok().body(models);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/bgmi-lan/{tid}/{played}")
    public ResponseEntity<?> updateBgmiLanCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        BgmiLan model = service.updateBgmiLanCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/valorant-lan/{tid}/{played}")
    public ResponseEntity<?> updateValorantLanCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        ValorantLan model = service.updateValorantLanCrd(tid, played);
        if ( model != null ) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pes-lan/{tid}/{played}")
    public ResponseEntity<?> updatePesLanCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        PesLan model = service.updatePesLanCrd(tid, played);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
