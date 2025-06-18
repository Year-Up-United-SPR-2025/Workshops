-- =============================================
-- Car Dealership Database Schema
-- File: dealership_schema.sql
-- =============================================

-- Create database
CREATE DATABASE IF NOT EXISTS CarDealership_WS9;
USE cardealership_ws9;

DROP TABLE IF EXISTS lease_contracts;
DROP TABLE IF EXISTS sales_contracts;
DROP TABLE IF EXISTS vehicles;

-- Create vehicles table
CREATE TABLE vehicles (
    vin INT PRIMARY KEY,
    year INT NOT NULL,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    vehicle_type VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    odometer INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    sold BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create sales contracts table
CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin INT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    contract_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    monthly_payment DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vin) REFERENCES vehicles(vin) ON DELETE CASCADE
);

-- Create lease contracts table
CREATE TABLE lease_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin INT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    contract_date DATE NOT NULL,
    lease_term INT NOT NULL COMMENT 'Lease term in months',
    monthly_payment DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vin) REFERENCES vehicles(vin) ON DELETE CASCADE
);