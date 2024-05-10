package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsAnd8KgComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.RoboticsAnd8KgComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/robotics")
public class RoboticsAndWar8KgComboController {

    @Autowired
    private RoboticsAnd8KgComboService service;

    @Async
    @PostMapping("/robotics-and-war-8kg-combo")
    public CompletableFuture<ResponseEntity<?>> roboticsCombo (
            @RequestBody RoboticsAnd8KgComboModel roboticsAnd8KgComboModel
    ) {
        return service.saveComboRobotics(roboticsAnd8KgComboModel)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success.getTid());
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/robotics-and-war-8kg-combo/{gid}")
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
