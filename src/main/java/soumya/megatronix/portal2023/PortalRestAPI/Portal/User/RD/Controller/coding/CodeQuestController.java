package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Controller.coding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.coding.CodeQuestService;

import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping("/megatronix/paridhi/user/coding")
public class CodeQuestController {

    @Qualifier("asyncExecutor")
    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;
    @Autowired
    private CodeQuestService service;

    @GetMapping("/code-quest")
    @Async
    public CompletableFuture<ResponseEntity<CodeQuestModel>> CodeQuestForm() {
        CompletableFuture<CodeQuestModel> future = CompletableFuture.supplyAsync(() -> {
            // Here you can perform any necessary processing to prepare your data
            CodeQuestModel user = new CodeQuestModel();
            return user;
        }, asyncTaskExecutor);
        return future.thenApply(result -> ResponseEntity.ok().body(result))
                .exceptionally(ex-> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PostMapping("/code-quest")
    @Async
    public CompletableFuture<ResponseEntity<?>> CodeQuestMember(@RequestBody CodeQuestModel member) {
        return service.CodeQuestRd(member).thenApply(savedMember -> {
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

    @GetMapping("/code-quest/{gid}")
    @Async
    public CompletableFuture<ResponseEntity<?>> validateCodeQuest(@PathVariable("gid") String gid) {
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