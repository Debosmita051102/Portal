package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsWarComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War15KgModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War8KgModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.RoboticsWarComboRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsWarComboService {

    @Autowired
    private RoboticsWarComboRepository repository;
    @Autowired
    private War8KgService war8KgService;
    @Autowired
    private War15KgService war15KgService;

    @Async
    public CompletableFuture<RoboticsWarComboModel> saveWarCombo(RoboticsWarComboModel model) {
        if(model.getNumber1()!=null) {
            War8KgModel war8KgModel = new War8KgModel();
            war8KgModel.setTeamname(model.getTeamname());
            war8KgModel.setGid1(model.getGid1());
            war8KgModel.setGid2(model.getGid2());
            war8KgModel.setGid3(model.getGid3());
            war8KgModel.setGid4(model.getGid4());
            war8KgModel.setGid5(model.getGid5());
            war8KgModel.setNumber1(model.getNumber1());
            CompletableFuture<War8KgModel> war8KgModel1 = war8KgService.war8KgRd(war8KgModel);

            War15KgModel war15KgModel = new War15KgModel();
            war15KgModel.setTeamname(model.getTeamname());
            war15KgModel.setGid1(model.getGid1());
            war15KgModel.setGid2(model.getGid2());
            war15KgModel.setGid3(model.getGid3());
            war15KgModel.setGid4(model.getGid4());
            war15KgModel.setGid5(model.getGid5());
            war15KgModel.setNumber1(model.getNumber1());
            CompletableFuture<War15KgModel> war15KgModel1 = war15KgService.war15KgRd(war15KgModel);

            List<String> tid = List.of(war8KgModel1.join().getTid(), war15KgModel1.join().getTid());
            model.setTid(tid);

            return CompletableFuture.completedFuture(repository.save(model));
        }
        else
            throw new RuntimeException("Number is required ");

    }

    @Async
    public CompletableFuture<MrdModel> checkComboGid (String gid) {
        CompletableFuture<MrdModel> war8KgCheckGid = war8KgService.checkGid(gid);
        CompletableFuture<MrdModel> war15KgCheckGid = war15KgService.checkGid(gid);

        if(war8KgCheckGid.join().equals(war15KgCheckGid.join())){
            return CompletableFuture.completedFuture(war8KgCheckGid.join());
        }
        return CompletableFuture.completedFuture(war15KgCheckGid.join());
    }
}
