package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.MRD.Model.MrdModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface MrdRepository  extends JpaRepository<MrdModel,Long> {

    MrdModel getModelByGid(String gid);

    Optional<MrdModel> findByGid(String gid1);

    Optional<List<MrdModel>> findAllByEmail(String email);
}
