package com.pluralsight.CarDealershipAPI.Dao.Dealership;

import com.pluralsight.CarDealershipAPI.Models.Dealership;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SimpleDealershipDAO implements DealershipDao {

    private final List<Dealership> dealerships = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public SimpleDealershipDAO() {
        // Initialize with sample data
        dealerships.add(new Dealership(
                "Platinum Motors",
                "123 Main St, Anytown, USA",
                "555-123-4567",
                "info@platinummotors.com"
        ));
        dealerships.add(new Dealership(
                "Golden Wheels",
                "456 Oak Ave, Somewhere, USA",
                "555-987-6543",
                "contact@goldenwheels.com"
        ));

        // Set IDs for sample dealerships
        dealerships.forEach(dealership -> dealership.setDealershipId(nextId.getAndIncrement()));
    }

    @Override
    public List<Dealership> getAllDealerships() {
        return new ArrayList<>(dealerships);
    }

    @Override
    public Dealership getDealershipById(int dealershipId) {
        return dealerships.stream()
                .filter(d -> d.getDealershipId() == dealershipId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Dealership getDealershipByName(String name) {
        return dealerships.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Dealership addDealership(Dealership dealership) {
        dealership.setDealershipId(nextId.getAndIncrement());
        dealerships.add(dealership);
        return dealership;
    }

    @Override
    public boolean updateDealership(Dealership dealership) {
        for (int i = 0; i < dealerships.size(); i++) {
            if (dealerships.get(i).getDealershipId() == dealership.getDealershipId()) {
                dealerships.set(i, dealership);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteDealership(int dealershipId) {
        return dealerships.removeIf(d -> d.getDealershipId() == dealershipId);
    }
}
