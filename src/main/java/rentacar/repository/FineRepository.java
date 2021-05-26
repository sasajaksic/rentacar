package rentacar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rentacar.entity.Fine;

public interface FineRepository extends JpaRepository<Fine, Integer>{
    Collection<Fine> findByTypeContainingIgnoreCase(String type);

}
