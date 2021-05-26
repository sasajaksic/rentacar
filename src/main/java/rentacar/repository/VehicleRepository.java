package rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.entity.Vehicle;

import java.util.Collection;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Collection<Vehicle> findByTypeContainingIgnoreCase(String type);
    Collection<Vehicle> findByBrandContainingIgnoreCase(String brand);
    Collection<Vehicle> findByModelContainingIgnoreCase(String model);
    Collection<Vehicle> findBySeatNumberEquals(Integer seatNumber);
    Collection<Vehicle> findByGearboxContainingIgnoreCase(String gearbox);

    Collection<Vehicle> findByPriceLessThanOrderByPriceDesc(Integer price);
    Collection<Vehicle> findByPriceGreaterThanOrderByPriceDesc(Integer price);
}
