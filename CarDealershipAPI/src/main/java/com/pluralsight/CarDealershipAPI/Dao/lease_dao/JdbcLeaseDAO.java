package com.pluralsight.CarDealershipAPI.Dao.lease_dao;


import com.pluralsight.CarDealershipAPI.Config.DatabaseConfig;
import com.pluralsight.CarDealershipAPI.Models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class JdbcLeaseDAO implements LeaseDao {

    private final BasicDataSource dataSource;

    @Autowired
    public JdbcLeaseDAO(DatabaseConfig databaseConfig) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
    }

    @Override
    public List<LeaseContract> getAllLeases() {
        List<LeaseContract> leases = new ArrayList<>();
        String query = """
                SELECT lease_id, vehicle_vin, customer_id, start_date, end_date, 
                       monthly_payment, total_price, sales_tax, recording_fee, 
                       processing_fee 
                FROM lease_contracts
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                leases.add(mapRowToLeaseContract(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all leases", e);
        }
        return leases;
    }

    @Override
    public LeaseContract getLeaseById(int leaseId) {
        String query = """
                SELECT lease_id, vehicle_vin, customer_id, start_date, end_date, 
                       monthly_payment, total_price, sales_tax, recording_fee, 
                       processing_fee 
                FROM lease_contracts 
                WHERE lease_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, leaseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToLeaseContract(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching lease by ID: " + leaseId, e);
        }
        return null;
    }

    @Override
    public LeaseContract addLease(LeaseContract leaseContract) {
        String query = """
                INSERT INTO lease_contracts 
                (vehicle_vin, customer_id, start_date, end_date, monthly_payment, 
                 total_price, sales_tax, recording_fee, processing_fee) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, leaseContract.getVehicleVin());
            ps.setInt(2, leaseContract.getCustomerId());
            ps.setDate(3, Date.valueOf(leaseContract.getStartDate()));
            ps.setDate(4, Date.valueOf(leaseContract.getEndDate()));
            ps.setDouble(5, leaseContract.getMonthlyPayment());
            ps.setDouble(6, leaseContract.getTotalPrice());
            ps.setDouble(7, leaseContract.getSalesTax());
            ps.setDouble(8, leaseContract.getRecordingFee());
            ps.setDouble(9, leaseContract.getProcessingFee());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating lease failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    leaseContract.setLeaseId(generatedKeys.getInt(1));
                    return leaseContract;
                } else {
                    throw new SQLException("Creating lease failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding lease", e);
        }
    }

    @Override
    public boolean updateLease(LeaseContract leaseContract) {
        String query = """
                UPDATE lease_contracts SET
                    vehicle_vin = ?,
                    customer_id = ?,
                    start_date = ?,
                    end_date = ?,
                    monthly_payment = ?,
                    total_price = ?,
                    sales_tax = ?,
                    recording_fee = ?,
                    processing_fee = ?
                WHERE lease_id = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, leaseContract.getVehicleVin());
            ps.setInt(2, leaseContract.getCustomerId());
            ps.setDate(3, Date.valueOf(leaseContract.getStartDate()));
            ps.setDate(4, Date.valueOf(leaseContract.getEndDate()));
            ps.setDouble(5, leaseContract.getMonthlyPayment());
            ps.setDouble(6, leaseContract.getTotalPrice());
            ps.setDouble(7, leaseContract.getSalesTax());
            ps.setDouble(8, leaseContract.getRecordingFee());
            ps.setDouble(9, leaseContract.getProcessingFee());
            ps.setInt(10, leaseContract.getLeaseId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating lease ID: " + leaseContract.getLeaseId(), e);
        }
    }

    @Override
    public boolean deleteLease(int leaseId) {
        String query = "DELETE FROM lease_contracts WHERE lease_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, leaseId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting lease ID: " + leaseId, e);
        }
    }

    private LeaseContract mapRowToLeaseContract(ResultSet rs) throws SQLException {
        LeaseContract contract = new LeaseContract();
        contract.setLeaseId(rs.getInt("lease_id"));
        contract.setVehicleVin(rs.getString("vehicle_vin"));
        contract.setCustomerId(rs.getInt("customer_id"));
        contract.setStartDate(rs.getDate("start_date").toLocalDate());
        contract.setEndDate(rs.getDate("end_date").toLocalDate());
        contract.setMonthlyPayment(rs.getDouble("monthly_payment"));
        contract.setTotalPrice(rs.getDouble("total_price"));
        contract.setSalesTax(rs.getDouble("sales_tax"));
        contract.setRecordingFee(rs.getDouble("recording_fee"));
        contract.setProcessingFee(rs.getDouble("processing_fee"));
        return contract;
    }
}
