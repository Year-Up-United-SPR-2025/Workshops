package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Dao.lease_dao.LeaseDao;
import com.pluralsight.CarDealershipAPI.Models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaseContracts")
public class LeaseContractsController {

    private final LeaseDao leaseDao;

    @Autowired
    public LeaseContractsController(LeaseDao leaseDao) {
        this.leaseDao = leaseDao;
    }

    @GetMapping
    public List<LeaseContract> getAllLeases() {
        return leaseDao.getAllLeases();
    }

    @GetMapping("/{id}")
    public LeaseContract getLeaseById(@PathVariable int id) {
        return leaseDao.getLeaseById(id);
    }

    @PostMapping
    public LeaseContract addLease(@RequestBody LeaseContract leaseContract) {
        return leaseDao.addLease(leaseContract);
    }
}
