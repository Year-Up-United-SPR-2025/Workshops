package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Dao.Dealership.DealershipDao;
import com.pluralsight.CarDealershipAPI.Models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipsController {

    private final DealershipDao dealershipDao;

    @Autowired
    public DealershipsController(@Qualifier("jdbcDealershipDAO") DealershipDao dealershipDao) {
        this.dealershipDao = dealershipDao;
    }

    @GetMapping
    public ResponseEntity<List<Dealership>> getAllDealerships() {
        return ResponseEntity.ok(dealershipDao.getAllDealerships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealership> getDealershipById(@PathVariable int id) {
        Dealership dealership = dealershipDao.getDealershipById(id);
        return dealership != null ?
                ResponseEntity.ok(dealership) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Dealership> getDealershipByName(@PathVariable String name) {
        Dealership dealership = dealershipDao.getDealershipByName(name);
        return dealership != null ?
                ResponseEntity.ok(dealership) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Dealership> addDealership(@RequestBody Dealership dealership) {
        Dealership addedDealership = dealershipDao.addDealership(dealership);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDealership);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealership> updateDealership(
            @PathVariable int id,
            @RequestBody Dealership dealership) {

        if (dealership.getDealershipId() != id) {
            return ResponseEntity.badRequest().build();
        }

        boolean updated = dealershipDao.updateDealership(dealership);
        return updated ?
                ResponseEntity.ok(dealership) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable int id) {
        boolean deleted = dealershipDao.deleteDealership(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
