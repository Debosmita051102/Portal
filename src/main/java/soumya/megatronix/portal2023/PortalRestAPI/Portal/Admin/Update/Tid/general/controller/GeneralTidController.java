package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.general.service.GeneralTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/general")
public class GeneralTidController {

    @Autowired
    private GeneralTidService service;

    @Async
    @PutMapping("/binge-quiz/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateBingeQuizPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.updateBingeQuizPaidStatus(tid, paid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/table-tennis/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateTableTennisPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.updateTableTennisPaidStatus(tid, paid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/carrom/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> handleUpdateCarromPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
      return service.updateCarromPaidStatus(tid, paid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
