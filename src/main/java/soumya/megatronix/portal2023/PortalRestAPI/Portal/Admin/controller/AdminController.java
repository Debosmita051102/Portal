package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.model.Loginmodel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.service.AdminService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("megatronix/paridhi/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

  @Async
  @PostMapping("/login")
  public CompletableFuture<ResponseEntity<?>> handleLogin (
      @RequestBody Loginmodel admin
  ) {
    return adminService.login(admin)
        .thenApply(result->{
          if (result != null) {
            return ResponseEntity.ok().body(admin);
          } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
          }
        })
        .exceptionally(ex->
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage())
        );
  }
}
