package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.general.service.GeneralCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;

import java.util.List;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/general")
public class GeneralCrdController {

    @Autowired
    private GeneralCrdService service;

    @GetMapping("/binge-quiz")
    public ResponseEntity<?> handleGetBingeQuizPlayedStatus () {
        List< BingeQuiz > model = service.getBingeQuizPlayedStatus();
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/table-tennis")
    public ResponseEntity<?> handleGetTableTennisPlayedStatus () {
        List< TableTennis > model = service.getTableTennisPlayedStatus();
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/carrom")
    public ResponseEntity<?> handleGetCarromPlayedStatus () {
        List< Carrom > model = service.getCarromPlayedStatus();
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/binge-quiz/{tid}/{played}")
    public ResponseEntity<?> handleUpdateBingeQuizPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        BingeQuiz model = service.updateBingeQuizPlayedStatus(tid, played);
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/table-tennis/{tid}/{played}")
    public ResponseEntity<?> handleUpdateTableTennisPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        TableTennis model = service.updateTableTennisPlayedStatus(tid, played);
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/carrom/{tid}/{played}")
    public ResponseEntity<?> handleUpdateCarromPlayedStatus (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        TableTennis model = service.updateTableTennisPlayedStatus(tid, played);
        if ( model != null ) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
