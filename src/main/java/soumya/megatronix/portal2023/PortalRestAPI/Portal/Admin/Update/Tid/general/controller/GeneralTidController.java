package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Tid.general.service.GeneralTidService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin/update-tid/general")
public class GeneralTidController {

    @Autowired
    private GeneralTidService service;


    @PutMapping("/binge-quiz/{tid}/{paid}")
    public ResponseEntity<?> handleUpdateBingeQuizPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        BingeQuiz model = service.updateBingeQuizPaidStatus(tid, paid);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/table-tennis/{tid}/{paid}")
    public ResponseEntity<?> handleUpdateTableTennisPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        TableTennis model = service.updateTableTennisPaidStatus(tid, paid);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/carrom/{tid}/{paid}")
    public ResponseEntity<?> handleUpdateCarromPaidStatus (
            @PathVariable("tid") String tid,
            @PathVariable("paid") Boolean paid
    ) {
        Carrom model = service.updateCarromPaidStatus(tid, paid);
        if ( model != null ) {
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
