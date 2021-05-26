package rentacar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rentacar.entity.Damage;

public interface DamageRepository extends JpaRepository<Damage, Integer>{
    Collection<Damage> findByTypeContainingIgnoreCase(String type);

}
