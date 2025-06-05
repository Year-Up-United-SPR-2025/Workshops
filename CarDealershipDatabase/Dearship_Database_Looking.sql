-- Car Dealership Database Queries

-- 1. Get all dealerships
SELECT dealership_id, name, address, phone
FROM dealerships
ORDER BY name;

-- 2. Find all vehicles for a specific dealership
SELECT v.VIN, v.make, v.model, v.year, v.color, v.price, v.sold
FROM vehicles v
JOIN inventory i ON v.VIN = i.VIN
WHERE i.dealership_id = 1
ORDER BY v.make, v.model;

-- Alternative with dealership name instead of ID:
SELECT v.VIN, v.make, v.model, v.year, v.color, v.price, v.sold, d.name AS dealership_name
FROM vehicles v
JOIN inventory i ON v.VIN = i.VIN
JOIN dealerships d ON i.dealership_id = d.dealership_id
WHERE d.name = 'AutoWorld'
ORDER BY v.make, v.model;

-- 3. Find a car by VIN
-- Example: Find Honda Civic
SELECT VIN, make, model, year, color, price, sold
FROM vehicles
WHERE VIN = '1HGCM82633A123456';

-- 4. Find the dealership where a certain car is located, by VIN
-- Example: Find where Honda Civic is located
SELECT d.dealership_id, d.name, d.address, d.phone, v.make, v.model, v.year
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.VIN = v.VIN
WHERE v.VIN = '1HGCM82633A123456';

-- 5. Find all Dealerships that have a certain car type
-- Example A: Find dealerships with Honda vehicles (by make)
SELECT DISTINCT d.dealership_id, d.name, d.address, d.phone
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.VIN = v.VIN
WHERE v.make = 'Honda'
ORDER BY d.name;

-- Example B: Find dealerships with Civic models (by model)
SELECT DISTINCT d.dealership_id, d.name, d.address, d.phone
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.VIN = v.VIN
WHERE v.model = 'Civic'
ORDER BY d.name;

-- Example C: Find dealerships with SUVs (by model type - multiple models)
SELECT DISTINCT d.dealership_id, d.name, d.address, d.phone
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.VIN = v.VIN
WHERE v.model IN ('Explorer', 'CX-5', 'Sorento')
ORDER BY d.name;

-- 6. Get all sales information for a specific dealer for a specific date range
-- Example: Sales for AutoWorld (dealership_id = 1) in May 2025
SELECT 
    sc.contract_id,
    sc.VIN,
    v.make,
    v.model,
    v.year,
    v.color,
    sc.customer_name,
    sc.sale_date,
    sc.sale_price,
    d.name AS dealership_name
FROM sales_contracts sc
JOIN vehicles v ON sc.VIN = v.VIN
JOIN inventory i ON v.VIN = i.VIN
JOIN dealerships d ON i.dealership_id = d.dealership_id
WHERE d.dealership_id = 1
  AND sc.sale_date BETWEEN '2025-05-01' AND '2025-06-30'
ORDER BY sc.sale_date;
