SET time_zone = "+07:00";

drop database netdb; 

CREATE DATABASE IF NOT EXISTS netdb;
USE netdb;

CREATE TABLE accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(30) NOT NULL,
  pwd varchar(30) NOT NULL,
  time_remaining int(11) NOT NULL DEFAULT 0,
  state tinyint(1) NOT NULL DEFAULT 0,
  start_date date DEFAULT NULL,
  end_date date DEFAULT NULL
);

INSERT INTO accounts (name, pwd, time_remaining, state, start_date, end_date) VALUES
('1', '1', 26, 0, '2020-06-29', '2020-06-29'),
('2', '2', 71, 0, '2020-07-04', '2020-07-04'),
('3', '3', 75, 0, '2020-06-25', '2020-06-25'),
('4', '4', 87, 0, '2020-06-25', '2020-06-25'),
('5', '5', 77, 0, '2020-06-25', '2020-06-25'),
('6', '6', 30, 0, NULL, NULL),
('7', '7', 0, 0, '2020-07-04', '2020-07-04'),
('8', '', 10, 0, NULL, NULL),
('khach', 'admin', 161084, 0, '2020-07-08', '2020-07-08'),
('nhan', 'nhan', 106, 0, '2020-07-08', '2020-07-08'),
('nhannguyen', '1', 1000, 0, NULL, NULL),
('nhatpro', 'nhatpro', 195, 0, NULL, NULL),
('thuan', 'thuan', 0, 0, '2020-07-08', '2020-07-08'),
('thuannt', 'thuannt', 24, 0, '2020-07-04', '2020-07-04'),
('thuantruong', 'thuantruong', 100, 0, NULL, NULL);

CREATE TABLE machines (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id int DEFAULT NULL,
  state tinyint(1) NOT NULL DEFAULT 0,
  using_time int(11) NOT NULL DEFAULT 0,
  FOREIGN KEY (account_id) REFERENCES accounts(id)
);

INSERT INTO machines (account_id, state, using_time) VALUES
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0),
(NULL, 0, 0);

CREATE TABLE statistical (
  date date NOT NULL PRIMARY KEY,
  income double NOT NULL DEFAULT 0
);
  
INSERT INTO statistical (date, income) VALUES
('2020-06-07', 123214),
('2020-06-08', 123400),
('2020-06-09', 120000),
('2020-06-10', 1243330),
('2020-06-11', 20000),
('2020-06-12', 234000),
('2020-06-13', 1231110),
('2020-06-14', 200),
('2020-06-15', 32500),
('2020-06-16', 45000),
('2020-06-17', 32000),
('2020-06-25', 46400),
('2020-06-26', 200),
('2020-06-27', 7311),
('2020-06-29', 40100),
('2020-07-04', 100200),
('2020-07-08', 14100);

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(40) DEFAULT NULL,
  price double DEFAULT NULL
);

INSERT INTO products (name, price) VALUES
('Sting dâu', 12000),
('Sting nho', 12000),
('Mì tôm', 15000),
('Mì trứng', 20000),
('But chi', 5000),
('So tay 500 trang', 40000),
('So tay loai 1', 55000);

CREATE TABLE customers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(40) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  sale_amount double DEFAULT NULL
);

INSERT INTO customers (name, phone, sale_amount) VALUES
('Nguyen Van A', '08823451', 13000000),
('Tran Ngoc Han', '0908256478', 280000),
('Tran Ngoc Linh', '0938776266', 3860000),
('Tran Minh Long', '0917325476', 250000),
('Le Nhat Minh', '08246108', 21000),
('Le Hoai Thuong', '08631738', 915000),
('Nguyen Van Tam', '0916783565', 12500),
('Phan Thi Thanh', '0938435756', 365000),
('Le Ha Vinh', '08654763', 70000),
('Ha Duy Lap', '08768904', 67500);

CREATE TABLE staffs (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(40) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  start_date date DEFAULT NULL
);

INSERT INTO staffs (name, phone, start_date) VALUES
('Nguyen Nhu Nhut', '0927345678', '2006-04-13'),
('Le Thi Phi Yen', '0987567390', '2006-04-21'),
('Nguyen Van B', '0997047382', '2006-04-27'),
('Ngo Thanh Tuan', '0913758498', '2006-06-24'),
('Nguyen Thi Truc Thanh', '0918590387', '2006-07-20');


CREATE TABLE bill_details (
  id INT AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL,
  quantity int(11) DEFAULT NULL,
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE bills (
  id INT AUTO_INCREMENT PRIMARY KEY,
  date date DEFAULT NULL,
  customer_id INT DEFAULT NULL,
  staff_id INT DEFAULT NULL,
  amount double DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (staff_id) REFERENCES staffs(id)
);

-- INSERT INTO bills (date, customer_id, staff_id, amount) VALUES 




