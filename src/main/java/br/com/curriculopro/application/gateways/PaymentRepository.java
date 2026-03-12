package br.com.curriculopro.application.gateways;

import br.com.curriculopro.domain.payment.Payment;

import java.util.Optional;

public interface PaymentRepository {
    Payment create(Payment payment);

    Optional<Payment> findByExternalReference(String externalRef);

}







