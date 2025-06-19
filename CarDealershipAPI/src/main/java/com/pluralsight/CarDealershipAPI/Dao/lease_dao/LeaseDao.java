package com.pluralsight.CarDealershipAPI.Dao.lease_dao;

import com.pluralsight.Models.LeaseContract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao {

    private final String connectionString;
    private final String username;
    private final String password;

    public LeaseDao(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public boolean saveLeaseContract(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (contract_date, customer_name, customer_email, vehicle_vin, " +
                "expected_ending_value, lease_fee, total_price, monthly_payment, lease_start, lease_end) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, contract.getDateOfContract());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getCustomerEmail());
            statement.setString(4, contract.getVehicleSold().getVin());
            statement.setDouble(5, contract.getExpectedEndingValue());
            statement.setDouble(6, contract.getLeaseFee());
            statement.setDouble(7, contract.getTotalPrice());
            statement.setDouble(8, contract.getMonthlyPayment());
            statement.setString(9, contract.getLeaseStart());
            statement.setString(10, contract.getLeaseEnd());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    contract.setId(generatedKeys.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error saving lease contract: " + e.getMessage());
        }

        return false;
    }

    public LeaseContract findLeaseContractById(int leaseId) {
        String sql = "SELECT * FROM lease_contracts WHERE lease_id = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, leaseId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createLeaseContractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            System.err.println("Error finding lease contract: " + e.getMessage());
        }

        return null;
    }

    public List<LeaseContract> getAllLeaseContracts() {
        List<LeaseContract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM lease_contracts";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contracts.add(createLeaseContractFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all lease contracts: " + e.getMessage());
        }

        return contracts;
    }

    private LeaseContract createLeaseContractFromResultSet(ResultSet resultSet) throws SQLException {
        LeaseContract contract = new LeaseContract();
        contract.setId(resultSet.getInt("lease_id"));
        contract.setDateOfContract(resultSet.getString("contract_date"));
        contract.setCustomerName(resultSet.getString("customer_name"));
        contract.setCustomerEmail(resultSet.getString("customer_email"));
        contract.setExpectedEndingValue(resultSet.getDouble("expected_ending_value"));
        contract.setLeaseFee(resultSet.getDouble("lease_fee"));
        contract.setTotalPrice(resultSet.getDouble("total_price"));
        contract.setMonthlyPayment(resultSet.getDouble("monthly_payment"));
        contract.setLeaseStart(resultSet.getString("lease_start"));
        contract.setLeaseEnd(resultSet.getString("lease_end"));
        return contract;
    }
}