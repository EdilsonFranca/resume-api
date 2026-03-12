package br.com.curriculopro.infra.persistence;

import br.com.curriculopro.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Long resumeId;
    private String externalReference;
    
    private LocalDateTime createdAt;

    public PaymentEntity(String email) {
        this.email = email;
        this.status = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void approve() {
        this.status = PaymentStatus.APPROVED;
    }
}