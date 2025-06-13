package com.pluralsight.Dao;

import com.pluralsight.Models.SalesContract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {

    private final String connectionString;
    private final String username;
    private final String password;

    public SalesDao(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    public boolean saveSalesContract(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (sale_date, customer_name, customer_email, vehicle_vin, " +
                "total_price, monthly_payment, sales_tax, recording_fee, processing_fee, finance_option) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, contract.getSaleDate());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getCustomerEmail());
            statement.setString(4, contract.getVehicleSold().getVin());
            statement.setDouble(5, contract.getTotalPrice());
            statement.setDouble(6, contract.getMonthlyPayment());
            statement.setDouble(7, contract.getSalesTaxAmount());
            statement.setDouble(8, contract.getRecordingFee());
            statement.setDouble(9, contract.getProcessingFee());
            statement.setInt(10, contract.getFinanceOption());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    contract.setContractId(generatedKeys.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error saving sales contract: " + e.getMessage());
        }

        return false;
    }

    public SalesContract findSalesContractById(int contractId) {
        String sql = "SELECT * FROM sales_contracts WHERE contract_id = ?";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contractId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createSalesContractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            System.err.println("Error finding sales contract: " + e.getMessage());
        }

        return null;
    }

    public List<SalesContract> getAllSalesContracts() {
        List<SalesContract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM sales_contracts";

        try (Connection connection = DriverManager.getConnection(connectionString, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contracts.add(createSalesContractFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.err.println("Error getting all sales contracts: " + e.getMessage());
        }

        return contracts;
    }

    private SalesContract createSalesContractFromResultSet(ResultSet resultSet) throws SQLException {
        SalesContract contract = new SalesContract();
        contract.setContractId(resultSet.getInt("contract_id"));
        contract.setSaleDate(resultSet.getString("sale_date"));
        contract.setCustomerName(resultSet.getString("customer_name"));
        contract.setCustomerEmail(resultSet.getString("customer_email"));
        contract.setTotalPrice(resultSet.getDouble("total_price"));
        contract.setMonthlyPayment(resultSet.getDouble("monthly_payment"));
        contract.setSalesTaxAmount(resultSet.getDouble("sales_tax"));
        contract.setRecordingFee(resultSet.getDouble("recording_fee"));
        contract.setProcessingFee(resultSet.getDouble("processing_fee"));
        contract.setFinanceOption(resultSet.getInt("finance_option"));

        return contract;
    }
}