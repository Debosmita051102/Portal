package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.general.BingeQuiz;

import java.util.Optional;

@Repository
public interface BingeQuizRepository extends JpaRepository<BingeQuiz, Long> {

    Optional<BingeQuiz> findByGid1 (String gid);

    Optional<BingeQuiz> findByGid2 (String gid);

    Optional<BingeQuiz> findByTid (String tid);
}
