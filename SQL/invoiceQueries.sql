-- Start Queries

-- 1) A query to retrieve the major fields for every person
SELECT p.PersonId, p.FirstName, p.LastName, a.Street, a.City, a.State, a.Country, a.Zip, Email FROM Email e
JOIN Person p ON e.PersonId = p.PersonId
JOIN Address a ON p.AddressId = a.AddressId;

-- 2) A query to add an email address to a specific person
INSERT INTO Email (
	Email, PersonId)
    VALUES ('spamemail@gmail.com', '2');
    
-- 3) A query to change the address of an apartment in an agreement (lease or sale)
UPDATE Address a
JOIN LeaseAgreement la ON a.AddressId = la.AddressId
SET a.Street = '1224 OldMother Hall'
WHERE la.ProductId = '6';

-- 4) A query to remove a given agreement record.
DELETE FROM InvoiceProduct WHERE ProductId = 13;
DELETE FROM LeaseAgreement WHERE ProductId = 13;
DELETE FROM Product WHERE ProductId = 13;

-- 5) A query to get all the products in a particular invoice
Select ip.ProductId, p.ProductCode, p.ProductType from Invoice i 
JOIN InvoiceProduct ip ON i.InvoiceId = ip.InvoiceId 
JOIN Product p ON p.ProductId = ip.ProductId
where i.InvoiceId = 1;

-- 6) A query to get all the invoices of a particular customer.
SELECT * FROM Invoice i
JOIN InvoiceProduct ip ON i.InvoiceId = ip.InvoiceId
WHERE CustomerId = '1';

-- 7) A query that "adds" a particular product to a particular invoice
INSERT INTO InvoiceProduct (
	ProductId, InvoiceId, CustomerId)
    VALUES('6', '2', '2');

-- 8) A query to find the total of all per-unit costs of all lease-agreements
SELECT SUM(Price)
FROM LeaseAgreement;

-- 9) A query to find the total of all per-unit costs of all sales-agreements
SELECT SUM(TotalCost)
FROM SaleAgreement;

-- 10) A query to find the total number of agreements sold on a particular date
SELECT COUNT(SaleDate)
FROM SaleAgreement
WHERE SaleDate = '2019/01/05';
-- 11) A query to find the total number of invoices for every realtor
SELECT p.FirstName, p.LastName, COUNT(InvoiceId)
FROM Invoice i
JOIN Person p ON i.Realtor = p.PersonId
GROUP BY Realtor;
-- 12) A query to find the total number of invoices for a particular agreement
SELECT p.ProductCode, COUNT(InvoiceProductId)
FROM InvoiceProduct ip
JOIN Product p ON ip.ProductId = p.ProductId
WHERE ip.ProductId = '6';

-- 13) A query to find the total revenue generated (excluding fees and taxes) on a particular date from all agreements
SELECT (SELECT IFNULL(SUM(Price),0) FROM LeaseAgreement WHERE StartDate = '2019/01/05') +
(SELECT IFNULL(SUM(MonthlyPayment),0) FROM SaleAgreement WHERE SaleDate = '2019/01/05')
FROM DUAL;

-- FIXME:: 14) A query to find the total quantities sold (excluding fees and taxes) of each category of
-- services (parking-passes and amenities) in all the existing invoices
SELECT ip.InvoiceId, SUM(p.Quantity), COUNT(a.ProductId) 
FROM InvoiceProduct ip
JOIN ParkingPass p ON ip.ProductId = p.ProductId
JOIN Amenity a ON ip.ProductId = a.ProductId
GROUP BY ip.InvoiceId;

-- 15) A query to find any invoice that includes multiple instances of the same agreement
SELECT InvoiceId, COUNT(ProductId)
FROM InvoiceProduct
GROUP BY ProductId
HAVING COUNT(*) > 1;

-- 16)  A query to find any and all invoices where the realtor is the same as the primary
-- contact of the invoice’s customer
SELECT ip.InvoiceId
FROM InvoiceProduct ip
JOIN Invoice i ON ip.InvoiceId = i.InvoiceId
JOIN Customer c ON ip.CustomerId = c.CustomerId
Where i.Realtor = c.PersonId;