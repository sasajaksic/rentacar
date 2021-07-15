package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Appuser;
import rentacar.entity.Location;
import rentacar.entity.dto.LocationDTO;
import rentacar.repository.AppuserRepository;
import rentacar.repository.LocationRepository;

import java.util.Collection;

@RestController
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AppuserRepository appuserRepository;

    @GetMapping("locations")
    public Collection<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("location/{id}")
    public Location getLocation(@PathVariable("id") Integer id) {
        return locationRepository.getOne(id);
    }

    @GetMapping("locationCity/{city}")
    public Collection<Location> getLocationByCity(@PathVariable("city") String city) {
        return locationRepository.findByCityEqualsIgnoreCase(city);
    }

    @GetMapping("locationManager/{manager}")
    public Collection<Location> getLocationByManager(@PathVariable("manager") Integer manager) {
        Appuser appuser = appuserRepository.getOne(manager);
        return locationRepository.findByAppuser(appuser);
    }

    @PostMapping("location")
    public ResponseEntity<Location> insertLocation(@RequestBody LocationDTO locationDTO) {

        Location location = new Location();

        location.setCity(locationDTO.getCity());
        location.setStreet(locationDTO.getStreet());
        location.setNumber(locationDTO.getNumber());
        location.setImage(locationDTO.getImage());

        Appuser manager = appuserRepository.getById(locationDTO.getManager());

        if (manager == null || manager.getRole() == 0) {
            return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
        }

        location.setManager(manager);

        locationRepository.save(location);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    @PutMapping("location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Integer id, @RequestBody LocationDTO locationDTO) {
        if (!locationRepository.existsById(id)) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        Location location = locationRepository.getById(id);

        location.setCity(locationDTO.getCity());
        location.setStreet(locationDTO.getStreet());
        location.setNumber(locationDTO.getNumber());
        location.setImage(locationDTO.getImage());

        Appuser manager = appuserRepository.getById(locationDTO.getManager());

        if (manager == null || manager.getRole() == 0) {
            return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
        }

        location.setManager(manager);

        locationRepository.save(location);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    @DeleteMapping("location/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Integer id) {
        if (!locationRepository.existsById(id)) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        Location location = locationRepository.getById(id);

        locationRepository.deleteById(id);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

}
