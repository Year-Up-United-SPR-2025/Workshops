package com.pluralsight.CarDealershipAPI.controller;

import com.pluralsight.CarDealershipAPI.dao.VehicleDao;
import com.pluralsight.CarDealershipAPI.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehiclesController {

    private VehicleDao vehicleDao;

    @Autowired
    public VehiclesController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type) {

        try {
            List<Vehicle> vehicles = vehicleDao.findVehicles(minPrice, maxPrice, make, model,
                    minYear, maxYear, color, minMiles, maxMiles, type);
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable int vin) {
        try {
            Vehicle vehicle = vehicleDao.findByVin(vin);
            if (vehicle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(vehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle addedVehicle = vehicleDao.addVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedVehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int vin, @RequestBody Vehicle vehicle) {
        try {
            Vehicle updatedVehicle = vehicleDao.updateVehicle(vin, vehicle);
            if (updatedVehicle == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int vin) {
        try {
            boolean deleted = vehicleDao.deleteVehicle(vin);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}