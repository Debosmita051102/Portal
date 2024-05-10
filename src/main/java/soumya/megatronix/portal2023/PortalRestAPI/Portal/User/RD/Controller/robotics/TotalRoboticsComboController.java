package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.TotalRoboticsComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.TotalRoboticsComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/robotics")
public class TotalRoboticsComboController {

    @Autowired
    private TotalRoboticsComboService service;

    @Async
    @PostMapping("/total-robotics-combo")
    public CompletableFuture<ResponseEntity<?>> roboticsCombo (
            @RequestBody TotalRoboticsComboModel totalRoboticsComboModel
    ) {
        return service.saveComboRobotics(totalRoboticsComboModel)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success.getTid());
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/total-robotics-combo/{gid}")
    public CompletableFuture<ResponseEntity<?>> handleCheckGidforRoboticsCombo (
            @PathVariable("gid") String gid
    ) {
        return service.checkComboGid(gid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
