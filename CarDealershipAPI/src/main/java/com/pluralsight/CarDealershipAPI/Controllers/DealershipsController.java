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
    public List<Dealership> getAllDealerships() {
        return dealershipDao.getAllDealerships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealership> getDealershipById(@PathVariable int id) {
        Dealership dealership = dealershipDao.getDealershipById(id);
        return ResponseEntity.ok(dealership);
    }

    @PostMapping
    public ResponseEntity<Dealership> addDealership(@RequestBody Dealership dealership) {
        Dealership savedDealership = dealershipDao.addDealership(dealership);
        return new ResponseEntity<>(savedDealership, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealership> updateDealership(@PathVariable int id, @RequestBody Dealership dealership) {
        dealership.setDealershipId(id);
        boolean updated = dealershipDao.updateDealership(dealership);
        return updated ? ResponseEntity.ok(dealership) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable int id) {
        boolean deleted = dealershipDao.deleteDealership(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
