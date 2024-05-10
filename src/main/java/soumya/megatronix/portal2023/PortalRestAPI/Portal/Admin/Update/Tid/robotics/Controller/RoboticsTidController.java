package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.robotics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.robotics.Service.RoboticsTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/robotics")
public class RoboticsTidController {

    @Autowired
    private RoboticsTidService service;

    @Async
    @PutMapping("/line-trekker/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> checkLineTrekkerTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkLineTrekkerTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/robo-klassiker/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> checkRoboKlassikerTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkRoboKlassikerTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/triathlon/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> checkTriathlonTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkTriathlonTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/war-8kg/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkWar8kgTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkWar8kgTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/war-15kg/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> checkWar15KgTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkWar15KgTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
