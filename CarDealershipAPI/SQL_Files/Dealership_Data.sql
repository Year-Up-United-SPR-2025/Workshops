-- =============================================
-- Car Dealership WS9 Sample Data
-- File: dealership_sample_data.sql
-- =============================================

USE cardealership_ws9;

-- Insert sample vehicles
INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price, sold) VALUES
-- Sedans
(10112, 1993, 'Ford', 'Explorer', 'SUV', 'Red', 525123, 995.00, false),
(37846, 2001, 'Ford', 'Ranger', 'Truck', 'Yellow', 172544, 1995.00, false),
(44901, 2012, 'Honda', 'Civic', 'Sedan', 'Gray', 103221, 6995.00, false),
(10101, 2018, 'Toyota', 'Camry', 'Sedan', 'Silver', 45000, 18500.00, false),
(10102, 2020, 'Honda', 'Accord', 'Sedan', 'White', 32000, 22000.00, false),
(10103, 2017, 'Nissan', 'Altima', 'Sedan', 'Black', 58000, 16500.00, false),

-- SUVs
(20201, 2019, 'Toyota', 'RAV4', 'SUV', 'Blue', 41000, 24500.00, false),
(20202, 2021, 'Honda', 'CR-V', 'SUV', 'Red', 28000, 26500.00, false),
(20203, 2018, 'Ford', 'Escape', 'SUV', 'Green', 52000, 19500.00, false),
(20204, 2020, 'Chevrolet', 'Equinox', 'SUV', 'Gray', 35000, 21500.00, false),

-- Trucks
(30301, 2019, 'Ford', 'F-150', 'Truck', 'Black', 65000, 32000.00, false),
(30302, 2020, 'Chevrolet', 'Silverado', 'Truck', 'White', 48000, 34500.00, false),
(30303, 2018, 'RAM', '1500', 'Truck', 'Red', 72000, 29500.00, false),

-- Coupes
(40401, 2021, 'BMW', '330i', 'Coupe', 'Blue', 25000, 38500.00, false),
(40402, 2020, 'Audi', 'A4', 'Coupe', 'Silver', 31000, 35500.00, false),
(40403, 2019, 'Mercedes', 'C-Class', 'Coupe', 'Black', 42000, 36500.00, false),

-- Hatchbacks
(50501, 2020, 'Volkswagen', 'Golf', 'Hatchback', 'Yellow', 38000, 19500.00, false),
(50502, 2021, 'Honda', 'Fit', 'Hatchback', 'Orange', 22000, 17500.00, false),

-- Sports Cars
(60601, 2022, 'Ford', 'Mustang', 'Sports', 'Red', 15000, 42000.00, false),
(60602, 2021, 'Chevrolet', 'Camaro', 'Sports', 'Yellow', 18000, 39500.00, false),

-- Electric Vehicles
(70701, 2022, 'Tesla', 'Model 3', 'Electric', 'White', 12000, 45000.00, false),
(70702, 2021, 'Nissan', 'Leaf', 'Electric', 'Blue', 28000, 24500.00, false);

-- Insert sample sales contracts
INSERT INTO sales_contracts (vin, customer_name, customer_email, contract_date, total_price, monthly_payment) VALUES
(44901, 'John Smith', 'john.smith@email.com', '2024-01-15', 6995.00, 199.00),
(10101, 'Sarah Johnson', 'sarah.johnson@email.com', '2024-02-20', 18500.00, 315.00),
(20201, 'Mike Davis', 'mike.davis@email.com', '2024-03-10', 24500.00, 425.00),
(30301, 'Lisa Wilson', 'lisa.wilson@email.com', '2024-03-25', 32000.00, 585.00),
(40401, 'David Brown', 'david.brown@email.com', '2024-04-05', 38500.00, 675.00);

-- Insert sample lease contracts
INSERT INTO lease_contracts (vin, customer_name, customer_email, contract_date, lease_term, monthly_payment, total_amount) VALUES
(10102, 'Emily Garcia', 'emily.garcia@email.com', '2024-01-20', 36, 385.00, 13860.00),
(20202, 'Robert Martinez', 'robert.martinez@email.com', '2024-02-15', 24, 465.00, 11160.00),
(30302, 'Jennifer Anderson', 'jennifer.anderson@email.com', '2024-03-01', 36, 595.00, 21420.00),
(40402, 'Christopher Taylor', 'christopher.taylor@email.com', '2024-03-20', 24, 625.00, 15000.00),
(70701, 'Amanda Thomas', 'amanda.thomas@email.com', '2024-04-10', 36, 785.00, 28260.00);
