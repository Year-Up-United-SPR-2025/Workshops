package com.pluralsight.CarDealershipAPI.Dao.vehicle_dao;


import com.pluralsight.CarDealershipAPI.Config.DatabaseConfig;
import com.pluralsight.CarDealershipAPI.Models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JbdcVechicleDAO implements VehicleDao {

    private final BasicDataSource dataSource;

    @Autowired
    public JbdcVechicleDAO(DatabaseConfig databaseConfig) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = mapRowToVehicle(rs);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all vehicles", e);
        }
        return vehicles;
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                WHERE 
                    vehicle_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToVehicle(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching vehicle by ID: " + vehicleId, e);
        }
        return null;
    }

    @Override
    public Vehicle getVehicleByVin(String vin) {
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                WHERE 
                    vin = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, vin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToVehicle(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching vehicle by VIN: " + vin, e);
        }
        return null;
    }

    @Override
    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                WHERE 
                    price BETWEEN ? AND ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching vehicles by price range", e);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByMake(String make) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                WHERE 
                    make = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, make);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching vehicles by make: " + make, e);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByModel(String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
                SELECT 
                    vehicle_id, 
                    vin, 
                    year, 
                    make, 
                    model, 
                    type, 
                    color, 
                    odometer, 
                    price, 
                    sold
                FROM 
                    vehicles
                WHERE 
                    model = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, model);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching vehicles by model: " + model, e);
        }
        return vehicles;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        String query = """
                INSERT INTO vehicles 
                    (vin, year, make, model, type, color, odometer, price, sold) 
                VALUES 
                    (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());
            ps.setBoolean(9, vehicle.isSold());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating vehicle failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vehicle.setVehicleId(generatedKeys.getInt(1));
                    return vehicle;
                } else {
                    throw new SQLException("Creating vehicle failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding vehicle", e);
        }
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        String query = """
                UPDATE vehicles SET
                    vin = ?,
                    year = ?,
                    make = ?,
                    model = ?,
                    type = ?,
                    color = ?,
                    odometer = ?,
                    price = ?,
                    sold = ?
                WHERE 
                    vehicle_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());
            ps.setBoolean(9, vehicle.isSold());
            ps.setInt(10, vehicle.getVehicleId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating vehicle ID: " + vehicle.getVehicleId(), e);
        }
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting vehicle ID: " + vehicleId, e);
        }
    }

    @Override
    public List<Vehicle> findVehicles(Integer minPrice, Integer maxPrice, String make, String model, Integer minYear, Integer maxYear, String color, Integer minMiles, Integer maxMiles, String type) {
        return List.of();
    }

    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicle_id"));
        vehicle.setVin(rs.getString("vin"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setType(rs.getString("type"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setOdometer(rs.getInt("odometer"));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setSold(rs.getBoolean("sold"));
        return vehicle;
    }
}
