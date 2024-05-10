package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.robotics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.robotics.Service.RoboticsCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/robotics")
public class RoboticsCheckTidController {

    @Autowired
    private RoboticsCheckTidService service;

    @Async
    @GetMapping("/line-trekker/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkLineTrekkerTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkLineTrekkerTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/robo-klassiker/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkRoboKlassikerTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkRoboKlassikerTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/triathlon/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkTriathlonTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkTriathlonTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/war-8kg/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkWar8kgTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkWar8kgTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/war-15kg/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkWar15KgTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkWar15KgTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
