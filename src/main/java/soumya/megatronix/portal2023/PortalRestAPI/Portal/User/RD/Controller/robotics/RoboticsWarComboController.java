package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsWarComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.RoboticsWarComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/robotics")
public class RoboticsWarComboController {

    @Autowired
    private RoboticsWarComboService roboticsWarComboService;

    @Async
    @PostMapping("/war-combo")
    public CompletableFuture<ResponseEntity<?>> warCombo(
            @RequestBody RoboticsWarComboModel warComboModel
    ) {
        return roboticsWarComboService.saveWarCombo(warComboModel)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success.getTid());
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/war-combo/{gid}")
    public CompletableFuture<ResponseEntity<?>> handleCheckGidforWarCombo (
            @PathVariable("gid") String gid
    ) {
        return roboticsWarComboService.checkComboGid(gid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
