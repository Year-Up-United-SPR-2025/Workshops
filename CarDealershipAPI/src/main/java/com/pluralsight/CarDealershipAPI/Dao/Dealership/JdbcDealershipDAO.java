package com.pluralsight.CarDealershipAPI.Dao.Dealership;

import com.pluralsight.CarDealershipAPI.Config.DatabaseConfig;
import com.pluralsight.CarDealershipAPI.Models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class JdbcDealershipDAO implements DealershipDao {

    private final BasicDataSource dataSource;

    @Autowired
    public JdbcDealershipDAO(DatabaseConfig databaseConfig) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
    }

    @Override
    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                dealerships.add(mapRowToDealership(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all dealerships", e);
        }
        return dealerships;
    }

    @Override
    public Dealership getDealershipById(int dealershipId) {
        String query = "SELECT * FROM dealerships WHERE dealership_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, dealershipId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToDealership(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching dealership by ID: " + dealershipId, e);
        }
        return null;
    }

    @Override
    public Dealership getDealershipByName(String name) {
        String query = "SELECT * FROM dealerships WHERE name = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToDealership(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching dealership by name: " + name, e);
        }
        return null;
    }

    @Override
    public Dealership addDealership(Dealership dealership) {
        String query = """
                INSERT INTO dealerships 
                (name, address, phone, email) 
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dealership.getName());
            ps.setString(2, dealership.getAddress());
            ps.setString(3, dealership.getPhone());
            ps.setString(4, dealership.getEmail());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating dealership failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dealership.setDealershipId(generatedKeys.getInt(1));
                    return dealership;
                } else {
                    throw new SQLException("Creating dealership failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding dealership", e);
        }
    }

    @Override
    public boolean updateDealership(Dealership dealership) {
        String query = """
                UPDATE dealerships SET
                    name = ?,
                    address = ?,
                    phone = ?,
                    email = ?
                WHERE dealership_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, dealership.getName());
            ps.setString(2, dealership.getAddress());
            ps.setString(3, dealership.getPhone());
            ps.setString(4, dealership.getEmail());
            ps.setInt(5, dealership.getDealershipId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating dealership ID: " + dealership.getDealershipId(), e);
        }
    }

    @Override
    public boolean deleteDealership(int dealershipId) {
        String query = "DELETE FROM dealerships WHERE dealership_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, dealershipId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dealership ID: " + dealershipId, e);
        }
    }

    private Dealership mapRowToDealership(ResultSet rs) throws SQLException {
        Dealership dealership = new Dealership();
        dealership.setDealershipId(rs.getInt("dealership_id"));
        dealership.setName(rs.getString("name"));
        dealership.setAddress(rs.getString("address"));
        dealership.setPhone(rs.getString("phone"));
        dealership.setEmail(rs.getString("email"));
        return dealership;
    }
}
