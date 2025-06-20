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
    List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear);
    List<Vehicle> getVehiclesByColor(String color);
    List<Vehicle> getVehiclesByType(String type);
    Vehicle addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    boolean deleteVehicle(int vehicleId);
}
