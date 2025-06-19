package com.pluralsight.CarDealershipAPI.Dao;

import com.pluralsight.Models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private final String connectionString;
    private final String username;
    private final String password;

    public VehicleDao(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by price range: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE make = ? AND model = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, make);
            statement.setString(2, model);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by make/model: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByYearRange(int startYear, int endYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE year BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, startYear);
            statement.setInt(2, endYear);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by year range: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE color = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, color);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by color: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE odometer BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, minMileage);
            statement.setInt(2, maxMileage);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by mileage range: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> findByType(String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE vehicle_type = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vehicleType);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error searching vehicles by type: " + e.getMessage());
        }

        return vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO cardealership.vehicles (vin, year, make, model, vehicle_type, color, odometer, price, sold, dealership_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setString(5, vehicle.getColor());
            statement.setDouble(6, vehicle.getPrice());
            statement.setBoolean(7, vehicle.isSold());
            statement.setInt(8, vehicle.getDealershipId());
            statement.setString(9, vehicle.getVehicleType());
            statement.setInt(10, vehicle.getOdometer());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    vehicle.setId(generatedKeys.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
        }

        return false;
    }

    public boolean removeVehicle(String vin) {
        String sql = "DELETE FROM cardealership.vehicles WHERE vin = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vin);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error removing vehicle: " + e.getMessage());
        }

        return false;
    }

    public Vehicle findByVin(String vin) {
        String sql = "SELECT * FROM cardealership.vehicles WHERE vin = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createVehicleFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            System.err.println("Error finding vehicle by VIN: " + e.getMessage());
        }

        return null;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all vehicles: " + e.getMessage());
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByDealership(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM cardealership.vehicles WHERE dealership_id = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting vehicles by dealership: " + e.getMessage());
        }

        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("vin"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setPrice(resultSet.getDouble("price"));
        vehicle.setSold(resultSet.getBoolean("sold"));
        vehicle.setDealershipId(resultSet.getInt("dealershipId"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        return vehicle;
    }
}
