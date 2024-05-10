package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.coding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.coding.WebMindsModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebMindsRepository extends JpaRepository<WebMindsModel, Long> {
    List<WebMindsModel> findBySelectedcodingevent(String selectedcodingevent);

    Optional<WebMindsModel> findByGid1(String gid);

    Optional<WebMindsModel> findByGid2(String gid);

    Optional<WebMindsModel> findByTid(String tid);
}
