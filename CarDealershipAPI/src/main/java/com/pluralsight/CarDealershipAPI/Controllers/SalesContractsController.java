package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Dao.sales_dao.SalesDao;
import com.pluralsight.CarDealershipAPI.Models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales-contracts")
public class SalesContractsController {

    private final SalesDao salesDao;

    @Autowired
    public SalesContractsController(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    @GetMapping
    public List<SalesContract> getAll() {
        return salesDao.getAllSalesContracts();
    }

    @GetMapping("/{id}")
    public SalesContract getById(@PathVariable int id) {
        return salesDao.getSalesContractById(id);
    }

    @PostMapping
    public SalesContract create(@RequestBody SalesContract contract) {
        return salesDao.addSalesContract(contract);
    }
}
