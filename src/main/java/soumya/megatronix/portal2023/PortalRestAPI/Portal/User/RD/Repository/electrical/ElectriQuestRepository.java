package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.electrical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.electrical.ElectriQuest;

import java.util.Optional;


@Repository
public interface ElectriQuestRepository extends JpaRepository<ElectriQuest, Long> {

    Optional<ElectriQuest> findByGid1(String gid);

    Optional<ElectriQuest> findByGid2(String gid);

    Optional<ElectriQuest> findByTid(String tid);
}
