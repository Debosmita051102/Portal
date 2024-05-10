package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War15KgModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface War15KgRepository extends JpaRepository<War15KgModel, Long> {


    Optional<War15KgModel> findByGid1(String gid);

    Optional<War15KgModel> findByGid2(String gid);

    Optional<War15KgModel> findByGid3(String gid);

    Optional<War15KgModel> findByGid4(String gid);

    Optional<War15KgModel> findByGid5(String gid);

    Optional<War15KgModel> findByTid(String tid);
}
