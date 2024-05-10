package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.TableTennis;

import java.util.Optional;

@Repository
public interface TableTennisRepository extends JpaRepository<TableTennis, Long> {

    Optional<TableTennis> findByGid1 (String gid);

    Optional<TableTennis> findByTid (String tid);
}
