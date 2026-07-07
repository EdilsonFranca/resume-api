package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.PaymentRepository;
import br.com.curriculopro.domain.payment.Payment;
import br.com.curriculopro.domain.payment.PaymentGateway;
import br.com.curriculopro.domain.payment.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentTest {

    @Mock
    private PaymentGateway gateway;

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private CreatePayment createPayment;


    @Test
    void deveCriarPagamentoComSucesso() throws Exception {

        String email = "teste@email.com";
        Long resumeId = 1L;

        Payment paymentSalvo = new Payment(
                10L,
                resumeId,
                email,
                PaymentStatus.PENDING,
                null
        );

        when(repository.create(any(Payment.class)))
                .thenReturn(paymentSalvo);

        when(gateway.createPayment(paymentSalvo))
                .thenReturn("payment-id-123");


        String resultado = createPayment.execute(email, resumeId);


        assertEquals("payment-id-123", resultado);


        ArgumentCaptor<Payment> captor =
                ArgumentCaptor.forClass(Payment.class);

        verify(repository)
                .create(captor.capture());


        Payment paymentCriado = captor.getValue();

        assertEquals(email, paymentCriado.getEmail());
        assertEquals(resumeId, paymentCriado.getResumeId());
        assertEquals(
                PaymentStatus.PENDING,
                paymentCriado.getStatus()
        );


        verify(gateway)
                .createPayment(paymentSalvo);
    }
}