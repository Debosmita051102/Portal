package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.model.ProfileModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.service.ProfileService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/user/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;


    @GetMapping("/getProfile")
    @Async
    public CompletableFuture<ResponseEntity< List<ProfileModel> >> handleGetProfileByEmail(
            @RequestParam String email
    ) throws Exception {

        return profileService.findProfile(email).thenApply(ResponseEntity::ok)
                .exceptionally(completableFuture -> {
                    System.out.println(completableFuture.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });

    }
}
