package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Gid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Gid.Service.CheckGidService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-gid")
public class CheckGidController {

    @Autowired
    private CheckGidService service;

    @Async
    @GetMapping("/{gid}")
    public CompletableFuture<ResponseEntity<?>> checkGid(
            @PathVariable("gid") String gid
    ) {
        return service.checkGidAdmin(gid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex ->ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
