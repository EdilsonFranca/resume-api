package br.com.curriculopro.infra.controller;

import br.com.curriculopro.application.gateways.PaymentRepository;
import br.com.curriculopro.domain.payment.Payment;
import com.mercadopago.client.payment.PaymentClient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebhookControllerTest {


    @Mock
    private PaymentRepository repository;


    @Mock
    private PaymentClient client;


    @InjectMocks
    private WebhookController controller;


    @Test
    void deveAprovarPagamentoQuandoMercadoPagoRetornarApproved()
            throws Exception {


        com.mercadopago.resources.payment.Payment mpPayment =
                mock(com.mercadopago.resources.payment.Payment.class);


        Payment payment =
                mock(Payment.class);


        when(client.get(123L))
                .thenReturn(mpPayment);


        when(mpPayment.getStatus())
                .thenReturn("approved");


        when(mpPayment.getExternalReference())
                .thenReturn("REF123");


        when(repository.findByExternalReference("REF123"))
                .thenReturn(Optional.of(payment));


        ResponseEntity<Void> response =
                controller.receive("123");


        assertEquals(
                200,
                response.getStatusCode().value()
        );


        verify(payment).approve();


        verify(repository)
                .create(payment);
    }


    @Test
    void deveRetornarOkQuandoPagamentoNaoForAprovado()
            throws Exception {


        com.mercadopago.resources.payment.Payment mpPayment =
                mock(com.mercadopago.resources.payment.Payment.class);


        when(client.get(123L))
                .thenReturn(mpPayment);


        when(mpPayment.getStatus())
                .thenReturn("pending");


        ResponseEntity<Void> response =
                controller.receive("123");


        assertEquals(
                200,
                response.getStatusCode().value()
        );


        verify(repository, never())
                .create(any());
    }
}