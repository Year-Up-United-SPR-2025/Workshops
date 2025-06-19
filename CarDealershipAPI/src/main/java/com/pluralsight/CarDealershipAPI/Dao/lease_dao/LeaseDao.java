package com.pluralsight.CarDealershipAPI.Dao.lease_dao;

import com.pluralsight.CarDealershipAPI.Models.LeaseContract;

import java.util.List;

public interface LeaseDao {
    List<LeaseContract> getAllLeases();

    LeaseContract getLeaseById(int leaseId);

    LeaseContract addLease(LeaseContract leaseContract);
}