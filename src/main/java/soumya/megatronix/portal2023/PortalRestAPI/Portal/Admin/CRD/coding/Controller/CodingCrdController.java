package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.coding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.CRD.coding.Service.CodingCrdService;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;

import java.util.List;

@RestController
@RequestMapping("megatronix/paridhi/admin/crd/coding")
public class CodingCrdController {

    @Autowired
    private CodingCrdService service;

    @GetMapping("/codezen")
    public ResponseEntity<?> codezenCrd () {
        List< CodezenModel > list = service.getCodezenCrd();
        if(list != null){
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/code-quest")
    public ResponseEntity<?> codeQuestCrd () {
        List< CodeQuestModel > list = service.getCodeQuestCrd();
        if(list != null){
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/web-minds")
    public ResponseEntity<?> webMindsCrd () {
        List< WebMindsModel > list = service.getWebMindsCrd();
        if(list != null){
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/codezen/{tid}/{played}")
    public ResponseEntity<?> updateCodezenCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        CodezenModel model = service.updateCodezenCrd(tid, played);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/code-quest/{tid}/{played}")
    public ResponseEntity<?> updateCodeQuestCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        CodeQuestModel model = service.updateCodeQuestCrd(tid, played);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/web-minds/{tid}/{played}")
    public ResponseEntity<?> updateWebMindsCrd (
            @PathVariable("tid") String tid,
            @PathVariable("played") Boolean played
    ) {
        WebMindsModel model = service.updateWebMindsCrd(tid, played);
        if(model != null){
            return ResponseEntity.ok().body(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
