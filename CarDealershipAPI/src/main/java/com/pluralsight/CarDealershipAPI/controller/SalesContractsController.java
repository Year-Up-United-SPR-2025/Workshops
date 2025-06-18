package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.SalesContractDao;
import com.pluralsight.CarDealershipAPI.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales-contracts")
@CrossOrigin
public class SalesContractsController {

    private SalesContractDao salesContractDao;

    @Autowired
    public SalesContractsController(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<SalesContract> getSalesContract(@PathVariable int contractId) {
        try {
            SalesContract contract = salesContractDao.findById(contractId);
            if (contract == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<SalesContract> addSalesContract(@RequestBody SalesContract contract) {
        try {
            SalesContract addedContract = salesContractDao.addSalesContract(contract);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedContract);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}