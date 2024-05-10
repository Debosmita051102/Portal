package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.general.service.GeneralCrdService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/general")
public class GeneralCrdController {

    @Autowired
    private GeneralCrdService service;

    @Async
    @GetMapping("/binge-quiz")
    public CompletableFuture<ResponseEntity<?>> handleGetBingeQuizPlayedStatus () {
        return service.getBingeQuizPlayedStatus()
                .thenApply(success -> {
                    if (success == null)
                        return ResponseEntity.badRequest().build();
                    else
                        return ResponseEntity.ok(success);
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/table-tennis")
    public CompletableFuture<ResponseEntity<?>> handleGetTableTennisPlayedStatus () {
        return service.getTableTennisPlayedStatus()
                .thenApply(success -> {
                    if (success == null)
                        return ResponseEntity.badRequest().build();
                    else
                        return ResponseEntity.ok(success);
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/carrom")
    public CompletableFuture<ResponseEntity<?>> handleGetCarromPlayedStatus () {
        return service.getCarromPlayedStatus()
                .thenApply(success -> {
                    if (success == null)
                        return ResponseEntity.badRequest().build();
                    else
                        return ResponseEntity.ok(success);
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/binge-quiz/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateBingeQuizPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateBingeQuizPlayedStatus(tid, played)
                .thenApply(success -> {
                    if( success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/table-tennis/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateTableTennisPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateTableTennisPlayedStatus(tid, played)
                .thenApply(success -> {
                    if( success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/carrom/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateCarromPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateCarromPlayedStatus(tid, played)
                .thenApply(success -> {
                    if( success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
