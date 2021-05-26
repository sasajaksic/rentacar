package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Appuser;
import rentacar.entity.Damage;
import rentacar.entity.dto.DamageDTO;
import rentacar.repository.DamageRepository;

import java.util.Collection;

@RestController
public class DamageController {

    @Autowired
    private DamageRepository damageRepository;

    @GetMapping("damages")
    public Collection<Damage> getAllDamages() {
        return damageRepository.findAll();
    }

    @GetMapping("damage/{id}")
    public Damage getDamage(@PathVariable("id") Integer id) {
        return damageRepository.getOne(id);
    }

    @GetMapping("damageType/{type}")
    public Collection<Damage> getDamageByType(@PathVariable("type") String type) {
        return damageRepository.findByTypeContainingIgnoreCase(type);
    }

    @PostMapping("damage")
    public ResponseEntity<Damage> insertDamage(@RequestBody DamageDTO damageDTO) {

        Damage damage = new Damage();

        damage.setType(damageDTO.getType());
        damage.setDescription(damageDTO.getDescription());
        damage.setPrice(damageDTO.getPrice());

        damageRepository.save(damage);
        return new ResponseEntity<Damage>(damage, HttpStatus.OK);
    }

    @PutMapping("damage/{id}")
    public ResponseEntity<Damage> updateDamage(@PathVariable Integer id, @RequestBody DamageDTO damageDTO) {
        if (!damageRepository.existsById(id)) {
            return new ResponseEntity<Damage>(HttpStatus.NOT_FOUND);
        }

        Damage damage = damageRepository.getById(id);

        damage.setType(damageDTO.getType());
        damage.setDescription(damageDTO.getDescription());
        damage.setPrice(damageDTO.getPrice());

        damageRepository.save(damage);
        return new ResponseEntity<Damage>(damage, HttpStatus.OK);
    }

    @DeleteMapping("damage/{id}")
    public ResponseEntity<Damage> deleteDamage(@PathVariable Integer id) {
        if (!damageRepository.existsById(id)) {
            return new ResponseEntity<Damage>(HttpStatus.NOT_FOUND);
        }

        Damage damage = damageRepository.getById(id);

        damageRepository.deleteById(id);
        return new ResponseEntity<Damage>(damage, HttpStatus.OK);
    }
}
