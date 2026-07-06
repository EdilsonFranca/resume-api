package br.com.curriculopro.infra.controller;

import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.domain.enums.State;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
public record ResumeDetailsDto(Long id,
                               String name,
                               String phone,
                               String email,
                               String position,
                               String city,
                               State state,
                               String cep,
                               String summary,
                               List<Experience> experiences,
                               List<Education> educations,
                               List<Skills> skills,
                               List<Link> links,
                               List<Language> languages
) {
    @RestController
    @RequestMapping("/webhook")
    public static class MercadoPagoWebhookController {

        public MercadoPagoWebhookController() {
            MercadoPagoConfig.setAccessToken("SEU_ACCESS_TOKEN");
        }

        @PostMapping("/mp")
        public ResponseEntity<Void> handleMercadoPagoWebhook(@RequestBody Map<String, Object> payload) {

            try {

                Object dataObj = payload.get("data");

                if (!(dataObj instanceof Map<?, ?> data)) {
                    return ResponseEntity.badRequest().build();
                }

                Object idObj = data.get("id");

                if (idObj == null) {
                    return ResponseEntity.badRequest().build();
                }

                PaymentClient client = new PaymentClient();
                Payment payment = client.get(Long.parseLong(idObj.toString()));

                if ("approved".equals(payment.getStatus())) {

                    String email = payment.getPayer().getEmail();

                    log.info("Pagamento aprovado para {}", email);
                    // userService.activatePremium(email);
                }

            } catch (MPApiException | MPException e) {
                log.error("Erro ao processar webhook Mercado Pago", e);
            }

            return ResponseEntity.ok().build();
        }
    }
}

