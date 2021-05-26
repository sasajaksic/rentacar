package rentacar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rentacar.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
    Collection<PaymentMethod> findByMethodContainingIgnoreCase(String method);

}
