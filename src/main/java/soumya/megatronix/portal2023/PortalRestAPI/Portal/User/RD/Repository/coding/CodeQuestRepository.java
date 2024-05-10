package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodeQuestModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeQuestRepository extends JpaRepository<CodeQuestModel, Long> {
    List<CodeQuestModel> findBySelectedcodingevent(String selectedcodingevent);

    Optional<CodeQuestModel> findByGid1(String gid);

    Optional<CodeQuestModel> findByGid2(String gid);

    Optional<CodeQuestModel> findByTid(String tid);
}