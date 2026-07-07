package br.com.curriculopro.infra.controller;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MercadoPagoWebhookControllerTest {


    @Mock
    private PaymentClient client;


    @InjectMocks
    private ResumeDetailsDto.MercadoPagoWebhookController controller;


    @Test
    void deveRetornarBadRequestQuandoDataNaoExistir() {

        ResponseEntity<Void> response =
                controller.handleMercadoPagoWebhook(
                        Map.of()
                );


        assertEquals(
                400,
                response.getStatusCode().value()
        );
    }


    @Test
    void deveRetornarBadRequestQuandoIdNaoExistir() {

        ResponseEntity<Void> response =
                controller.handleMercadoPagoWebhook(
                        Map.of(
                                "data",
                                Map.of()
                        )
                );


        assertEquals(
                400,
                response.getStatusCode().value()
        );
    }
}