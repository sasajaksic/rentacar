package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Appuser;
import rentacar.entity.dto.AppuserDTO;
import rentacar.repository.AppuserRepository;

import java.util.Collection;

@RestController
public class AppuserController {
    @Autowired
    private AppuserRepository appuserRepository;

    @GetMapping("appusers")
    public Collection<Appuser> getAllUsers() {
        return appuserRepository.findAll();
    }

    @GetMapping("appuser/{id}")
    public Appuser getAppuser(@PathVariable("id") Integer id) {
        return appuserRepository.getOne(id);
    }

    @GetMapping("appuserEmail/{email}")
    public Collection<Appuser> getAppuserByEmail(@PathVariable("email") String email) {
        return appuserRepository.findByEmailContainingIgnoreCase(email);
    }

    @PostMapping("appuser")
    public ResponseEntity<Appuser> insertAppuser(@RequestBody AppuserDTO appuserDTO) {

        Appuser appuser = new Appuser();

        appuser.setAdress(appuserDTO.getAdress());
        appuser.setCardNumber(appuserDTO.getCardNumber());
        appuser.setDriverLicence(appuserDTO.getDriverLicence());
        appuser.setEmail(appuserDTO.getEmail());
        appuser.setFirstName(appuserDTO.getFirstName());
        appuser.setLastName(appuserDTO.getLastName());
        appuser.setPassword(appuserDTO.getPassword());
        appuser.setPhone(appuserDTO.getPhone());
        appuser.setRole(appuserDTO.getRole());
        appuser.setUsername(appuserDTO.getUsername());

        appuserRepository.save(appuser);
        return new ResponseEntity<>(appuser, HttpStatus.OK);
    }

    @PutMapping("appuser/{id}")
    public ResponseEntity<Appuser> updateAppuser(@PathVariable Integer id, @RequestBody AppuserDTO appuserDTO) {
        if (!appuserRepository.existsById(id)) {
            return new ResponseEntity<Appuser>(HttpStatus.NOT_FOUND);
        }

        Appuser appuser = appuserRepository.getById(id);

        appuser.setAdress(appuserDTO.getAdress());
        appuser.setCardNumber(appuserDTO.getCardNumber());
        appuser.setDriverLicence(appuserDTO.getDriverLicence());
        appuser.setEmail(appuserDTO.getEmail());
        appuser.setFirstName(appuserDTO.getFirstName());
        appuser.setLastName(appuserDTO.getLastName());
        appuser.setPassword(appuserDTO.getPassword());
        appuser.setPhone(appuserDTO.getPhone());
        appuser.setRole(appuserDTO.getRole());
        appuser.setUsername(appuserDTO.getUsername());

        appuserRepository.save(appuser);
        return new ResponseEntity<>(appuser, HttpStatus.OK);
    }

    @DeleteMapping("appuser/{id}")
    public ResponseEntity<Appuser> deleteAppuser(@PathVariable Integer id) {
        if (!appuserRepository.existsById(id)) {
            return new ResponseEntity<Appuser>(HttpStatus.NOT_FOUND);
        }

        Appuser appuser = appuserRepository.getById(id);

        appuserRepository.deleteById(id);
        return new ResponseEntity<Appuser>(appuser, HttpStatus.OK);
    }
}
