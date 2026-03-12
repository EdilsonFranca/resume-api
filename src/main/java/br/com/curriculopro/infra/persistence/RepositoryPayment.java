package br.com.curriculopro.infra.persistence;

import br.com.curriculopro.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryPayment extends JpaRepository<PaymentEntity, Long> {
    Optional<Payment>  findByExternalReference(String externalRef);
}



