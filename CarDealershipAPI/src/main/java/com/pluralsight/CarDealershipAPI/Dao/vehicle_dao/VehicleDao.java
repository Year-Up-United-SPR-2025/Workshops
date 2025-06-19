package com.pluralsight.CarDealershipAPI.Dao.vehicle_dao;


import com.pluralsight.CarDealershipAPI.Models.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(int vehicleId);

    Vehicle addVehicle(Vehicle vehicle);
}
