package soumya.megatronix.portal2023.PortalRestAPI.payment.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumya.megatronix.portal2023.PortalRestAPI.payment.Repository.PaymentRepository;
import soumya.megatronix.portal2023.PortalRestAPI.payment.model.PaymentModel;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {

    @Value("${RAZORPAY_KEY_ID}")
    private String keyId;

    @Value("${RAZORPAY_SECRET_KEY}")
    private String secretKey;

    private final String currency = "INR";

    @Autowired
    private PaymentRepository paymentRepository;

    @Async
    public CompletableFuture<String> createPayment (Integer amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId, secretKey);

            String receiptId = "txn-" + UUID.randomUUID().toString();

            JSONObject options = new JSONObject();
            options.put("amount", amount);
            options.put("currency", currency);
            options.put("receipt", receiptId);

            Order order = razorpayClient.orders.create(options);

            return CompletableFuture.completedFuture(order.get("id"));
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Async
    public CompletableFuture<String> verifyPayment (
            String orderId,
            String paymentId,
            String signature,
            String name,
            Integer amount
    ) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(keyId, secretKey);

            JSONObject attributes = new JSONObject();
            attributes.put("razorpay_order_id", orderId);
            attributes.put("razorpay_payment_id", paymentId);
            attributes.put("razorpay_signature", signature);

            boolean status = Utils.verifyPaymentSignature(attributes, secretKey);

            if(status) {
                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setOrderId(orderId);
                paymentModel.setPaymentId(paymentId);
                paymentModel.setPaymentSignature(signature);
                paymentModel.setPaymentStatus(true);
                paymentModel.setPaymentDate(new Date());
                paymentModel.setPaymentAmount(amount);
                paymentModel.setName(name);
                paymentRepository.save(paymentModel);

                return CompletableFuture.completedFuture(paymentId);
            } else {
                throw new RuntimeException("Payment Failed");
            }
        } catch ( RazorpayException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
