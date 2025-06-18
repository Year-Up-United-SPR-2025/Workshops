DROP DATABASE IF EXISTS CarDealership;
CREATE DATABASE CarDealership;
USE CarDealership;

CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);

CREATE TABLE vehicles (
    VIN VARCHAR(17) PRIMARY KEY,
    make VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    year INT NOT NULL,
    color VARCHAR(20),
    price DECIMAL(10,2),
    sold BOOLEAN DEFAULT FALSE,
    dealershipId INT,
    vehicleType VARCHAR(30),
    odometer INT,
    FOREIGN KEY (dealershipId) REFERENCES dealerships(dealership_id)
);

CREATE TABLE inventory (
    dealership_id INT,
    VIN VARCHAR(17),
    PRIMARY KEY (dealership_id, VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    sale_date DATE NOT NULL,
    sale_price DECIMAL(10,2) NOT NULL,
    sales_tax DECIMAL(10,2),
    recording_fee DECIMAL(10,2),
    processing_fee DECIMAL(10,2),
    finance_option BOOLEAN DEFAULT FALSE,
    monthly_payment DECIMAL(10,2),
    total_price DECIMAL(10,2),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE lease_contracts (
    lease_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    lease_start DATE NOT NULL,
    lease_end DATE NOT NULL,
    expected_ending_value DECIMAL(10,2),
    lease_fee DECIMAL(10,2),
    total_price DECIMAL(10,2),
    monthly_payment DECIMAL(10,2),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);