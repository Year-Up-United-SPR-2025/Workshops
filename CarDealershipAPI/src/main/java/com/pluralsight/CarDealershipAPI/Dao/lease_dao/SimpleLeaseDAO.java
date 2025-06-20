package com.pluralsight.CarDealershipAPI.Dao.lease_dao;

import com.pluralsight.CarDealershipAPI.Models.LeaseContract;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SimpleLeaseDAO implements LeaseDao {

    private final List<LeaseContract> leases = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public SimpleLeaseDAO() {
        // Initialize with sample data
        LocalDate today = LocalDate.now();
        leases.add(new LeaseContract(
                "1HGCM82633A123456",
                1001,
                today,
                today.plusYears(3),
                299.99,
                10799.64,
                864.00,
                100.00,
                395.00
        ));
        leases.add(new LeaseContract(
                "JH4KB2F56CC123456",
                1002,
                today.minusMonths(6),
                today.plusYears(2).minusMonths(6),
                349.99,
                12599.64,
                1008.00,
                100.00,
                495.00
        ));

        // Set IDs for sample leases
        leases.forEach(lease -> lease.setLeaseId(nextId.getAndIncrement()));
    }

    @Override
    public List<LeaseContract> getAllLeases() {
        return new ArrayList<>(leases); // Return a copy to prevent external modification
    }

    @Override
    public LeaseContract getLeaseById(int leaseId) {
        return leases.stream()
                .filter(lease -> lease.getLeaseId() == leaseId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public LeaseContract addLease(LeaseContract leaseContract) {
        leaseContract.setLeaseId(nextId.getAndIncrement());
        leases.add(leaseContract);
        return leaseContract;
    }

    @Override
    public boolean updateLease(LeaseContract leaseContract) {
        for (int i = 0; i < leases.size(); i++) {
            if (leases.get(i).getLeaseId() == leaseContract.getLeaseId()) {
                leases.set(i, leaseContract);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteLease(int leaseId) {
        return leases.removeIf(lease -> lease.getLeaseId() == leaseId);
    }
}
