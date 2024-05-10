package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanModel;

import java.util.Optional;

@Repository
public interface SetuBandhanRepository extends JpaRepository<SetuBandhanModel, Long> {


    Optional<SetuBandhanModel> findByGid1(String gid);

    Optional<SetuBandhanModel> findByGid2(String gid);

    Optional<SetuBandhanModel> findByGid3(String gid);

    Optional<SetuBandhanModel> findByTid(String tid);
}
