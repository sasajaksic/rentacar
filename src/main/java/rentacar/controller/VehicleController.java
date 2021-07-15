package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Vehicle;
import rentacar.entity.dto.VehicleDTO;
import rentacar.repository.VehicleRepository;

import java.util.Collection;

@RestController
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("vehicles")
    public Collection<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("vehicle/{id}")
    public Vehicle getVehicle(@PathVariable("id") Integer id){
        return vehicleRepository.getOne(id);
    }

    @GetMapping("vehicleType/{type}")
    public Collection<Vehicle> getVehicleByType(@PathVariable("type") String type) {
        return vehicleRepository.findByTypeContainingIgnoreCase(type);
    }

    @GetMapping("vehicleBrand/{brand}")
    public Collection<Vehicle> getVehicleByBrand(@PathVariable("brand") String brand) {
        return vehicleRepository.findByBrandContainingIgnoreCase(brand);
    }

    @GetMapping("vehicleModel/{model}")
    public Collection<Vehicle> getVehicleByModel(@PathVariable("model") String model) {
        return vehicleRepository.findByModelContainingIgnoreCase(model);
    }

    @GetMapping("vehicleSeatNumber/{seatNumber}")
    public Collection<Vehicle> getVehicleBySeatNumber(@PathVariable("seatNumber") Integer seatNumber) {
        return vehicleRepository.findBySeatNumberEquals(seatNumber);
    }

    @GetMapping("vehicleGearbox/{gearbox}")
    public Collection<Vehicle> getVehicleByGearbox(@PathVariable("gearbox") String gearbox) {
        return vehicleRepository.findByGearboxContainingIgnoreCase(gearbox);
    }

    @GetMapping("vehiclePriceLessThan/{price}")
    public Collection<Vehicle> getVehicleByPriceLessThan(@PathVariable("price") Integer price) {
        return vehicleRepository.findByPriceLessThanOrderByPriceDesc(price);
    }

    @GetMapping("vehiclePriceGreaterThan/{price}")
    public Collection<Vehicle> getVehicleByPriceGreaterThan(@PathVariable("price") Integer price) {
        return vehicleRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @PostMapping("vehicle")
    public ResponseEntity<Vehicle> insertVehicle(@RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setDoorNumber(vehicleDTO.getDoorNumber());
        vehicle.setFuel(vehicleDTO.getFuel());
        vehicle.setGearbox(vehicleDTO.getGearbox());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setProductionYear(vehicleDTO.getProductionYear());
        vehicle.setSeatNumber(vehicleDTO.getSeatNumber());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setImage(vehicleDTO.getImage());

        vehicleRepository.save(vehicle);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    @PutMapping("vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO) {
        if(!vehicleRepository.existsById(id)){
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }

        Vehicle vehicle = vehicleRepository.getById(id);

        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setDoorNumber(vehicleDTO.getDoorNumber());
        vehicle.setFuel(vehicleDTO.getFuel());
        vehicle.setGearbox(vehicleDTO.getGearbox());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setProductionYear(vehicleDTO.getProductionYear());
        vehicle.setSeatNumber(vehicleDTO.getSeatNumber());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setImage(vehicleDTO.getImage());

        vehicleRepository.save(vehicle);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping("vehicle/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Integer id) {
        if(!vehicleRepository.existsById(id)){
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        }

        Vehicle vehicle = vehicleRepository.getById(id);

        vehicleRepository.deleteById(id);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }
}
