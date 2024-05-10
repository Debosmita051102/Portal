package soumya.megatronix.portal2023.PortalRestAPI.payment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@Getter
@Setter
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String paymentId;
    private String orderId;
    private String paymentSignature;
    private boolean paymentStatus;
    private Integer paymentAmount;
    private Date paymentDate;


    public PaymentModel(String paymentId, boolean paymentStatus, Integer paymentAmount, Date paymentDate, String name, String orderId, String paymentSignature){
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.name = name;
        this.orderId = orderId;
        this.paymentSignature = paymentSignature;

    }
}
