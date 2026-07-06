package br.com.curriculopro.infra.persistence;

import br.com.curriculopro.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Clock;
import java.time.LocalDateTime;

import static com.zaxxer.hikari.util.ClockSource.CLOCK;

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
    private static final Clock CLOCK = Clock.systemDefaultZone();

    public PaymentEntity(String email) {
        this.email = email;
        this.status = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now(CLOCK);
    }

    public void approve() {
        this.status = PaymentStatus.APPROVED;
    }
}