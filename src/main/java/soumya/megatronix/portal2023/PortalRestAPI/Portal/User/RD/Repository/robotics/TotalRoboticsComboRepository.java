package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.TotalRoboticsComboModel;

@Repository
public interface TotalRoboticsComboRepository extends JpaRepository<TotalRoboticsComboModel, Long> {
}
