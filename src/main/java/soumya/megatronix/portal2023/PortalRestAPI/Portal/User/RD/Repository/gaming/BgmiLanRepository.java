package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.BgmiLan;

import java.util.Optional;

@Repository
public interface BgmiLanRepository extends JpaRepository<BgmiLan, Long> {


    Optional<BgmiLan> findByGid1(String gid);

    Optional<BgmiLan> findByGid2(String gid);

    Optional<BgmiLan> findByGid3(String gid);

    Optional<BgmiLan> findByGid4(String gid);

    Optional<BgmiLan> findByGid5(String gid);

    Optional<BgmiLan> findByGid6(String gid);

    Optional<BgmiLan> findByTid(String tid);
}
