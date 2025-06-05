-- STEP 1: Insert Dealerships
INSERT INTO dealerships (name, address, phone) VALUES
('AutoWorld', '123 Main St, NY', '555-123-4567'),
('Elite Motors', '456 Oak Ave, CA', '555-987-6543'),
('Premium Auto Group', '789 Sunset Blvd, CA', '555-246-8135'),
('Metro Car Center', '321 Broadway, NY', '555-369-2580'),
('Coastal Motors', '654 Beach Ave, FL', '555-741-9630'),
('Mountain View Auto', '987 Highland Dr, CO', '555-852-1470');

-- STEP 2: Insert Vehicles
INSERT INTO vehicles (VIN, make, model, year, color, price, sold) VALUES
('1HGCM82633A123456', 'Honda', 'Civic', 2023, 'Red', 22000.00, FALSE),
('2FMDK36C59BA12345', 'Ford', 'Explorer', 2022, 'Black', 35000.00, TRUE),
('3GNDA13D76S123789', 'Chevrolet', 'Malibu', 2024, 'White', 28500.00, FALSE),
('5NPE34AF4FH456123', 'Hyundai', 'Sonata', 2023, 'Silver', 26000.00, TRUE),
('WBAVA37553NM78901', 'BMW', '3 Series', 2024, 'Blue', 42000.00, FALSE),
('JM1BK32F781234567', 'Mazda', 'CX-5', 2023, 'Gray', 31000.00, TRUE),
('1N4AL3AP5FC567890', 'Nissan', 'Altima', 2024, 'Black', 27500.00, FALSE),
('KNDJN2A26F7890123', 'Kia', 'Sorento', 2022, 'Red', 33000.00, TRUE),
('1FTEW1EP8FK234567', 'Ford', 'F-150', 2024, 'White', 38000.00, FALSE),
('JTDKARFP9J3345678', 'Toyota', 'Camry', 2023, 'Green', 29000.00, FALSE),
('WDDGF4HB1CA987654', 'Mercedes-Benz', 'C-Class', 2024, 'Silver', 45000.00, FALSE),
('1G1ZD5ST8HF123789', 'Chevrolet', 'Camaro', 2023, 'Yellow', 32000.00, FALSE);

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
INSERT INTO sales_contracts (VIN, customer_name, sale_date, sale_price) VALUES
('2FMDK36C59BA12345', 'John Doe', '2025-06-01', 34000.00),
('5NPE34AF4FH456123', 'Sarah Johnson', '2025-05-28', 25500.00),
('JM1BK32F781234567', 'Mike Rodriguez', '2025-06-02', 30500.00),
('KNDJN2A26F7890123', 'Emily Chen', '2025-05-25', 32000.00),
('WBAVA37553NM78901', 'Lisa Thompson', '2025-06-04', 41000.00),
('1G1ZD5ST8HF123789', 'James Wilson', '2025-06-03', 31500.00);

-- STEP 5: Insert Lease Contracts
INSERT INTO lease_contracts (VIN, customer_name, lease_start, lease_end, monthly_payment) VALUES
('1HGCM82633A123456', 'Carlos Rivera', '2025-06-10', '2027-06-09', 275.00),
('3GNDA13D76S123789', 'Alex Martinez', '2025-06-01', '2028-05-31', 385.00),
('1N4AL3AP5FC567890', 'Jennifer Park', '2025-05-15', '2028-05-14', 295.00),
('1FTEW1EP8FK234567', 'Robert Kim', '2025-06-05', '2028-06-04', 425.00),
('JTDKARFP9J3345678', 'Amanda Foster', '2025-05-20', '2027-05-19', 315.00),
('WDDGF4HB1CA987654', 'Michelle Wong', '2025-05-28', '2028-05-27', 465.00);