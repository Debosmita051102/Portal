package soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.Portal.Admin.model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository< Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
