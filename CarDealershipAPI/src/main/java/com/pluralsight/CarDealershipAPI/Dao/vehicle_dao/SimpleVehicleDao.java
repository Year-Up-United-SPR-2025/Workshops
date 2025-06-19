package com.pluralsight.CarDealershipAPI.Dao.vehicle_dao;

import com.pluralsight.CarDealershipAPI.Models.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SimpleVehicleDao implements VehicleDao {

    private List<Vehicle> vehicles = new ArrayList<>();
    private int nextId = 1;

    public SimpleVehicleDao() {
        // Initialize with some sample vehicles
        vehicles.add(new Vehicle("1HGCM82633A123456", 2022, "Toyota", "Camry",
                "Sedan", "Blue", 15000, 24999.99, false));
        vehicles.add(new Vehicle("JH4KB2F56CC123456", 2021, "Honda", "Accord",
                "Sedan", "Red", 22000, 22999.99, false));
        vehicles.add(new Vehicle("3N1CB51D92L123456", 2023, "Nissan", "Rogue",
                "SUV", "Black", 5000, 29999.99, false));

        // Set IDs for sample vehicles
        for (Vehicle vehicle : vehicles) {
            vehicle.setVehicleId(nextId++);
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        return vehicles.stream()
                .filter(v -> v.getVehicleId() == vehicleId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        return vehicles.stream()
                .filter(v -> v.getVin().equalsIgnoreCase(vin))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) {
        return vehicles.stream()
                .filter(v -> v.getPrice() >= minPrice && v.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getVehiclesByMake(String make) {
        return vehicles.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getVehiclesByModel(String model) {
        return vehicles.stream()
                .filter(v -> v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setVehicleId(nextId++);
        vehicles.add(vehicle);
        return vehicle;
    }

    @Override
    public boolean updateVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle existingVehicle = vehicles.get(i);
            if (existingVehicle.getVehicleId() == updatedVehicle.getVehicleId()) {
                vehicles.set(i, updatedVehicle);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        return vehicles.removeIf(v -> v.getVehicleId() == vehicleId);
    }
}
