package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.LineTrekkerModel;

import java.util.Optional;

@Repository
public interface LineTrekkerRepository extends JpaRepository<LineTrekkerModel, Long> {

    Optional<LineTrekkerModel> findByGid1(String gid);

    Optional<LineTrekkerModel> findByGid2(String gid);

    Optional<LineTrekkerModel> findByGid3(String gid);

    Optional<LineTrekkerModel> findByGid4(String gid);

    Optional<LineTrekkerModel> findByGid5(String gid);

    Optional<LineTrekkerModel> findByTid(String tid);
}
