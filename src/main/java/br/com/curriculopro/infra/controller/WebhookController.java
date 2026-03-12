package br.com.curriculopro.infra.controller;

import br.com.curriculopro.application.gateways.PaymentRepository;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final PaymentRepository repository;

    public WebhookController(PaymentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> receive(@RequestParam("data.id") String paymentId) throws MPException, MPApiException {

        PaymentClient client = new PaymentClient();
        Payment mpPayment    = client.get(Long.parseLong(paymentId));

        if ("approved".equals(mpPayment.getStatus())) {

            String externalRef = mpPayment.getExternalReference();

            repository.findByExternalReference(externalRef)
                    .ifPresent(payment -> {
                        payment.approve();
                        repository.create(payment);
                    });
        }

        return ResponseEntity.ok().build();
    }
}