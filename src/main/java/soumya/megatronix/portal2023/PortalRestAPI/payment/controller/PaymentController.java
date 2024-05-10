package soumya.megatronix.portal2023.PortalRestAPI.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumya.megatronix.portal2023.PortalRestAPI.payment.service.PaymentService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/megatronix/paridhi/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Async
    @GetMapping("/{amount}")
    public CompletableFuture<ResponseEntity<String>> createPayment (
            @PathVariable(name = "amount") Integer amount
    ) {
        return paymentService.createPayment(amount)
                .thenApply(orderId -> ResponseEntity.ok().body(orderId))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @Async
    @PostMapping("/verify")
    public CompletableFuture<ResponseEntity<String>> verifyPayment (
            @RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "paymentId") String paymentId,
            @RequestParam(name = "signature") String signature,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "amount") Integer amount
    ) {
        return paymentService.verifyPayment(orderId, paymentId, signature, name, amount)
                .thenApply(referenceNo -> ResponseEntity.ok().body(referenceNo))
                .exceptionally( ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }
}