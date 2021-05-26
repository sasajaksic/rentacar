package rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.entity.Appuser;
import rentacar.entity.Location;

import java.util.Collection;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Collection<Location> findByCityEqualsIgnoreCase(String city);
    Collection<Location> findByAppuser(Appuser appuser);
}
