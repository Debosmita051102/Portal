package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Tid.general.service.GeneralCheckTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/check-tid/general")
public class GeneralCheckTidController {

    @Autowired
    private GeneralCheckTidService service;

    @GetMapping("/binge-quiz/{tid}")
    public ResponseEntity<?> handleBingeQuizTidChecking (
            @PathVariable("tid") String tid
    ) {
        BingeQuiz model = service.checkBingeQuizTid(tid);
        if(model != null){
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/table-tennis/{tid}")
    public ResponseEntity<?> handleTableTennisTidChecking (
            @PathVariable("tid") String tid
    ) {
        TableTennis model = service.checkTableTennisTid(tid);
        if(model != null){
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carrom/{tid}")
    public ResponseEntity<?> handleCarromTidChecking (
            @PathVariable("tid") String tid
    ) {
        Carrom model = service.checkCarromTid(tid);
        if(model != null){
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
