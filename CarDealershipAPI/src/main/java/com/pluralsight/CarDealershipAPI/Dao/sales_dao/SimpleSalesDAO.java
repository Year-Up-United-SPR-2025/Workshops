package com.pluralsight.CarDealershipAPI.Dao.sales_dao;

import com.pluralsight.CarDealershipAPI.Models.SalesContract;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleSalesDAO implements SalesDao {

    private List<SalesContract> salesContracts;

    public SimpleSalesDAO() {
        // Initialize the sales contracts list with some sample data
        salesContracts = new ArrayList<>();
        salesContracts.add(new SalesContract(1, "2023-01-01", "John Doe", "john@example.com", "1HGCM82633A123456",
                30000.00, 500.00, 2500.00, 100.00, 50.00, 1, 29500.00));
        salesContracts.add(new SalesContract(2, "2023-01-02", "Jane Smith", "jane@example.com", "1HGCM82633A654321",
                25000.00, 450.00, 2000.00, 80.00, 40.00, 2, 24500.00));
    }

    @Override
    public List<SalesContract> getAllSalesContracts() {
        return salesContracts;
    }

    @Override
    public SalesContract getSalesContractById(int contractId) {
        return salesContracts.stream()
                .filter(c -> c.getContractId() == contractId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public SalesContract addSalesContract(SalesContract salesContract) {
        int newId = salesContracts.size() + 1;
        salesContract.setContractId(newId);
        salesContracts.add(salesContract);
        return salesContract;
    }
}
