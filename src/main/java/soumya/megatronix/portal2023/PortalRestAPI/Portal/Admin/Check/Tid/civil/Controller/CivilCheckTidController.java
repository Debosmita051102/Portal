package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.civil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.civil.Service.CivilCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/civil")
public class CivilCheckTidController {

    @Autowired
    private CivilCheckTidService service;

    @Async
    @GetMapping("/setu-bandhan/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkSetuBandhanTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkSetuBandhanTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/tot/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkToTTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkToTTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/mega-arch/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkMegaArchTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkMegaArchTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
