package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.gaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.gaming.Service.GamingTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/gaming")
public class
GamingTidController {

    @Autowired
    private GamingTidService service;

    @PutMapping("/bgmi/{tid}/{paid}")
    public ResponseEntity<?> checkBgmiLanTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        BgmiLan model = service.checkBgmiLanTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/valorant/{tid}/{paid}")
    public ResponseEntity<?> checkValorantLanTid(
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        ValorantLan model = service.checkValorantLanTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pes/{tid}/{paid}")
    public ResponseEntity<?> checkPesLanTid (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        PesLan model = service.checkPesLanTid(tid, paid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
