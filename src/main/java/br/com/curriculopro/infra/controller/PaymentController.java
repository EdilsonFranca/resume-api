package br.com.curriculopro.infra.controller;

import br.com.curriculopro.application.usecases.CreatePayment;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final CreatePayment createPayment;

    public PaymentController(CreatePayment createPayment) {
        this.createPayment = createPayment;
    }

    @PostMapping
    public ResponseEntity<?> pagar(@RequestBody PaymentRequest request) throws MPException {

        String email  = request.email();
        Long resumeId = request.resumeId();
        String execute = createPayment.execute(email, resumeId);
        return ResponseEntity.ok(new PaymentResponseDto(execute));
    }

}