package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Repository.civil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.RD.Model.civil.SetuBandhanAndToTComboModel;

@Repository
public interface SetuBandhanAndToTComboRepository extends JpaRepository< SetuBandhanAndToTComboModel, Long > {
}
