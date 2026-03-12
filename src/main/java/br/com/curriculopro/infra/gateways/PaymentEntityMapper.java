package br.com.curriculopro.infra.gateways;

import br.com.curriculopro.domain.payment.Payment;
import br.com.curriculopro.infra.persistence.PaymentEntity;

public class PaymentEntityMapper {

        public PaymentEntity toEntity(Payment payment) {
            return PaymentEntity.builder()
                    .id(payment.getId())
                    .email(payment.getEmail())
                    .status(payment.getStatus())
                    .createdAt(payment.getCreatedAt())
                    .build();
        }
        public Payment toDomain(PaymentEntity entity) {
            return new Payment(
                    entity.getId(),
                    entity.getResumeId(),
                    entity.getEmail(),
                    entity.getStatus(),
                    entity.getCreatedAt());
        }
}

