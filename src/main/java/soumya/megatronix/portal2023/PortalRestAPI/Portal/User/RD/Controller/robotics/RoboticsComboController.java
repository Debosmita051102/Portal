package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.RoboticsComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/robotics")
public class RoboticsComboController {

    @Autowired
    private RoboticsComboService roboticsComboService;

    @Async
    @PostMapping("/robotics-combo")
    public CompletableFuture<ResponseEntity<?>> roboticsCombo(
            @RequestBody RoboticsComboModel roboticsComboModel
    ) {
        return roboticsComboService.saveComboRobotics(roboticsComboModel)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success.getTid());
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/robotics-combo/{gid}")
    public CompletableFuture<ResponseEntity<?>> handleCheckGidforRoboticsCombo (
            @PathVariable("gid") String gid
    ) {
        return roboticsComboService.checkComboGid(gid)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success);
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
