package br.com.curriculopro.domain.payment;

import java.time.LocalDateTime;

public class Payment {

    private Long id;
    private Long resumeId;
    private String email;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public Payment(String email, Long resumeId) {
        this.resumeId   = resumeId;
        this.email      = email;
        this.status     = PaymentStatus.PENDING;
        this.createdAt  = LocalDateTime.now();
    }

    public Payment(Long id, Long resumeId, String email, PaymentStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.resumeId = resumeId;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void approve() {
        this.status = PaymentStatus.APPROVED;
    }

    public boolean isApproved() {
        return this.status == PaymentStatus.APPROVED;
    }

    public Long getId() {
        return id;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public String getEmail() {
        return email;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}