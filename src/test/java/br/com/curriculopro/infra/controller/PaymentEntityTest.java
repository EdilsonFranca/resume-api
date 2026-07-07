package br.com.curriculopro.infra.controller;

import br.com.curriculopro.domain.payment.PaymentStatus;
import br.com.curriculopro.infra.persistence.PaymentEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {


    @Test
    void deveCriarPagamentoComStatusPendente() {

        String email = "teste@email.com";


        PaymentEntity payment =
                new PaymentEntity(email);


        assertEquals(
                email,
                payment.getEmail()
        );

        assertEquals(
                PaymentStatus.PENDING,
                payment.getStatus()
        );

        assertNotNull(
                payment.getCreatedAt()
        );
    }


    @Test
    void deveAprovarPagamento() {

        PaymentEntity payment =
                new PaymentEntity("teste@email.com");


        payment.approve();


        assertEquals(
                PaymentStatus.APPROVED,
                payment.getStatus()
        );
    }


    @Test
    void deveCriarPagamentoComBuilder() {

        LocalDateTime data =
                LocalDateTime.now();


        PaymentEntity payment =
                PaymentEntity.builder()
                        .id(1L)
                        .email("teste@email.com")
                        .resumeId(10L)
                        .externalReference("123")
                        .status(PaymentStatus.PENDING)
                        .createdAt(data)
                        .build();


        assertEquals(
                1L,
                payment.getId()
        );

        assertEquals(
                "teste@email.com",
                payment.getEmail()
        );

        assertEquals(
                10L,
                payment.getResumeId()
        );

        assertEquals(
                "123",
                payment.getExternalReference()
        );

        assertEquals(
                PaymentStatus.PENDING,
                payment.getStatus()
        );

        assertEquals(
                data,
                payment.getCreatedAt()
        );
    }
}