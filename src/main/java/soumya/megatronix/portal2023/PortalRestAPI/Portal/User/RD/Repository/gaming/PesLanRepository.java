package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.gaming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.gaming.PesLan;

import java.util.Optional;

@Repository
public interface PesLanRepository extends JpaRepository<PesLan, Long> {


    Optional<PesLan> findByGid1(String gid);

    Optional<PesLan> findByGid2(String gid);

    Optional<PesLan> findByGid3(String gid);

    Optional<PesLan> findByGid4(String gid);

    Optional<PesLan> findByGid5(String gid);

    Optional<PesLan> findByGid6(String gid);

    Optional<PesLan> findByTid(String tid);
}
