package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.Carrom;

import java.util.Optional;

@Repository
public interface CarromRepository extends JpaRepository<Carrom, Long> {
    
    Optional<Carrom> findByGid1 (String gid);

    Optional<Carrom> findByGid2 (String gid);

    Optional<Carrom> findByTid (String tid);
}
