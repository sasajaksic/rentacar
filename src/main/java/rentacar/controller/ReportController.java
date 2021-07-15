package rentacar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentacar.entity.Damage;
import rentacar.entity.Fine;
import rentacar.entity.Rent;
import rentacar.entity.Report;
import rentacar.entity.dto.ReportDTO;
import rentacar.repository.DamageRepository;
import rentacar.repository.FineRepository;
import rentacar.repository.RentRepository;
import rentacar.repository.ReportRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@RestController
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private DamageRepository damageRepository;

    @Autowired
    private RentRepository rentRepository;

    @GetMapping("reports")
    public Collection<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @GetMapping("report/{id}")
    public Report getReport(@PathVariable("id") Integer id) {
        return reportRepository.getOne(id);
    }

    @GetMapping("reportType/{type}")
    public Collection<Report> getReportByType(@PathVariable("type") String type) {
        return reportRepository.findByTypeContainingIgnoreCase(type);
    }

    @GetMapping("reportFine/{fineId}")
    public Collection<Report> getReportByFine(@PathVariable("fineId") Integer fineId) {
        Fine fine = fineRepository.getOne(fineId);
        return reportRepository.findByFine(fine);
    }

    @GetMapping("reportDamage/{damageId}")
    public Collection<Report> getReportByDamage(@PathVariable("damageId") Integer damageId) {
        Damage damage = damageRepository.getOne(damageId);
        return reportRepository.findByDamage(damage);
    }

    @GetMapping("reportRent/{rentId}")
    public Collection<Report> getReportByRent(@PathVariable("rentId") Integer rentId) {
        Rent rent = rentRepository.getOne(rentId);
        return reportRepository.findByRent(rent);
    }


    @PostMapping("report")
    public ResponseEntity<Report> insertReport(@RequestBody ReportDTO reportDTO) throws ParseException {

        Report report = new Report();

        report.setType(reportDTO.getType());
        report.setFuelLevel(reportDTO.getFuelLevel());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = reportDTO.getDate().toString();
        Date date = formatter.parse(dateInString);

        report.setDate(date);

        Fine fine = fineRepository.getById(reportDTO.getFine());

        if(fine == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Damage damage = damageRepository.getById(reportDTO.getDamage());

        if(damage == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Rent rent = rentRepository.getById(reportDTO.getRent());

        if(rent == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        report.setFine(fine);
        report.setDamage(damage);
        report.setRent(rent);

        reportRepository.save(report);
        return new ResponseEntity<Report>(report, HttpStatus.OK);
    }

    @PutMapping("report/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @RequestBody ReportDTO reportDTO) throws ParseException {
        if(!reportRepository.existsById(id)) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Report report = reportRepository.getById(id);

        report.setType(reportDTO.getType());
        report.setFuelLevel(reportDTO.getFuelLevel());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String dateInString = reportDTO.getDate().toString();
        Date date = formatter.parse(dateInString);

        report.setDate(date);

        Fine fine = fineRepository.getById(reportDTO.getFine());

        if(fine == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Damage damage = damageRepository.getById(reportDTO.getDamage());

        if(damage == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Rent rent = rentRepository.getById(reportDTO.getRent());

        if(rent == null) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        report.setFine(fine);
        report.setDamage(damage);
        report.setRent(rent);

        reportRepository.save(report);
        return new ResponseEntity<Report>(report, HttpStatus.OK);
    }

    @DeleteMapping("report/{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable Integer id) {
        if(!reportRepository.existsById(id)) {
            return new ResponseEntity<Report>(HttpStatus.BAD_REQUEST);
        }

        Report report = reportRepository.getById(id);

        reportRepository.deleteById(id);
        return new ResponseEntity<Report>(report, HttpStatus.OK);
    }

}
