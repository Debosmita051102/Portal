package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.coding.Service.CodingCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/coding")
public class CodingCheckTidController {

    @Autowired
    private CodingCheckTidService service;

    @Async
    @GetMapping("/codezen/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkCodezenTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkCodezenTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/code-quest/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkCodeQuestTid(
            @PathVariable("tid") String tid
    ) {
        return service.checkCodeQuestTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/web-minds/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkWebMindsTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkWebMindsTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
