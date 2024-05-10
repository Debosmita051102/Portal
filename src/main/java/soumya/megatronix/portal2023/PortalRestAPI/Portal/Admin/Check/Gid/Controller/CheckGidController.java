package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Gid.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Gid.Service.CheckGidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-gid")
public class CheckGidController {

    @Autowired
    private CheckGidService service;

    @GetMapping("/{gid}")
    public ResponseEntity<?> checkGid(
            @PathVariable("gid") String gid
    ) {
        MrdModel model = service.checkGidAdmin(gid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
