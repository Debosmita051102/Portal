package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.War8KgModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface War8KgRepository extends JpaRepository<War8KgModel, Long> {


    Optional<War8KgModel> findByGid1(String gid);

    Optional<War8KgModel> findByGid2(String gid);

    Optional<War8KgModel> findByGid3(String gid);

    Optional<War8KgModel> findByGid4(String gid);

    Optional<War8KgModel> findByGid5(String gid);

    Optional<War8KgModel> findByTid(String tid);
}
