package rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentacar.entity.Appuser;

import java.util.Collection;

public interface AppuserRepository extends JpaRepository<Appuser, Integer> {

    Collection<Appuser> findByEmailContainingIgnoreCase(String email);
}
