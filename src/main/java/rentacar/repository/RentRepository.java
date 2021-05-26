package rentacar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rentacar.entity.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer>{
    Collection<Rent> findByPriceLessThanOrderByPrice(Integer price);
    Collection<Rent> findByPriceGreaterThanOrderByPrice(Integer price);

}
