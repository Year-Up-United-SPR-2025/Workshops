package com.pluralsight.CarDealershipAPI.Dao.lease_dao;

import com.pluralsight.CarDealershipAPI.Models.LeaseContract;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleLeaseDAO implements LeaseDao {

    private List<LeaseContract> leaseContracts;

    public SimpleLeaseDAO(){
        // Initialize the categories list with some sample data
        leaseContracts = new ArrayList<>();
        leaseContracts.add(new LeaseContract(1, ""))
    }
}
