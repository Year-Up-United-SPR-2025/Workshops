-- STEP 1: Insert Dealerships
INSERT INTO dealerships (name, address, phone) VALUES
('AutoWorld', '123 Main St, NY', '555-123-4567'),
('Elite Motors', '456 Oak Ave, CA', '555-987-6543'),
('Premium Auto Group', '789 Sunset Blvd, CA', '555-246-8135'),
('Metro Car Center', '321 Broadway, NY', '555-369-2580'),
('Coastal Motors', '654 Beach Ave, FL', '555-741-9630'),
('Mountain View Auto', '987 Highland Dr, CO', '555-852-1470');

-- STEP 2: Insert Vehicles
INSERT INTO vehicles (VIN, make, model, year, color, price, sold, dealershipId) VALUES
('1HGCM82633A123456', 'Honda', 'Civic', 2023, 'Red', 22000.00, FALSE, 1),
('2FMDK36C59BA12345', 'Ford', 'Explorer', 2022, 'Black', 35000.00, TRUE, 2),
('3GNDA13D76S123789', 'Chevrolet', 'Malibu', 2024, 'White', 28500.00, FALSE, 1),
('5NPE34AF4FH456123', 'Hyundai', 'Sonata', 2023, 'Silver', 26000.00, TRUE, 2),
('WBAVA37553NM78901', 'BMW', '3 Series', 2024, 'Blue', 42000.00, FALSE, 3),
('JM1BK32F781234567', 'Mazda', 'CX-5', 2023, 'Gray', 31000.00, TRUE, 1),
('1N4AL3AP5FC567890', 'Nissan', 'Altima', 2024, 'Black', 27500.00, FALSE, 4),
('KNDJN2A26F7890123', 'Kia', 'Sorento', 2022, 'Red', 33000.00, TRUE, 2),
('1FTEW1EP8FK234567', 'Ford', 'F-150', 2024, 'White', 38000.00, FALSE, 3),
('JTDKARFP9J3345678', 'Toyota', 'Camry', 2023, 'Green', 29000.00, FALSE, 4),
('WDDGF4HB1CA987654', 'Mercedes-Benz', 'C-Class', 2024, 'Silver', 45000.00, FALSE, 5),
('1G1ZD5ST8HF123789', 'Chevrolet', 'Camaro', 2023, 'Yellow', 32000.00, FALSE, 6);

-- STEP 3: Insert Inventory Assignments
INSERT INTO inventory (dealership_id, VIN) VALUES
(1, '1HGCM82633A123456'),
(2, '2FMDK36C59BA12345'),
(1, '3GNDA13D76S123789'),
(2, '5NPE34AF4FH456123'),
(3, 'WBAVA37553NM78901'),
(1, 'JM1BK32F781234567'),
(4, '1N4AL3AP5FC567890'),
(2, 'KNDJN2A26F7890123'),
(3, '1FTEW1EP8FK234567'),
(4, 'JTDKARFP9J3345678'),
(5, 'WDDGF4HB1CA987654'),
(6, '1G1ZD5ST8HF123789');

-- STEP 4: Insert Sales Contracts
INSERT INTO sales_contracts (VIN, customer_name, customer_email, sale_date, sale_price, sales_tax, recording_fee, processing_fee, finance_option, monthly_payment, total_price) VALUES
('2FMDK36C59BA12345', 'John Doe', 'john.doe@example.com', '2025-06-01', 34000.00, 2720.00, 150.00, 250.00, TRUE, 643.32, 37120.00),
('5NPE34AF4FH456123', 'Sarah Johnson', 'sarah.johnson@example.com', '2025-05-28', 25500.00, 2040.00, 150.00, 250.00, FALSE, NULL, 27940.00),
('JM1BK32F781234567', 'Mike Rodriguez', 'mike.rodriguez@example.com', '2025-06-02', 30500.00, 2440.00, 150.00, 250.00, TRUE, 577.07, 33240.00),
('KNDJN2A26F7890123', 'Emily Chen', 'emily.chen@example.com', '2025-05-25', 32000.00, 2560.00, 150.00, 250.00, FALSE, NULL, 34960.00),
('WBAVA37553NM78901', 'Lisa Thompson', 'lisa.thompson@example.com', '2025-06-04', 41000.00, 3280.00, 150.00, 250.00, TRUE, 776.27, 44680.00),
('1G1ZD5ST8HF123789', 'James Wilson', 'james.wilson@example.com', '2025-06-03', 31500.00, 2520.00, 150.00, 250.00, FALSE, NULL, 34420.00);

-- STEP 5: Insert Lease Contracts
INSERT INTO lease_contracts (VIN, customer_name, customer_email, lease_start, lease_end, expected_ending_value, lease_fee, total_price, monthly_payment) VALUES
('1HGCM82633A123456', 'Carlos Rivera', 'carlos.rivera@example.com', '2025-06-10', '2027-06-09', 12100.00, 500.00, 6650.00, 275.00),
('3GNDA13D76S123789', 'Alex Martinez', 'alex.martinez@example.com', '2025-06-01', '2028-05-31', 15675.00, 500.00, 13955.00, 385.00),
('1N4AL3AP5FC567890', 'Jennifer Park', 'jennifer.park@example.com', '2025-05-15', '2028-05-14', 15125.00, 500.00, 10615.00, 295.00),
('1FTEW1EP8FK234567', 'Robert Kim', 'robert.kim@example.com', '2025-06-05', '2028-06-04', 20900.00, 500.00, 15380.00, 425.00),
('JTDKARFP9J3345678', 'Amanda Foster', 'amanda.foster@example.com', '2025-05-20', '2027-05-19', 15950.00, 500.00, 8010.00, 315.00),
('WDDGF4HB1CA987654', 'Michelle Wong', 'michelle.wong@example.com', '2025-05-28', '2028-05-27', 24750.00, 500.00, 17140.00, 465.00);
