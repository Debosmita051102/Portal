package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.gaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.gaming.Service.GamingCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/gaming")
public class GamingCheckTidController {

    @Autowired
    private GamingCheckTidService service;

    @Async
    @GetMapping("/bgmi/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkBgmiLanTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkBgmiLanTid(tid).thenApply(savedMember -> {
            if (savedMember != null) {
                return ResponseEntity.ok().body(savedMember);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/valorant/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkValorantLanTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkValorantLanTid(tid).thenApply(savedMember -> {
            if (savedMember != null) {
                return ResponseEntity.ok().body(savedMember);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/pes/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkPesLanTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkPesLanTid(tid).thenApply(savedMember -> {
            if (savedMember != null) {
                return ResponseEntity.ok().body(savedMember);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
