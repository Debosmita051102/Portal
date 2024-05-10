package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Service.robotics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.*;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics.TotalRoboticsComboRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TotalRoboticsComboService {

    @Autowired
    private TotalRoboticsComboRepository totalRoboticsComboRepository;
    @Autowired
    private RoboKlassikerService roboKlassiker;
    @Autowired
    private TriathlonService triathlon;
    @Autowired
    private War8KgService war8Kg;
    @Autowired
    private War15KgService war15Kg;

    @Async
    public CompletableFuture<TotalRoboticsComboModel> saveComboRobotics (TotalRoboticsComboModel model) {
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

            War15KgModel war15KgModel = new War15KgModel();
            war15KgModel.setTeamname(model.getTeamname());
            war15KgModel.setGid1(model.getGid1());
            war15KgModel.setGid2(model.getGid2());
            war15KgModel.setGid3(model.getGid3());
            war15KgModel.setGid4(model.getGid4());
            war15KgModel.setGid5(model.getGid5());
            war15KgModel.setNumber1(model.getNumber1());
            CompletableFuture<War15KgModel> war15KgModel1 = war15Kg.war15KgRd(war15KgModel);

            tid.add(war15KgModel1.join().getTid());

            model.setTid(tid);
            return CompletableFuture.completedFuture(totalRoboticsComboRepository.save(model));
        }
        else
            throw new RuntimeException("Number1 is required");
    }

    @Async
    public CompletableFuture<MrdModel> checkComboGid (String gid) {
        CompletableFuture<MrdModel> roboKlassikerCheckGid = roboKlassiker.checkGid(gid);
        CompletableFuture<MrdModel> triathlonCheckGid = triathlon.checkGid(gid);
        CompletableFuture<MrdModel> war8KgCheckGid = war8Kg.checkGid(gid);
        CompletableFuture<MrdModel> war15KgCheckGid = war15Kg.checkGid(gid);

        if(roboKlassikerCheckGid.join().equals(triathlonCheckGid.join()) &&
                roboKlassikerCheckGid.join().equals(war8KgCheckGid.join()) &&
                roboKlassikerCheckGid.join().equals(war15KgCheckGid.join())
        ){
            return CompletableFuture.completedFuture(roboKlassikerCheckGid.join());
        }
        if(triathlonCheckGid.join().equals(war8KgCheckGid.join()) &&
                triathlonCheckGid.join().equals(war15KgCheckGid.join())
        ){
            return CompletableFuture.completedFuture(triathlonCheckGid.join());
        }
        if(war8KgCheckGid.join().equals(war15KgCheckGid.join())){
            return CompletableFuture.completedFuture(war8KgCheckGid.join());
        }
        return CompletableFuture.completedFuture(war15KgCheckGid.join());
    }
}
