package rentacar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rentacar.entity.Damage;
import rentacar.entity.Fine;
import rentacar.entity.Rent;
import rentacar.entity.Report;

import javax.persistence.NamedQuery;

public interface ReportRepository extends JpaRepository<Report, Integer>{
    Collection<Report> findByTypeContainingIgnoreCase(String type);
    Collection<Report> findByFine(Fine fine);
    Collection<Report> findByDamage(Damage damage);
    Collection<Report> findByRent(Rent rent);

}
