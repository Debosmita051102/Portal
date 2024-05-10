package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.model.Admin;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.model.Loginmodel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.repository.AdminRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Async
    public CompletableFuture<Loginmodel> login (
            Loginmodel loginmodel
    ) {
        Optional<Admin> admin = adminRepository.findByUsername(loginmodel.getUsername());
        if(admin.isPresent()) {
            if(loginmodel.getPassword().equals("megatronixparidhi2024msitmegaparidhi20031002202425")) {
                return CompletableFuture.completedFuture(loginmodel);
            } else {
               throw new RuntimeException("Invalid Password");
            }
        } else {
            throw new RuntimeException("Invalid Username");
        }
}
}
