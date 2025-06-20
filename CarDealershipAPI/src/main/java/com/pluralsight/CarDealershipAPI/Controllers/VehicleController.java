package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.Dao.vehicle_dao.VehicleDao;
import com.pluralsight.CarDealershipAPI.Models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleDao vehicleDao;

    @Autowired
    public VehicleController(@Qualifier("jdbcVehicleDAO") VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String type) {

        // If no filters provided, return all vehicles
        if (minPrice == null && maxPrice == null && make == null && model == null &&
                minYear == null && maxYear == null && color == null && type == null) {
            return ResponseEntity.ok(vehicleDao.getAllVehicles());
        }

        // Apply filters
        List<Vehicle> filteredVehicles = vehicleDao.getAllVehicles();

        if (minPrice != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getPrice() >= minPrice)
                    .toList();
        }

        if (maxPrice != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getPrice() <= maxPrice)
                    .toList();
        }

        if (make != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getMake().equalsIgnoreCase(make))
                    .toList();
        }

        if (model != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getModel().equalsIgnoreCase(model))
                    .toList();
        }

        if (minYear != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getYear() >= minYear)
                    .toList();
        }

        if (maxYear != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getYear() <= maxYear)
                    .toList();
        }

        if (color != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getColor().equalsIgnoreCase(color))
                    .toList();
        }

        if (type != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getType().equalsIgnoreCase(type))
                    .toList();
        }

        return ResponseEntity.ok(filteredVehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable int id) {
        Vehicle vehicle = vehicleDao.getVehicleById(id);
        return vehicle != null ?
                ResponseEntity.ok(vehicle) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/vin/{vin}")
    public ResponseEntity<Vehicle> getVehicleByVin(@PathVariable String vin) {
        Vehicle vehicle = vehicleDao.getVehicleByVin(vin);
        return vehicle != null ?
                ResponseEntity.ok(vehicle) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle addedVehicle = vehicleDao.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable int id,
            @RequestBody Vehicle vehicle) {

        if (vehicle.getVehicleId() != id) {
            return ResponseEntity.badRequest().build();
        }

        boolean updated = vehicleDao.updateVehicle(vehicle);
        return updated ?
                ResponseEntity.ok(vehicle) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int id) {
        boolean deleted = vehicleDao.deleteVehicle(id);
        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String type) {

        // If no filters provided, return all vehicles
        if (minPrice == null && maxPrice == null && make == null && model == null &&
                minYear == null && maxYear == null && color == null && type == null) {
            return ResponseEntity.ok(vehicleDao.getAllVehicles());
        }

        // Apply filters
        List<Vehicle> filteredVehicles = vehicleDao.getAllVehicles();

        if (minPrice != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getPrice() >= minPrice)
                    .toList();
        }

        if (maxPrice != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getPrice() <= maxPrice)
                    .toList();
        }

        if (make != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getMake().equalsIgnoreCase(make))
                    .toList();
        }

        if (model != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getModel().equalsIgnoreCase(model))
                    .toList();
        }

        if (minYear != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getYear() >= minYear)
                    .toList();
        }

        if (maxYear != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getYear() <= maxYear)
                    .toList();
        }

        if (color != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getColor().equalsIgnoreCase(color))
                    .toList();
        }

        if (type != null) {
            filteredVehicles = filteredVehicles.stream()
                    .filter(v -> v.getType().equalsIgnoreCase(type))
                    .toList();
        }

        return ResponseEntity.ok(filteredVehicles);
    }
}
