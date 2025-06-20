package com.pluralsight.CarDealershipAPI.Dao.Dealership;

import com.pluralsight.CarDealershipAPI.Models.Dealership;
import java.util.List;

public interface DealershipDao {
    List<Dealership> getAllDealerships();
    Dealership getDealershipById(int dealershipId);
    Dealership getDealershipByName(String name);
    Dealership addDealership(Dealership dealership);
    boolean updateDealership(Dealership dealership);
    boolean deleteDealership(int dealershipId);
}
