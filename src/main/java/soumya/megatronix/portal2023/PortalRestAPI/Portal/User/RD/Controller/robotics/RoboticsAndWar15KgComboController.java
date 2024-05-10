package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsAnd15KgComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.RoboticsAnd15KgComboService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/robotics")
public class RoboticsAndWar15KgComboController {

    @Autowired
    private RoboticsAnd15KgComboService service;

    @Async
    @PostMapping("/robotics-and-war-15kg-combo")
    public CompletableFuture<ResponseEntity<?>> roboticsCombo (
            @RequestBody RoboticsAnd15KgComboModel roboticsAnd15KgComboModel
    ) {
        return service.saveComboRobotics(roboticsAnd15KgComboModel)
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok().body(success.getTid());
                    else
                        return ResponseEntity.notFound().build();
                })
                .exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/robotics-and-war-15kg-combo/{gid}")
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
