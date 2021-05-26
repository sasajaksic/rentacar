package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Damage;
import rentacar.entity.Fine;
import rentacar.entity.dto.FineDTO;
import rentacar.repository.FineRepository;

import java.util.Collection;

@RestController
public class FineController {

    @Autowired
    private FineRepository fineRepository;

    @GetMapping("fines")
    public Collection<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @GetMapping("fine/{id}")
    public Fine getFine(@PathVariable("id") Integer id) {
        return fineRepository.getOne(id);
    }

    @GetMapping("fineType/{type}")
    public Collection<Fine> getFineByType(@PathVariable("type") String type) {
        return fineRepository.findByTypeContainingIgnoreCase(type);
    }

    @PostMapping("fine")
    public ResponseEntity<Fine> insertFine(@RequestBody FineDTO fineDTO) {

        Fine fine = new Fine();

        fine.setType(fineDTO.getType());
        fine.setDescription(fineDTO.getDescription());
        fine.setPrice(fineDTO.getPrice());

        fineRepository.save(fine);
        return new ResponseEntity<Fine>(fine, HttpStatus.OK);
    }

    @PutMapping("fine/{id}")
    public ResponseEntity<Fine> updateFine(@PathVariable Integer id, @RequestBody FineDTO fineDTO) {
        if (!fineRepository.existsById(id)) {
            return new ResponseEntity<Fine>(HttpStatus.NOT_FOUND);
        }

        Fine fine = fineRepository.getById(id);

        fine.setType(fineDTO.getType());
        fine.setDescription(fineDTO.getDescription());
        fine.setPrice(fineDTO.getPrice());

        fineRepository.save(fine);
        return new ResponseEntity<Fine>(fine, HttpStatus.OK);
    }

    @DeleteMapping("fine/{id}")
    public ResponseEntity<Fine> deleteFine(@PathVariable Integer id) {
        if (!fineRepository.existsById(id)) {
            return new ResponseEntity<Fine>(HttpStatus.NOT_FOUND);
        }

        Fine fine = fineRepository.getById(id);

        fineRepository.deleteById(id);
        return new ResponseEntity<Fine>(fine, HttpStatus.OK);
    }
}
