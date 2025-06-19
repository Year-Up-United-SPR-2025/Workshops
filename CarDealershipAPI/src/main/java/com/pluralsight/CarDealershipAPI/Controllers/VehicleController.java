package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleDAO vehicleDAO;

    @GetMapping
    public List<Vehicle> getVehicles(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type) {
        // Logic to filter vehicles based on parameters
        return vehicleDAO.findVehicles(minPrice, maxPrice, make, model, minYear, maxYear, color, minMiles, maxMiles, type);
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDAO.addVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public void updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicleDAO.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleDAO.deleteVehicle(id);
    }
}
   