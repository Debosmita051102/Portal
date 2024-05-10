package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Gid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Gid.Service.UpdateGidService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-gid")
public class UpdateGidController {

    @Autowired
    private UpdateGidService updateGidService;

    @Async
    @PutMapping("/{gid}/{paid}")
    public CompletableFuture<ResponseEntity<?>> updateGid(
            @PathVariable("gid") String gid,
            @PathVariable("paid") Boolean paid
    ) {
        return updateGidService.updateGid(gid,paid)
                .thenApply(savedMember -> {
                  if (savedMember != null && savedMember.getGid() != null) {
                      return ResponseEntity.ok().body(savedMember);
                  } else {
                      return ResponseEntity.notFound().build();
                  }
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
