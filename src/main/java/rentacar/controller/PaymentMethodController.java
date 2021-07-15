package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.PaymentMethod;
import rentacar.entity.dto.PaymentMethodDTO;
import rentacar.repository.PaymentMethodRepository;

import java.io.Console;
import java.util.Collection;

@RestController
@CrossOrigin
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @GetMapping("paymentMethods")
    public Collection<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @GetMapping("paymentMethod/{id}")
    public PaymentMethod getPaymentMethod(@PathVariable("id") Integer id) {
        return paymentMethodRepository.getOne(id);
    }

    @GetMapping("paymentMethodMethod/{method}")
    public Collection<PaymentMethod> getPaymentMethodByMethod(@PathVariable("method") String method) {
        return paymentMethodRepository.findByMethodContainingIgnoreCase(method);
    }

    @PostMapping("paymentMethod")
    public ResponseEntity<PaymentMethod> insertPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO) {

        PaymentMethod paymentMethod = new PaymentMethod();

        paymentMethod.setMethod(paymentMethodDTO.getMethod());
        paymentMethod.setDescription(paymentMethodDTO.getDescription());

        paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<PaymentMethod>(paymentMethod, HttpStatus.OK);
    }

    @PutMapping("paymentMethod/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Integer id, @RequestBody PaymentMethodDTO paymentMethodDTO) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
        }

        PaymentMethod paymentMethod = paymentMethodRepository.getById(id);

        paymentMethod.setMethod(paymentMethodDTO.getMethod());
        paymentMethod.setDescription(paymentMethodDTO.getDescription());

        paymentMethodRepository.save(paymentMethod);
        return new ResponseEntity<PaymentMethod>(paymentMethod, HttpStatus.OK);
    }

    @DeleteMapping("paymentMethod/{id}")
    public ResponseEntity<PaymentMethod> deletePaymentMethod(@PathVariable Integer id) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
        }

        PaymentMethod paymentMethod = paymentMethodRepository.getById(id);

        paymentMethodRepository.deleteById(id);
        return new ResponseEntity<PaymentMethod>(paymentMethod, HttpStatus.OK);
    }
}
