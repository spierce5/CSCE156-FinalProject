USE spierce;
DROP TABLE IF EXISTS LeaseAgreement;
DROP TABLE IF EXISTS SaleAgreement;
DROP TABLE IF EXISTS Amenity;
DROP TABLE IF EXISTS ParkingPass;
DROP TABLE IF EXISTS InvoiceProduct;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Invoice;
DROP TABLE IF EXISTS Email;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Address;

CREATE TABLE Address (
	AddressId int NOT NULL AUTO_INCREMENT,
    Street varchar(255),
    City varchar(50),
    State varchar(10),
    Country varchar(50),
    Zip varchar(10),
    PRIMARY KEY (AddressId)
    );

CREATE TABLE Person (
	PersonId int NOT NULL AUTO_INCREMENT,
    PersonCode varchar(255),
	FirstName varchar(255),
    LastName varchar(255),
    AddressId int,
    PRIMARY KEY (PersonId),
    FOREIGN KEY (AddressId) REFERENCES Address(AddressId)
    );
    

CREATE TABLE Customer (
	CustomerId int NOT NULL AUTO_INCREMENT,
	CustomerCode varchar(50),
    customerType varchar(5),
    ClientName varchar(50),
    PersonId int,
    AddressId int,
    PRIMARY KEY (CustomerId),
    FOREIGN KEY (PersonId) REFERENCES Person(PersonId),
    FOREIGN KEY (AddressId) REFERENCES Address(AddressId)
    );

CREATE TABLE Email (
	EmailId int NOT NULL AUTO_INCREMENT,
    Email varchar(255),
    PersonId int,
    PRIMARY KEY (EmailId),
    FOREIGN KEY (PersonId) REFERENCES Person(PersonId)
    );

CREATE TABLE Invoice (
	InvoiceId int NOT NULL AUTO_INCREMENT,
    InvoiceCode varchar(10),
    InvoiceDate varchar(10),
    Realtor int not null,
    CustomerId int not null,
    PRIMARY KEY (InvoiceId),
    FOREIGN KEY (Realtor) REFERENCES Person(PersonId),
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
    );

CREATE TABLE Product (
	ProductId int NOT NULL AUTO_INCREMENT,
    ProductCode varchar(10),
    ProductType varchar(20),
    Quantity int,
    PRIMARY KEY (ProductId)
    );

CREATE TABLE LeaseAgreement (
	ProductId int REFERENCES Product(ProductId),
    StartDate varchar(10),
    EndDate varchar(10),
    Price double,
    Deposit double,
    AddressId int,
    PRIMARY KEY (ProductId)
    );

CREATE TABLE SaleAgreement (
	ProductId int REFERENCES Product(ProductId),
    SaleDate varchar(20),
    TotalCost double,
    TotalMonths double,
    InitialPayment double,
    MonthlyPayment double,
    InterestRate double,
    AddressId int,
    PRIMARY KEY (ProductId)
    );
    


CREATE TABLE Amenity (
	ProductId int REFERENCES Product(ProductId),
    Price double,
    Description varchar(255),
	PRIMARY KEY (ProductId)
    );
  
    
 

CREATE TABLE ParkingPass (
	ProductId int not null,
    LeaseCode varchar(10),
    ParkingFee double,
    PRIMARY KEY (ProductId)
    );

	
CREATE TABLE InvoiceProduct (
	InvoiceProductId int NOT NULL AUTO_INCREMENT,
    ProductId int,
    InvoiceId int,
    PRIMARY KEY (InvoiceProductId),
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (InvoiceId) REFERENCES Invoice(InvoiceId)
    );

-- Data Entry
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES ('402 Small', 'Milwaukee', 'WS', 'USA','98765');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES ('999 W Dredge', 'Savage', 'MN', 'USA', '83654');
INSERT INTO Address(Street, City, State, Country, Zip)
    VALUES ('755 Winston Drive','Ashland','KY','USA','52542');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 111 Michigan Drive', 'Maryville', 'TX',  'USA', '54632');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 222 Red Street', 'Tampa', 'FL',  'USA', '98742');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 33 Clifford Drive', 'San Antonio', 'TX',  'USA', '58525');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 4444 W Madison', 'Wick', 'OK',  'USA', '45645');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 505 Knox', 'Tudelo', 'IL',  'USA', '88888');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 1221 Whitehall Drive', 'Minneapolis', 'MN',  'USA', '89898');
