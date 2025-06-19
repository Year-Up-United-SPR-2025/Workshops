package com.pluralsight.CarDealershipAPI.Dao.sales_dao;

import com.pluralsight.CarDealershipAPI.Models.SalesContract;
import java.util.List;

public interface SalesDao {
    List<SalesContract> getAllSalesContracts();
    SalesContract getSalesContractById(int contractId);
    SalesContract addSalesContract(SalesContract salesContract);
}
