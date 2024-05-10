package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.ValorantLan;

import java.util.Optional;

@Repository
public interface ValorantLanRepository extends JpaRepository<ValorantLan, Long> {

    Optional<ValorantLan> findByGid1(String gid);

    Optional<ValorantLan> findByGid2(String gid);

    Optional<ValorantLan> findByGid3(String gid);

    Optional<ValorantLan> findByGid4(String gid);

    Optional<ValorantLan> findByGid5(String gid);

    Optional<ValorantLan> findByGid6(String gid);

    Optional<ValorantLan> findByTid(String tid);
}
