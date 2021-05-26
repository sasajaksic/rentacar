package rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.entity.Reservation;
import rentacar.entity.Vehicle;

import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Collection<Reservation> findByVehicle(Vehicle vehicle);
}
