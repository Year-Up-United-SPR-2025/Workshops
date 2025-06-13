package com.pluralsight.Dao;

import com.pluralsight.Models.Dealership;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    private final String connectionString;
    private final String username;
    private final String password;

    public DealershipDao(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public Dealership findById(int id) {
        String sql = "SELECT * FROM cardealership.dealerships WHERE dealership_id = ?;";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createDealershipFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            System.err.println("Error finding dealership: " + e.getMessage());
        }

        return null;
    }

    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String sql = "SELECT * FROM dealerships";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dealerships.add(createDealershipFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all dealerships: " + e.getMessage());
        }

        return dealerships;
    }

    private Dealership createDealershipFromResultSet(ResultSet resultSet) throws SQLException {
        Dealership dealership = new Dealership();
        dealership.setId(resultSet.getInt("dealership_id"));
        dealership.setName(resultSet.getString("name"));
        dealership.setAddress(resultSet.getString("address"));
        dealership.setPhone(resultSet.getString("phone"));
        return dealership;
    }
}