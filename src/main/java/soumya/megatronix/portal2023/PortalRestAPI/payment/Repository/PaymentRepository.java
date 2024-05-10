package soumya.megatronix.portal2023.PortalRestAPI.payment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soumya.megatronix.portal2023.PortalRestAPI.payment.model.PaymentModel;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}
