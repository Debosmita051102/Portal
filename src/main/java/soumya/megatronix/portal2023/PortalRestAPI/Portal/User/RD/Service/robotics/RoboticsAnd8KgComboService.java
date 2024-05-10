package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboKlassikerModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsAnd8KgComboModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.TriathlonModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War8KgModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.RoboticsAnd8KgComboRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RoboticsAnd8KgComboService {

    @Autowired
    private RoboticsAnd8KgComboRepository roboticsAnd8KgComboRepository;
    @Autowired
    private RoboKlassikerService roboKlassiker;
    @Autowired
    private TriathlonService triathlon;
    @Autowired
    private War8KgService war8Kg;

    @Async
    public CompletableFuture<RoboticsAnd8KgComboModel> saveComboRobotics(RoboticsAnd8KgComboModel model) {
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

            War8KgModel war8KgModel = new War8KgModel();
            war8KgModel.setTeamname(model.getTeamname());
            war8KgModel.setGid1(model.getGid1());
            war8KgModel.setGid2(model.getGid2());
            war8KgModel.setGid3(model.getGid3());
            war8KgModel.setGid4(model.getGid4());
            war8KgModel.setGid5(model.getGid5());
            war8KgModel.setNumber1(model.getNumber1());
            CompletableFuture<War8KgModel> war8KgModel1 = war8Kg.war8KgRd(war8KgModel);

            tid.add(war8KgModel1.join().getTid());

            model.setTid(tid);
            return CompletableFuture.completedFuture(roboticsAnd8KgComboRepository.save(model));
        }
        else
            throw new RuntimeException("Number1 is null");
    }

    @Async
    public CompletableFuture<MrdModel> checkComboGid (String gid) {
        CompletableFuture<MrdModel> roboKlassikerCheckGid = roboKlassiker.checkGid(gid);
        CompletableFuture<MrdModel> triathlonCheckGid = triathlon.checkGid(gid);
        CompletableFuture<MrdModel> war8KgCheckGid = war8Kg.checkGid(gid);

        if(roboKlassikerCheckGid.join().equals(triathlonCheckGid.join()) &&
                roboKlassikerCheckGid.join().equals(war8KgCheckGid.join())
        ){
            return CompletableFuture.completedFuture(roboKlassikerCheckGid.join());
        }
        if(triathlonCheckGid.join().equals(war8KgCheckGid.join())){
            return CompletableFuture.completedFuture(triathlonCheckGid.join());
        }
        return CompletableFuture.completedFuture(war8KgCheckGid.join());
    }
}
