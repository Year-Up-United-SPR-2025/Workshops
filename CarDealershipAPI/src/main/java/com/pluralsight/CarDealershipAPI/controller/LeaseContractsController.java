package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.LeaseContractDao;
import com.pluralsight.CarDealershipAPI.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lease-contracts")
@CrossOrigin
public class LeaseContractsController {

    private LeaseContractDao leaseContractDao;

    @Autowired
    public LeaseContractsController(LeaseContractDao leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<LeaseContract> getLeaseContract(@PathVariable int contractId) {
        try {
            LeaseContract contract = leaseContractDao.findById(contractId);
            if (contract == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<LeaseContract> addLeaseContract(@RequestBody LeaseContract contract) {
        try {
            LeaseContract addedContract = leaseContractDao.addLeaseContract(contract);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedContract);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}