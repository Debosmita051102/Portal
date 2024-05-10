package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.CodezenModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodezenRepository extends JpaRepository<CodezenModel, Long> {
    List<CodezenModel> findBySelectedcodingevent(String selectedcodingevent);

    Optional<CodezenModel> findByGid1(String gid);

    Optional<CodezenModel> findByGid2(String gid);

    Optional<CodezenModel> findByTid(String tid);
}
