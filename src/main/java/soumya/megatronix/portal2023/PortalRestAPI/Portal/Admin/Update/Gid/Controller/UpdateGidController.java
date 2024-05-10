package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Gid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Gid.Service.UpdateGidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-gid")
public class UpdateGidController {

    @Autowired
    private UpdateGidService updateGidService;
    
    @PutMapping("/{gid}/{paid}")
    public ResponseEntity<?> updateGid(
            @PathVariable("gid") String gid,
            @PathVariable("paid") Boolean paid
    ) {
        MrdModel model = updateGidService.updateGid(gid, paid);
        if (model != null) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
