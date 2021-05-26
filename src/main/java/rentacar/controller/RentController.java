package rentacar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.*;
import rentacar.entity.dto.RentDTO;
import rentacar.repository.PaymentMethodRepository;
import rentacar.repository.RentRepository;
import rentacar.repository.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("rents")
    public Collection<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @GetMapping("rent/{id}")
    public Rent getRent(@PathVariable("id") Integer id) {
        return rentRepository.getOne(id);
    }

    @GetMapping("rentPriceLessThan/{price}")
    public Collection<Rent> getRentByPriceLessThan(@PathVariable("price") Integer price) {
        return rentRepository.findByPriceLessThanOrderByPrice(price);
    }

    @GetMapping("rentPriceGreaterThan/{price}")
    public Collection<Rent> getRentByPriceGreaterThan(@PathVariable("price") Integer price) {
        return rentRepository.findByPriceGreaterThanOrderByPrice(price);
    }

    @PostMapping("rent")
    public ResponseEntity<Rent> insertRent(@RequestBody RentDTO rentDTO) throws ParseException {

        Rent rent = new Rent();

        rent.setDeposit(rentDTO.getDeposit());
        rent.setPrice(rentDTO.getPrice());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = rentDTO.getDate().toString();
        Date date = formatter.parse(dateInString);

        rent.setDate(date);

        PaymentMethod paymentMethod = paymentMethodRepository.getById(rentDTO.getPaymentMethod());

        if(paymentMethod == null) {
            return new ResponseEntity<Rent>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationRepository.getById(rentDTO.getReservation());

        if(reservation == null) {
            return new ResponseEntity<Rent>(HttpStatus.BAD_REQUEST);
        }

        rent.setPaymentMethod(paymentMethod);
        rent.setReservation(reservation);

        rentRepository.save(rent);
        return new ResponseEntity<Rent>(rent, HttpStatus.OK);
    }

    @PutMapping("rent/{id}")
    public ResponseEntity<Rent> updateRent(@PathVariable Integer id, @RequestBody RentDTO rentDTO) throws ParseException {
        if (!rentRepository.existsById(id)) {
            return new ResponseEntity<Rent>(HttpStatus.NOT_FOUND);
        }

        Rent rent = rentRepository.getById(id);

        rent.setDeposit(rentDTO.getDeposit());
        rent.setPrice(rentDTO.getPrice());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = rentDTO.getDate().toString();
        Date date = formatter.parse(dateInString);

        rent.setDate(date);

        PaymentMethod paymentMethod = paymentMethodRepository.getById(rentDTO.getPaymentMethod());

        if(paymentMethod == null) {
            return new ResponseEntity<Rent>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = reservationRepository.getById(rentDTO.getReservation());

        if(reservation == null) {
            return new ResponseEntity<Rent>(HttpStatus.BAD_REQUEST);
        }

        rent.setPaymentMethod(paymentMethod);
        rent.setReservation(reservation);

        rentRepository.save(rent);
        return new ResponseEntity<Rent>(rent, HttpStatus.OK);
    }

    @DeleteMapping("rent/{id}")
    public ResponseEntity<Rent> deleteRent(@PathVariable Integer id) {
        if (!rentRepository.existsById(id)) {
            return new ResponseEntity<Rent>(HttpStatus.NOT_FOUND);
        }

        Rent rent = rentRepository.getById(id);
        rent.setReports(null);

        rentRepository.deleteById(id);
        return new ResponseEntity<Rent>(rent, HttpStatus.OK);
    }
}
