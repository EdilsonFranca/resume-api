package br.com.curriculopro.infra.gateways;

import br.com.curriculopro.application.gateways.PaymentRepository;
import br.com.curriculopro.domain.payment.Payment;
import br.com.curriculopro.infra.persistence.PaymentEntity;
import br.com.curriculopro.infra.persistence.RepositoryPayment;

import java.util.Optional;

public class PaymentRepositoryJpa implements PaymentRepository {

    private final RepositoryPayment repositorio;
    private final PaymentEntityMapper mapper;

    public PaymentRepositoryJpa(RepositoryPayment repositorio, PaymentEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper      = mapper;
    }

    @Override
    public Payment create(Payment payment) {
        PaymentEntity entity = mapper.toEntity(payment);
        PaymentEntity save   = repositorio.save(entity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<Payment> findByExternalReference(String externalRef) {
        return repositorio.findByExternalReference(externalRef);
    }
}

