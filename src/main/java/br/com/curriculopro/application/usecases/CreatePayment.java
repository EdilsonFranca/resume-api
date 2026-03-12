package br.com.curriculopro.application.usecases;
import br.com.curriculopro.application.gateways.PaymentRepository;
import br.com.curriculopro.domain.payment.Payment;
import br.com.curriculopro.domain.payment.PaymentGateway;
import com.mercadopago.exceptions.MPException;

public class CreatePayment {
    private final PaymentGateway gateway;
    private final PaymentRepository repository;

    public CreatePayment(PaymentGateway gateway, PaymentRepository repository) {
        this.gateway    = gateway;
        this.repository = repository;
    }

    public String execute(String email, Long resumeId) throws MPException {
        Payment payment = new Payment(email, resumeId);
        Payment save    = repository.create(payment);
        return gateway.createPayment(save);
    }
}
