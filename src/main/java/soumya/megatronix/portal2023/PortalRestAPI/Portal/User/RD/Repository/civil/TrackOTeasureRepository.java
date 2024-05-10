package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.TrackOTeasureModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackOTeasureRepository extends JpaRepository<TrackOTeasureModel, Long> {

    Optional<TrackOTeasureModel> findByGid1(String gid);

    Optional<TrackOTeasureModel> findByGid2(String gid);

    Optional<TrackOTeasureModel> findByGid3(String gid);

    Optional<TrackOTeasureModel> findByTid(String tid);
}
