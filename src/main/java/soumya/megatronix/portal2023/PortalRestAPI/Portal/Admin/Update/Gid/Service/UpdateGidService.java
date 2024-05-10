package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Update.Gid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;
import soumya.megatronix.portal2023.PortalRestAPI.Verification.Email.Service.EmailService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UpdateGidService {

    @Autowired
    private MrdRepository mrdRepository;
    @Autowired
    private EmailService emailService;

    public MrdModel updateGid(String gid,Boolean paid) {

        Optional<MrdModel> mrdModel = mrdRepository.findByGid(gid);
        if(mrdModel.isEmpty()) {
            throw new RuntimeException("GID not found");
        } else {
            mrdModel.get().setPaid(paid);
            emailService.sendRegistrationUpdateMail(
                    mrdModel.get().getEmail(),
                    gid,
                    mrdModel.get().getName()
            );
            return mrdRepository.save(mrdModel.get());
        }
    }
}
