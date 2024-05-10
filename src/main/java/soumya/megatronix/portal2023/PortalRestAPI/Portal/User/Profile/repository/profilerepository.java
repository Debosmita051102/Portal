package soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.User.Profile.model.ProfileModel;

@Repository
public interface profilerepository extends JpaRepository<ProfileModel, Long> {

    ProfileModel findByGid(String gid);
}
