package com.pluralsight.CarDealershipAPI.Dao.sales_dao;

import com.pluralsight.CarDealershipAPI.Config.DatabaseConfig;
import com.pluralsight.CarDealershipAPI.Models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class SalesJdbcDao implements SalesDao {

    private final DatabaseConfig databaseConfig;
    private final BasicDataSource basicDataSource;

    @Autowired
    public SalesJdbcDao(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        this.basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseConfig.getUrl());
        basicDataSource.setUsername(databaseConfig.getUsername());
        basicDataSource.setPassword(databaseConfig.getPassword());
    }

    @Override
    public List<SalesContract> getAllSalesContracts() {
        List<SalesContract> salesContracts = new ArrayList<>();
        String query = """
                SELECT
                    contract_id,
                    sale_date,
                    customer_name,
                    customer_email,
                    vehicle_vin,
                    total_price,
                    monthly_payment,
                    sales_tax,
                    recording_fee,
                    processing_fee,
                    finance_option,
                    sale_price
                FROM
                    sales_contracts
                """;

        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                SalesContract contract = new SalesContract();
                contract.setContractId(resultSet.getInt("contract_id"));
                contract.setSaleDate(resultSet.getString("sale_date"));
                contract.setCustomerName(resultSet.getString("customer_name"));
                contract.setCustomerEmail(resultSet.getString("customer_email"));
                contract.setVehicleVin(resultSet.getString("vehicle_vin"));
                contract.setTotalPrice(resultSet.getDouble("total_price"));
                contract.setMonthlyPayment(resultSet.getDouble("monthly_payment"));
                contract.setSalesTaxAmount(resultSet.getDouble("sales_tax"));
                contract.setRecordingFee(resultSet.getDouble("recording_fee"));
                contract.setProcessingFee(resultSet.getDouble("processing_fee"));
                contract.setFinanceOption(resultSet.getInt("finance_option"));
                contract.setSalePrice(resultSet.getDouble("sale_price"));
                salesContracts.add(contract);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salesContracts;
    }

    @Override
    public SalesContract getSalesContractById(int contractId) {
        String query = """
                SELECT
                    contract_id,
                    sale_date,
                    customer_name,
                    customer_email,
                    vehicle_vin,
                    total_price,
                    monthly_payment,
                    sales_tax,
                    recording_fee,
                    processing_fee,
                    finance_option,
                    sale_price
                FROM
                    sales_contracts
                WHERE
                    contract_id = ?
                """;

        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, contractId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SalesContract contract = new SalesContract();
                    contract.setContractId(resultSet.getInt("contract_id"));
                    contract.setSaleDate(resultSet.getString("sale_date"));
                    contract.setCustomerName(resultSet.getString("customer_name"));
                    contract.setCustomerEmail(resultSet.getString("customer_email"));
                    contract.setVehicleVin(resultSet.getString("vehicle_vin"));
                    contract.setTotalPrice(resultSet.getDouble("total_price"));
                    contract.setMonthlyPayment(resultSet.getDouble("monthly_payment"));
                    contract.setSalesTaxAmount(resultSet.getDouble("sales_tax"));
                    contract.setRecordingFee(resultSet.getDouble("recording_fee"));
                    contract.setProcessingFee(resultSet.getDouble("processing_fee"));
                    contract.setFinanceOption(resultSet.getInt("finance_option"));
                    contract.setSalePrice(resultSet.getDouble("sale_price"));
                    return contract;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public SalesContract addSalesContract(SalesContract salesContract) {
        String query = """
                INSERT INTO sales_contracts
                (sale_date, customer_name, customer_email, vehicle_vin, 
                 total_price, monthly_payment, sales_tax, recording_fee, 
                 processing_fee, finance_option, sale_price)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, salesContract.getSaleDate());
            preparedStatement.setString(2, salesContract.getCustomerName());
            preparedStatement.setString(3, salesContract.getCustomerEmail());
            preparedStatement.setString(4, salesContract.getVehicleVin());
            preparedStatement.setDouble(5, salesContract.getTotalPrice());
            preparedStatement.setDouble(6, salesContract.getMonthlyPayment());
            preparedStatement.setDouble(7, salesContract.getSalesTaxAmount());
            preparedStatement.setDouble(8, salesContract.getRecordingFee());
            preparedStatement.setDouble(9, salesContract.getProcessingFee());
            preparedStatement.setInt(10, salesContract.getFinanceOption());
            preparedStatement.setDouble(11, salesContract.getSalePrice());

            int rows = preparedStatement.executeUpdate();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    salesContract.setContractId(keys.getInt(1));
                    return salesContract;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
