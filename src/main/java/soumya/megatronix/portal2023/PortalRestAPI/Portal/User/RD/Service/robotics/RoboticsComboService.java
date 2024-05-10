package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboKlassikerModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.TriathlonModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.RoboticsComboRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsComboService {

    @Autowired
    private RoboticsComboRepository repository;
    @Autowired
    private RoboKlassikerService roboKlassiker;
    @Autowired
    private TriathlonService triathlon;

    @Async
    public CompletableFuture<RoboticsComboModel> saveComboRobotics(RoboticsComboModel model) {
        if(model.getNumber1()!=null) {
            List<String> tid = new ArrayList<>();

            RoboKlassikerModel roboKlassikerModel = new RoboKlassikerModel();
            roboKlassikerModel.setTeamname(model.getTeamname());
            roboKlassikerModel.setGid1(model.getGid1());
            roboKlassikerModel.setGid2(model.getGid2());
            roboKlassikerModel.setGid3(model.getGid3());
            roboKlassikerModel.setGid4(model.getGid4());
            roboKlassikerModel.setGid5(model.getGid5());
            roboKlassikerModel.setNumber1(model.getNumber1());
            CompletableFuture<RoboKlassikerModel> roboKlassikerModel1 = roboKlassiker.RoboKlassikerRd(roboKlassikerModel);

            tid.add(roboKlassikerModel1.join().getTid());

            TriathlonModel triathlonModel = new TriathlonModel();
            triathlonModel.setTeamname(model.getTeamname());
            triathlonModel.setGid1(model.getGid1());
            triathlonModel.setGid2(model.getGid2());
            triathlonModel.setGid3(model.getGid3());
            triathlonModel.setGid4(model.getGid4());
            triathlonModel.setGid5(model.getGid5());
            triathlonModel.setNumber1(model.getNumber1());
            CompletableFuture<TriathlonModel> triathlonModel1 = triathlon.TriathlonRd(triathlonModel);

            tid.add(triathlonModel1.join().getTid());

            model.setTid(tid);
            return CompletableFuture.completedFuture(repository.save(model));
        }
        else
            throw new RuntimeException("Number is required ");

    }

    @Async
    public CompletableFuture<MrdModel> checkComboGid (String gid) {
        CompletableFuture<MrdModel> roboKlassikerCheckGid = roboKlassiker.checkGid(gid);
        CompletableFuture<MrdModel> triathlonCheckGid = triathlon.checkGid(gid);

        if(roboKlassikerCheckGid.join().equals(triathlonCheckGid.join())){
            return CompletableFuture.completedFuture(roboKlassikerCheckGid.join());
        }
        return CompletableFuture.completedFuture(triathlonCheckGid.join());
    }
}
