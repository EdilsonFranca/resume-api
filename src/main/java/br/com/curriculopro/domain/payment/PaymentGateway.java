package br.com.curriculopro.domain.payment;

import com.mercadopago.exceptions.MPException;

public interface PaymentGateway {
    String createPayment(Payment payment) throws MPException;
}
