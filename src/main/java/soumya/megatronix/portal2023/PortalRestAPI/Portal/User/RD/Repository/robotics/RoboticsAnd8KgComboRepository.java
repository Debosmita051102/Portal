package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.robotics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.robotics.RoboticsAnd8KgComboModel;

@Repository
public interface RoboticsAnd8KgComboRepository extends JpaRepository<RoboticsAnd8KgComboModel, Long> {
}
