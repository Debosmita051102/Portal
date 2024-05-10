package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboKlassikerModel;

import java.util.Optional;

@Repository
public interface RoboKlassikerRepository extends JpaRepository<RoboKlassikerModel, Long> {

    Optional<RoboKlassikerModel> findByGid1(String gid);

    Optional<RoboKlassikerModel> findByGid2(String gid);

    Optional<RoboKlassikerModel> findByGid3(String gid);

    Optional<RoboKlassikerModel> findByGid4(String gid);

    Optional<RoboKlassikerModel> findByGid5(String gid);

    Optional<RoboKlassikerModel> findByTid(String tid);
}