INSERT INTO Address (Street, City, State, Country, Zip)
    VALUES (' 9215 Marx Plaza', 'Northington', 'ND',  'USA', '25852');
    
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
	VALUES 	('s123', 'Spadling', 'Jeff', '1');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('we87', 'Gekko', 'Gordon', '2');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('29qe', 'Max', 'Roach', '3');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('23js', 'James', 'Dean', '4');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('tc34', 'John', 'Goodham', '5');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('98sf', 'Michael', 'Jordan', '6');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('sd23', 'Abe', 'Lincoln', '7');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('19le', 'Sonny','Rollins','8');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('38yu', 'Dizzy','Gillespie','9');
INSERT INTO Person (PersonCode, FirstName, LastName, AddressId)
    VALUES ('me34', 'Miles','Davis','10');


INSERT INTO Email (Email, PersonId)
    VALUES ('bfox@gmail.com', '1');
INSERT INTO Email (Email, PersonId)
    VALUES ('slyguy@hotmail.com', '2');
INSERT INTO Email (Email, PersonId)
    VALUES ('hurndall@cse.unl.edu','2');
INSERT INTO Email (Email, PersonId)
    VALUES ('masta@hotmail.com','3');
INSERT INTO Email (Email, PersonId)
    VALUES ('Drummer@gmail.com','3');
INSERT INTO Email (Email, PersonId)
    VALUES ('jdean4@cse.unl.edu','4');
INSERT INTO Email (Email, PersonId)
    VALUES ('jg@unl.edu','5');
INSERT INTO Email (Email, PersonId)
    VALUES ('bballstar@yahoo.com','6');
INSERT INTO Email (Email, PersonId)
    VALUES ('mistaprez@hotmail.com','7');
INSERT INTO Email (Email, PersonId)
    VALUES ('honestguy@gmail.com','7');
INSERT INTO Email (Email, PersonId)
    VALUES ('a.lincoln@usmc.mil','7');
INSERT INTO Email (Email, PersonId)
    VALUES ('saxplayer@hotmail.com','8');
INSERT INTO Email (Email, PersonId)
    VALUES ('jazzzz@gmail.com','8');
INSERT INTO Email (Email, PersonId)
    VALUES ('bepop.jazz@gmail.com','9');
INSERT INTO Email (Email, PersonId)
    VALUES ('cool.jazz@gmail.com','10');
INSERT INTO Email (Email, PersonId)
    VALUES ('trumpetguy@hotmail.com','10');

INSERT INTO Customer ( CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M001', 'G', 'Vendome Mutual','1', '1');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M002','L','Canon International', '2', '2');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M003','G', 'Brooky Stone', '3','3');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M004','L', 'Stoney Broke', '4','4');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M005','G', 'Expo Inc', '5','5');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M006','G', 'Winneman Bros', '6','6');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M007','G', 'Corsair Inc', '7','7');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M008','G', 'Johnson Realty', '8','8');
INSERT INTO Customer(CustomerCode, CustomerType, ClientName, PersonId, AddressId)
	VALUES ('M009','G', 'Winnepeg ltd', '9','9');

INSERT INTO Product (ProductCode, ProductType, Quantity)
    VALUES ('33h9','A', '1');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('3423','A', '2');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('85gq','A', '3');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('777t','A', '4');
INSERT INTO Product (ProductCode, ProductType, Quantity)
    VALUES ('88jk','S', '1');
INSERT INTO Product (ProductCode, ProductType, Quantity)
    VALUES ('121s','S', '3');
INSERT INTO Product (ProductCode, ProductType, Quantity)
    VALUES ('354f','S', '20');
INSERT INTO Product (ProductCode, ProductType, Quantity)
    VALUES ('99ll','S', '12');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('1234','P', '3');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('2134','P', '1');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('2341','P', '33');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('4414','P', '2');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('3493','L', '4');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('9849','L', '55');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('sd87','L', '6');
