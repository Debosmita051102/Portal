package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.coding.Service.CodingTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/coding")
public class CodingTidController {

    @Autowired
    private CodingTidService service;

    @Async
    @PutMapping("/codezen/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> setCodezenPaid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkCodezenTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/code-quest/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> setCodeQuestPaid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkCodeQuestTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/web-minds/{tid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> setWebMindsPaid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        return service.checkWebMindsTid(tid, paid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
