package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.electrical.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.electrical.Service.ElectricalCheckTidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/electrical")
public class ElectricalCheckTidController {

    @Autowired
    private ElectricalCheckTidService service;

    @Async
    @GetMapping("/electri-quest/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkElectriQuestTid (
            @PathVariable("tid") String tid
    ) {
        return service.checkElectriQeustTid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/electrical2/{tid}")
    public CompletableFuture<ResponseEntity<?>> checkElectrical2Tid(
            @PathVariable("tid") String tid
    ) {
        return service.checkElectrical2Tid(tid)
                .thenApply(savedMember -> {
                    if (savedMember != null) {
                        return ResponseEntity.ok().body(savedMember);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

}
