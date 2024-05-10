package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War8KgModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics.War8KgService;

import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping("/megatronix/paridhi/user/robotics")
public class War8KgController {

    @Qualifier("asyncExecutor")
    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    @Autowired
    private War8KgService service;

    @GetMapping("/war-8kg")
    @Async
    public CompletableFuture<ResponseEntity<War8KgModel>> war8KgForm() {
        CompletableFuture<War8KgModel> future = CompletableFuture.supplyAsync(() -> {
            // Here you can perform any necessary processing to prepare your data
            War8KgModel user = new War8KgModel();
            return user;
        }, asyncTaskExecutor);
        return future.thenApply(result -> ResponseEntity.ok().body(result))
                .exceptionally(ex-> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PostMapping("/war-8kg")
    @Async
    public CompletableFuture<ResponseEntity<?>> war8KgMember(@RequestBody War8KgModel member) {
        return service.war8KgRd(member).thenApply(savedMember -> {
            if (savedMember != null && savedMember.getTid() != null) {
                return ResponseEntity.ok().body(savedMember.getTid());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }).exceptionally(ex -> {
            // Log the exception or handle it in some other way
            System.err.println("An error occurred: " + ex.getMessage());
            // Return a default value
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        });
    }

    @GetMapping("/war-8kg/{gid}")
    @Async
    public CompletableFuture<ResponseEntity<?>> validateWar8Kg(@PathVariable("gid") String gid) {
        return service.checkGid(gid).thenApply(savedMember -> {
            if (savedMember != null && savedMember.getGid() != null) {
                return ResponseEntity.ok().body(savedMember.getName());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }).exceptionally(ex -> {
            // Log the exception or handle it in some other way
            System.err.println("An error occurred: " + ex.getMessage());
            // Return a default value
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        });
    }
}