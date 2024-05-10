package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.gaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.gaming.Service.GamingCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/gaming")
public class GamingCheckTidController {

    @Autowired
    private GamingCheckTidService service;

    @GetMapping("/bgmi/{tid}")
    public ResponseEntity<?> checkBgmiLanTid (
            @PathVariable("tid") String tid
    ) {
        BgmiLan model = service.checkBgmiLanTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/valorant/{tid}")
    public ResponseEntity<?> checkValorantLanTid(
            @PathVariable("tid") String tid
    ) {
        ValorantLan model = service.checkValorantLanTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pes/{tid}")
    public ResponseEntity<?> checkPesLanTid (
            @PathVariable("tid") String tid
    ) {
        PesLan model = service.checkPesLanTid(tid);
        if (model != null) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
