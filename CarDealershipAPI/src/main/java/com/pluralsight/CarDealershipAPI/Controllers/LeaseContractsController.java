package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Dao.lease_dao.LeaseDao;
import com.pluralsight.CarDealershipAPI.Models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leases_contracts")
public class LeaseContractsController {

    private final LeaseDao leaseDao;

    @Autowired
    public LeaseContractsController(LeaseDao leaseDao) {
        this.leaseDao = leaseDao;
    }

    /**
     * Retrieve all lease contracts.
     *
     * @return a list of lease contracts
     */
    @GetMapping
    public ResponseEntity<List<LeaseContract>> getAllLeases() {
        List<LeaseContract> leases = leaseDao.getAllLeases();
        return ResponseEntity.ok(leases);
    }

    /**
     * Retrieve a lease contract by its ID.
     *
     * @param id the ID of the lease contract
     * @return the lease contract if found, or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<LeaseContract> getLeaseById(@PathVariable int id) {
        LeaseContract lease = leaseDao.getLeaseById(id);
        return lease != null ?
                ResponseEntity.ok(lease) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Add a new lease contract.
     *
     * @param leaseContract the lease contract to add
     * @return the added lease contract with 201 Created status
     */
    @PostMapping
    public ResponseEntity<LeaseContract> addLease(@RequestBody LeaseContract leaseContract) {
        LeaseContract addedLease = leaseDao.addLease(leaseContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedLease);
    }

    /**
     * Update an existing lease contract.
     *
     * @param id            the ID of the lease contract to update
     * @param leaseContract the updated lease contract
     * @return the updated lease contract or 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<LeaseContract> updateLease(
            @PathVariable int id,
            @RequestBody LeaseContract leaseContract) {

        if (leaseContract.getLeaseId() != id) {
            return ResponseEntity.badRequest().build();
        }

        boolean updated = leaseDao.updateLease(leaseContract);
        return updated ?
                ResponseEntity.ok(leaseContract) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Delete a lease contract by its ID.
     *
     * @param id the ID of the lease contract to delete
     * @return 204 No Content if deleted, or 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLease(@PathVariable int id) {
        boolean deleted = leaseDao.deleteLease(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
