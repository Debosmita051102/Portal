package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.coding.Service.CodingCrdService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/coding")
public class CodingCrdController {

    @Autowired
    private CodingCrdService service;

    @Async
    @GetMapping("/codezen")
    public CompletableFuture<ResponseEntity<?>> codezenCrd () {
        return service.getCodezenCrd().thenApply(success->{
            if (success != null){
                return ResponseEntity.ok().body(success);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/code-quest")
    public CompletableFuture<ResponseEntity<?>> codeQuestCrd () {
        return service.getCodeQuestCrd().thenApply(success->{
            if (success != null){
                return ResponseEntity.ok().body(success);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/web-minds")
    public CompletableFuture<ResponseEntity<?>> webMindsCrd () {
        return service.getWebMindsCrd().thenApply(success->{
            if (success != null){
                return ResponseEntity.ok().body(success);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/codezen/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> updateCodezenCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateCodezenCrd(tid, played)
                .thenApply(success -> {
                    if (success != null) {
                        return ResponseEntity.ok().body(success);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/code-quest/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> updateCodeQuestCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateCodeQuestCrd(tid, played)
                .thenApply(success -> {
                    if (success != null) {
                        return ResponseEntity.ok().body(success);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/web-minds/{tid}/{played}")
    public CompletableFuture<ResponseEntity<?>> updateWebMindsCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        return service.updateWebMindsCrd(tid, played)
                .thenApply(success -> {
                    if (success != null) {
                        return ResponseEntity.ok().body(success);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