INSERT INTO Product (ProductCode, ProductType, Quantity)
	VALUES ('q124','L', '8');

INSERT INTO Amenity(ProductId, Price, Description)
    VALUES ('1', '75', 'table');
INSERT INTO Amenity(ProductId, Price, Description)
    VALUES ('2', '35', 'Door Knobs');
INSERT INTO Amenity(ProductId, Price, Description)
    VALUES ('3', '100', 'Desk Lamp');
INSERT INTO Amenity(ProductId, Price, Description)
    VALUES ('4', '85', 'Printer');
    
INSERT INTO SaleAgreement(ProductId, SaleDate, TotalCost, TotalMonths, InitialPayment, MonthlyPayment, InterestRate, AddressId)
	VALUES('5', '2019-01-05 10:00', '133000', '60', '10000', '500', '2', '1');
INSERT INTO SaleAgreement(ProductId, SaleDate, TotalCost, TotalMonths, InitialPayment, MonthlyPayment, InterestRate, AddressId)
	VALUES('6', '2019-02-07 15:55', '300000', '60', '20000', '750', '4', '2');
INSERT INTO SaleAgreement(ProductId, SaleDate, TotalCost, TotalMonths, InitialPayment, MonthlyPayment, InterestRate, AddressId)
	VALUES('7', '2019-01-05 23:24', '250000', '60', '15000', '600', '5', '3');
INSERT INTO SaleAgreement(ProductId, SaleDate, TotalCost, TotalMonths, InitialPayment, MonthlyPayment, InterestRate, AddressId)
	VALUES('8', '2018-11-30 19:08', '332000', '90', '45000', '800', '6', '4');
    
INSERT INTO ParkingPass (ProductId, ParkingFee)
    VALUES('9', '25');
INSERT INTO ParkingPass (ProductId, ParkingFee, LeaseCode)
    VALUES('10', '15', '3493');
INSERT INTO ParkingPass (ProductId, ParkingFee)
    VALUES('11', '20');
INSERT INTO ParkingPass (ProductId, ParkingFee, LeaseCode)
    VALUES('12', '25', '9849');

INSERT INTO LeaseAgreement (ProductId, StartDate, EndDate, Price, Deposit, AddressId)
    VALUES ('13', '2017-01-13', '2018-01-12', '765', '600', '5');
INSERT INTO LeaseAgreement (ProductId, StartDate, EndDate, Price, Deposit, AddressId)
    VALUES ('14', '2018-02-14', '2018-04-12', '928', '800', '6');
INSERT INTO LeaseAgreement (ProductId, StartDate, EndDate, Price, Deposit, AddressId)
    VALUES ('15', '2016-03-12', '2018-03-19', '1000', '800', '7');
INSERT INTO LeaseAgreement (ProductId, StartDate, EndDate, Price, Deposit, AddressId)
    VALUES ('16', '2018-09-01', '2019-11-12', '825', '500', '8');

INSERT INTO Invoice (InvoiceCode, InvoiceDate, Realtor, CustomerId)
	VALUES ('INV001', '2018-12-20', '7', '1');
INSERT INTO Invoice (InvoiceCode, InvoiceDate, Realtor, CustomerId)
	VALUES ('INV002', '2017-06-15', '8', '2');
INSERT INTO Invoice (InvoiceCode, InvoiceDate, Realtor, CustomerId)
	VALUES ('INV003', '2019-08-15', '9', '3');
INSERT INTO Invoice (InvoiceCode, InvoiceDate, Realtor, CustomerId)
	VALUES ('INV004', '2017-04-01', '8', '4');
INSERT INTO Invoice (InvoiceCode, InvoiceDate, Realtor, CustomerId)
	VALUES ('INV005', '2018-09-20', '10', '5');
    

INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('1', '1');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('2', '2');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('3', '3');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('4', '3');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('5', '1');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('6', '2');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('7', '3');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('8', '1');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('9', '2');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('10', '4');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('11', '4');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('12', '5');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('13', '4');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('14', '3');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('15', '5');
INSERT INTO InvoiceProduct (ProductId, InvoiceId)
    VALUES ('16', '5');






