package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.*;
import rentacar.entity.dto.ReservationDTO;
import rentacar.repository.AppuserRepository;
import rentacar.repository.LocationRepository;
import rentacar.repository.ReservationRepository;
import rentacar.repository.VehicleRepository;

import java.rmi.server.RemoteServer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AppuserRepository appuserRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("reservations")
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("reservation/{id}")
    public Reservation getReservation(@PathVariable("id") Integer id) {
        return reservationRepository.getOne(id);
    }

    @GetMapping("reservationVehicle/{vehicleId}")
    public Collection<Reservation> getReservationByVehicle(@PathVariable("vehicleId") Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.getOne(vehicleId);
        return reservationRepository.findByVehicle(vehicle);
    }

    @PostMapping("reservation")
    public ResponseEntity<Reservation> insertReservation(@RequestBody ReservationDTO reservationDTO) throws ParseException {

        Reservation reservation = new Reservation();

        reservation.setPrice(reservationDTO.getPrice());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = reservationDTO.getDate().toString();
        Date date = formatter.parse(dateInString);
        reservation.setDate(date);

        String pickupDateInString = reservationDTO.getPickupDate().toString();
        Date pickupDate = formatter.parse(pickupDateInString);
        reservation.setPickupDate(pickupDate);

        String returnDateInString = reservationDTO.getReturnDate().toString();
        Date returnDate = formatter.parse(returnDateInString);
        reservation.setReturnDate(returnDate);

        Appuser client = appuserRepository.getById(reservationDTO.getClient());

        if(client == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Appuser manager = appuserRepository.getById(reservationDTO.getManager());

        if(manager == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Vehicle vehicle = vehicleRepository.getById(reservationDTO.getVehicle());

        if(vehicle == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Location location = locationRepository.getById(reservationDTO.getLocation());

        if(location == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        reservation.setClient(client);
        reservation.setManager(manager);
        reservation.setVehicle(vehicle);
        reservation.setLocation(location);

        reservationRepository.save(reservation);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @PutMapping("reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody ReservationDTO reservationDTO) throws ParseException {
        if(!reservationRepository.existsById(id)){
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationRepository.getById(id);

        reservation.setPrice(reservationDTO.getPrice());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = reservationDTO.getDate().toString();
        Date date = formatter.parse(dateInString);
        reservation.setDate(date);

        String pickupDateInString = reservationDTO.getPickupDate().toString();
        Date pickupDate = formatter.parse(pickupDateInString);
        reservation.setPickupDate(pickupDate);

        String returnDateInString = reservationDTO.getReturnDate().toString();
        Date returnDate = formatter.parse(returnDateInString);
        reservation.setReturnDate(returnDate);

        Appuser client = appuserRepository.getById(reservationDTO.getClient());

        if(client == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Appuser manager = appuserRepository.getById(reservationDTO.getManager());

        if(manager == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Vehicle vehicle = vehicleRepository.getById(reservationDTO.getVehicle());

        if(vehicle == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Location location = locationRepository.getById(reservationDTO.getLocation());

        if(location == null) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        reservation.setClient(client);
        reservation.setManager(manager);
        reservation.setVehicle(vehicle);
        reservation.setLocation(location);

        reservationRepository.save(reservation);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("reservation/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer id) {
        if(!reservationRepository.existsById(id)){
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationRepository.getById(id);

        reservationRepository.deleteById(id);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

}
