package br.com.curriculopro.application.gateways;

import br.com.curriculopro.domain.payment.Payment;
import br.com.curriculopro.domain.payment.PaymentGateway;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MercadoPagoGateway implements PaymentGateway {

    @Value("${mercadopago.access-token:dummy-token}")
    private String accessToken;

    public String createPayment(Payment payment) {
        try {
            MercadoPagoConfig.setAccessToken(accessToken);

            PreferenceClient client = new PreferenceClient();

            List<PreferenceItemRequest> items = new ArrayList<>();
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title("Download Currículo PDF - CurriculoPro")
                    .quantity(1)
                    .unitPrice(new BigDecimal("1.90"))
                    .currencyId("BRL")
                    .build();
            items.add(itemRequest);

            // URLs de retorno (Importante!)
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://seu-site.com/success")
                    .pending("https://seu-site.com/pending")
                    .failure("https://seu-site.com/failure")
                    .build();

            PreferenceRequest request = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .build();

            Preference preference = client.create(request);

            System.out.println(preference.getId()+ "    #######################################");
            return preference.getId(); // Esse ID vai para o Angular

        } catch (MPApiException e) {

            System.out.println("STATUS: " + e.getStatusCode());
            System.out.println("RESPONSE: " + e.getApiResponse().getContent());

            throw new RuntimeException(
                    "Erro ao criar pagamento no Mercado Pago: " + e.getApiResponse().getContent(), e
            );

        } catch (MPException e) {
            throw new RuntimeException("Erro de comunicação com Mercado Pago", e);
        }
    }


    public String createPayment2(Payment payment) {

        try {
            PreferenceItemRequest item =
                    PreferenceItemRequest.builder()
                            .title("Download Currículo PDF - CurriculoPro")
                            .quantity(1)
                            .unitPrice(new BigDecimal("9.90"))
                            .build();

            PreferencePayerRequest payer = PreferencePayerRequest.builder().email(payment.getEmail()).build();
            PreferenceRequest preferenceRequest = PreferenceRequest.builder().items(List.of(item)).payer(payer).build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return preference.getInitPoint();
        } catch (MPApiException e) {

            System.out.println("STATUS: " + e.getStatusCode());
            System.out.println("RESPONSE: " + e.getApiResponse().getContent());

            throw new RuntimeException(
                    "Erro ao criar pagamento no Mercado Pago: " + e.getApiResponse().getContent(), e
            );

        } catch (MPException e) {
            throw new RuntimeException("Erro de comunicação com Mercado Pago", e);
        }
    }
}

