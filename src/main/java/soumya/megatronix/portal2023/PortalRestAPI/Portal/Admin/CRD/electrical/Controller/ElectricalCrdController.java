package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.electrical.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.electrical.Service.ElectricalCrdService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/electrical")
public class ElectricalCrdController {

    @Autowired
    private ElectricalCrdService service;

    @Async
    @GetMapping("/electri-quest")
    public CompletableFuture<ResponseEntity<?>> electriQuestCrd () {
        return service.getElectriQuestCrd().thenApply(success->{
            if (success != null){
                return ResponseEntity.ok().body(success);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/electrical2")
    public CompletableFuture<ResponseEntity<?>> electrical2Crd () {
        return service.getElectrical2Crd().thenApply(success->{
            if (success != null){
                return ResponseEntity.ok().body(success);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/electri-quest/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> updateElectriQuestCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateElectriQuestCrd(tid, played)
                .thenApply(success -> {
                    if (success != null) {
                        return ResponseEntity.ok().body(success);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/electrical2/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> updateElectrical2Crd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateElectrical2Crd(tid, played)
                .thenApply(success -> {
                    if (success != null) {
                        return ResponseEntity.ok().body(success);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
