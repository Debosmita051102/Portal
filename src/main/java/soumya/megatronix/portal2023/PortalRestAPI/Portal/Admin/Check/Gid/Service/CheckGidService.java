package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.Check.Gid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository.MrdRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CheckGidService {

    @Autowired
    private MrdRepository mrdRepository;

    public MrdModel checkGidAdmin (String gid) {
        Optional<MrdModel> mrdModel = mrdRepository.findByGid(gid);
        if(mrdModel.isPresent()) {
            return mrdModel.get();
        }
        throw new RuntimeException("GID not found");
    }

}
