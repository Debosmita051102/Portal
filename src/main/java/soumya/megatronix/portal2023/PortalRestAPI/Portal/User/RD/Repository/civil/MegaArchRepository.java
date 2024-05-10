package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.MegaArchModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface MegaArchRepository extends JpaRepository<MegaArchModel, Long> {
    List<MegaArchModel> findBySelectedcivilevent(String selectedcivilevent);



    Optional<MegaArchModel> findByGid1(String gid);

    Optional<MegaArchModel> findByGid2(String gid);

    Optional<MegaArchModel> findByGid3(String gid);

    Optional<MegaArchModel> findByGid4(String gid);

    Optional<MegaArchModel> findByGid5(String gid);

    Optional<MegaArchModel> findByTid(String tid);
}
