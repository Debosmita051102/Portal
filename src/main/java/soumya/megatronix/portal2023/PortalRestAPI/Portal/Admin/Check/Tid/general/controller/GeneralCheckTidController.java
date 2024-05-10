package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.general.service.GeneralCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/general")
public class GeneralCheckTidController {

    @Autowired
    private GeneralCheckTidService service;

    @Async
    @GetMapping("/binge-quiz/{tid}")
    public CompletableFuture<ResponseEntity<?>> handleBingeQuizTidChecking (
            @PathVariable("tid") String tid
    ) {
        return service.checkBingeQuizTid(tid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/table-tennis/{tid}")
    public CompletableFuture<ResponseEntity<?>> handleTableTennisTidChecking (
            @PathVariable("tid") String tid
    ) {
        return service.checkTableTennisTid(tid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/carrom/{tid}")
    public CompletableFuture<ResponseEntity<?>> handleCarromTidChecking (
            @PathVariable("tid") String tid
    ) {
        return service.checkCarromTid(tid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
