package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.TriathlonModel;

import java.util.Optional;

@Repository
public interface TriathlonRepository extends JpaRepository<TriathlonModel, Long> {

    Optional<TriathlonModel> findByGid1(String gid);

    Optional<TriathlonModel> findByGid2(String gid);

    Optional<TriathlonModel> findByGid3(String gid);

    Optional<TriathlonModel> findByGid4(String gid);

    Optional<TriathlonModel> findByGid5(String gid);

    Optional<TriathlonModel> findByTid(String tid);
}
