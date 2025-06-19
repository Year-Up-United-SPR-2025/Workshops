package com.pluralsight.CarDealershipAPI.Dao.vehicle_dao;

import com.pluralsight.CarDealershipAPI.Models.Vehicle;
import java.util.List;

public interface VehicleDao {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int vehicleId);
    Vehicle getVehicleByVin(String vin);
    List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> getVehiclesByMake(String make);
    List<Vehicle> getVehiclesByModel(String model);
    Vehicle addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean deleteVehicle(int vehicleId);

    List<Vehicle> findVehicles(Integer minPrice, Integer maxPrice, String make, String model, Integer minYear, Integer maxYear, String color, Integer minMiles, Integer maxMiles, String type);
}
