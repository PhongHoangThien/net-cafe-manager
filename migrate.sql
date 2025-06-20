drop database Internet;

CREATE DATABASE IF NOT EXISTS Internet;
USE Internet;
CREATE TABLE Account (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         balance FLOAT NOT NULL,
                         createdAt DATETIME(6) NOT NULL,
                         deletedAt DATETIME(6),
                         password VARCHAR(45) NOT NULL,
                         role INT,
                         username VARCHAR(45) NOT NULL
);

CREATE TABLE Computer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          createdAt DATETIME(6) NOT NULL,
                          deletedAt DATETIME(6),
                          name VARCHAR(100) NOT NULL,
                          price FLOAT NOT NULL,
                          status INT,
                          type INT
);

CREATE TABLE ComputerUsage (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               computerID INT,
                               createdAt DATETIME(6) NOT NULL,
                               endAt DATETIME(6),
                               isEmployeeUsing BIT NOT NULL,
                               totalMoney FLOAT,
                               usedByAccountId INT,
                               usedBy INT,
                               FOREIGN KEY (computerID) REFERENCES Computer (id) ON DELETE CASCADE
);

CREATE TABLE Employee (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          accountID INT,
                          createdAt DATETIME(6) NOT NULL,
                          deletedAt DATETIME(6),
                          name VARCHAR(100) NOT NULL,
                          otherInformation VARCHAR(255),
                          salaryPerHour INT,
                          phoneNumber VARCHAR(15),
                          address VARCHAR(250),
                          FOREIGN KEY (accountID) REFERENCES Account (id) ON DELETE CASCADE
);

CREATE TABLE Invoice (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         computerId INT,
                         createdAt DATETIME(6) NOT NULL,
                         createdBy INT NOT NULL,
                         createdToAccountId INT,
                         deletedAt DATETIME(6),
                         isPaid BIT NOT NULL,
                         note TEXT,
                         status INT NOT NULL,
                         total FLOAT,
                         type INT NOT NULL,
                         FOREIGN KEY (computerId) REFERENCES Computer (id) ON DELETE CASCADE
);

CREATE TABLE InvoiceDetail (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               invoiceId INT NOT NULL,
                               price FLOAT NOT NULL,
                               productId INT NOT NULL,
                               quantity INT NOT NULL,
                               FOREIGN KEY (invoiceId) REFERENCES Invoice (id) ON DELETE CASCADE
);

CREATE TABLE Session (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         computerID INT NOT NULL,
                         prepaidAmount FLOAT,
                         serviceCost INT NOT NULL,
                         startTime DATETIME(6) NOT NULL,
                         totalTime INT,
                         usedCost INT NOT NULL,
                         usedTime INT NOT NULL,
                         usingBy INT NULL,
                         FOREIGN KEY (computerID) REFERENCES Computer (id) ON DELETE CASCADE,
                         FOREIGN KEY (usingBy) REFERENCES Account (id) ON DELETE CASCADE
);

CREATE TABLE Message (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         sessionId INT NOT NULL,
                         content VARCHAR(255),
                         fromType INT,
                         createdAt DATETIME(6),
                         FOREIGN KEY (sessionId) REFERENCES Session (id) ON DELETE CASCADE
);

CREATE TABLE Product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         createdAt DATETIME(6) NOT NULL,
                         deletedAt DATETIME(6),
                         description TEXT NOT NULL,
                         image VARCHAR(255) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         price FLOAT NOT NULL,
                         stock INT NOT NULL,
                         type INT
);

INSERT INTO `Account` (`id`, `balance`, `createdAt`, `deletedAt`, `password`, `role`, `username`) VALUES
                                                                                                      (1, 0, '2023-03-10 00:00:00', NULL, 'admin', 0, 'admin'),
                                                                                                      (2, 0, '2023-03-10 00:00:00', NULL, 'manager', 1, 'manager'),
                                                                                                      (3, 0, '2023-03-10 00:00:00', NULL, '123456', 2, 'employee1'),
                                                                                                      (4, 0, '2023-03-10 15:25:30', NULL, 'employee2', 2, 'employee2'),
                                                                                                      (5, 0, '2023-03-10 15:25:30', NULL, 'employee3', 2, 'employee3'),
                                                                                                      (6, 0, '2023-03-10 15:25:30', NULL, 'employee4', 2, 'employee4'),
                                                                                                      (7, 0, '2023-03-10 15:25:30', NULL, 'employee5', 2, 'employee5'),
                                                                                                      (8, 15000, '2023-01-06 20:45:37', NULL, 'test', 3, 'test'),
                                                                                                      (9, 15000, '2023-01-06 20:45:37', NULL, 'm49UJn9', 3, 'cdeathridge0'),
                                                                                                      (10, 31000, '2022-08-17 15:47:47', NULL, 'runadg', 3, 'cardley1'),
                                                                                                      (11, 7000, '2022-04-26 23:22:21', NULL, 'lcblEtkD', 3, 'ddudderidgem');

INSERT INTO Employee (id, accountID, createdAt, deletedAt, name, otherInformation, salaryPerHour, phoneNumber, address)
VALUES
    (1, 1, '2023-03-10 15:25:30.355000', NULL, 'admin', 'admin', 0, '0376507260', 'Tran Binh Trong'),
    (2, 2, '2023-03-10 15:25:30.361000', NULL, 'Nguyễn Van A', 'Chủ cửa hàng', 0, '0376507260', 'An Duong Vuong'),
    (3, 3, '2023-03-10 15:25:30.365000', NULL, 'Nhân viên 1', 'Nhân viên 1', 16000, '000000001', 'An Duong Vuong'),
    (4, 4, '2023-03-10 15:25:30.368000', NULL, 'Nhân viên 2', 'Nhân viên 2', 20000, '0000002121', 'An Duong Vuong'),
    (5, 5, '2023-03-10 15:25:30.372000', NULL, 'Nhân viên 3', 'Nhân viên 3', 22000, '0010101024', 'An Duong Vuong'),
    (6, 6, '2023-03-10 15:25:30.374000', NULL, 'Nhân viên 4', 'Nhân viên 4', 10000, '00602588974', 'An Duong Vuong'),
    (7, 7, '2023-03-10 15:25:30.377000', NULL, 'Nhân viên 5', 'Nhân viên 5', 15000, '06241520522', 'An Duong Vuong');

INSERT INTO `Computer` (`id`, `createdAt`, `deletedAt`, `name`, `price`, `status`, `type`) VALUES
                                                                                               (1, '2023-03-10 15:25:30', NULL, 'Máy 1', 5000, 2, 1),
                                                                                               (2, '2023-03-10 15:25:30', NULL, 'Máy 2', 10000, 2, 0),
                                                                                               (3, '2023-03-10 15:25:30', NULL, 'Máy 3', 5000, 2, 1),
                                                                                               (4, '2023-03-10 15:25:30', NULL, 'Máy 4', 5000, 2, 1),
                                                                                               (5, '2023-03-10 15:25:30', NULL, 'Máy 5', 5000, 2, 1),
                                                                                               (6, '2023-03-10 15:25:30', NULL, 'Máy 6', 10000, 2, 0),
                                                                                               (7, '2023-03-10 15:25:30', NULL, 'Máy 7', 10000, 2, 0),
                                                                                               (8, '2023-03-10 15:25:30', NULL, 'Máy 8', 10000, 2, 0),
                                                                                               (9, '2023-03-10 15:25:30', NULL, 'Máy 9', 5000, 2, 1),
                                                                                               (10, '2023-03-10 15:25:30', NULL, 'Máy 10', 5000, 2, 1),
                                                                                               (11, '2023-03-10 15:25:30', NULL, 'Máy 11', 5000, 2, 1),
                                                                                               (12, '2023-03-10 15:25:30', NULL, 'Máy 12', 10000, 2, 0),
                                                                                               (13, '2023-03-10 15:25:30', NULL, 'Máy 13', 5000, 2, 1),
                                                                                               (14, '2023-03-10 15:25:30', NULL, 'Máy 14', 5000, 2, 1),
                                                                                               (15, '2023-03-10 15:25:30', NULL, 'Máy 15', 5000, 2, 1),
                                                                                               (16, '2023-03-10 15:25:30', NULL, 'Máy 16', 10000, 2, 0),
                                                                                               (17, '2023-03-10 15:25:30', NULL, 'Máy 17', 10000, 2, 0),
                                                                                               (18, '2023-03-10 15:25:30', NULL, 'Máy 18', 10000, 2, 0),
                                                                                               (19, '2023-03-10 15:25:30', NULL, 'Máy 19', 5000, 2, 1),
                                                                                               (20, '2023-03-10 15:25:30', NULL, 'Máy 20', 5000, 2, 1);

INSERT INTO ComputerUsage
(id, computerID, createdAt, endAt, isEmployeeUsing, totalMoney, usedByAccountId, usedBy)
VALUES
    (1, 1, '2021-07-25 05:24:58', '2021-07-25 10:24:58', 0, 25000, NULL, NULL),
    (2, 2, '2021-11-19 20:32:10', '2021-11-19 22:56:10', 0, 12000, 15, NULL),
    (3, 3, '2021-08-06 06:01:56', '2021-08-06 08:07:56', 0, 21000, 67, NULL),
    (4, 4, '2022-11-16 06:18:13', '2022-11-16 10:42:13', 0, 22000, NULL, NULL),
    (5, 5, '2022-02-22 07:11:16', '2022-02-22 08:53:16', 0, 17000, NULL, NULL);

INSERT INTO Invoice
(id, computerId, createdAt, createdBy, createdToAccountId, deletedAt, isPaid, note, status, total, type)
VALUES
    (1, NULL, '2021-03-18 04:34:21', 4, NULL, NULL, 0, NULL, 3, 10971090, 0),
    (2, NULL, '2022-08-06 11:57:28', 3, NULL, NULL, 0, NULL, 3, 3784960, 0),
    (3, NULL, '2022-09-12 00:06:24', 4, NULL, NULL, 0, NULL, 3, 10718270, 0),
    (4, NULL, '2021-03-09 16:37:19', 2, NULL, NULL, 0, NULL, 3, 2316500, 0);

INSERT INTO InvoiceDetail (id, invoiceId, price, productId, quantity) VALUES
                                                                          (1, 1, 466600000, 15, 21),
                                                                          (2, 1, 61710000, 9, 19),
                                                                          (3, 2, 67050000, 13, 22),
                                                                          (4, 2, 86625000, 20, 14),
                                                                          (5, 2, 87150000, 6, 6),
                                                                          (6, 2, 44170000, 12, 13),
                                                                          (7, 3, 199750000, 18, 20),
                                                                          (8, 3, 631200000, 16, 9),
                                                                          (9, 3, 80190000, 10, 13),
                                                                          (10, 4, 231650000, 18, 10);

INSERT INTO Product (id, createdAt, deletedAt, description, image, name, price, stock, type) VALUES
                                                                                                 (1, '2023-03-01 01:16:46.463', NULL, 'Com chiên bò', '/images/Cơm_chiên_bò.jpg', 'Cơm chiên bò', 30000, -1, 0),
                                                                                                 (2, '2023-03-01 01:16:46.463', NULL, 'Com chiên trứng', '/images/Cơm_chiên_trứng.jpg', 'Cơm chiên trứng', 30000, -1, 0),
                                                                                                 (3, '2023-03-01 01:16:46.463', NULL, 'Nui xào bò', '/images/Nui_xào_bò.jpg', 'Nui xào bò', 30000, -1, 0),
                                                                                                 (4, '2023-03-01 01:16:46.463', NULL, 'Mì tôm', '/images/Mì_tôm.jpg', 'Mì tôm', 10000, 20, 0),
                                                                                                 (5, '2023-03-01 01:16:46.463', NULL, 'Mì tôm trứng', '/images/Mì_tôm_trứng.jpg', 'Mì tôm trứng', 20000, 40, 0),
                                                                                                 (6, '2023-03-01 01:16:46.463', NULL, 'Cá viên chiên', '/images/Cá_viên_chiên.jpg', 'Cá viên chiên', 15000, 36, 0),
                                                                                                 (7, '2023-03-01 01:16:46.463', NULL, 'Mì xào bò', '/images/Mì_xào_bò.jpg', 'Mì xào bò', 30000, -2, 0),
                                                                                                 (8, '2023-03-01 01:16:46.463', NULL, 'Com rang thập cẩm', '/images/Cơm_rang_thập_cẩm.jpg', 'Com rang thập cẩm', 30000, -1, 0),
                                                                                                 (9, '2023-03-01 01:16:46.463', NULL, 'Coca', '/images/Coca.jpg', 'Coca', 15000, 60, 1),
                                                                                                 (10, '2023-03-01 01:16:46.463', NULL, 'Pepsi', '/images/Pepsi.jpg', 'Pepsi', 15000, 45, 1),
                                                                                                 (11, '2023-03-01 01:16:46.463', NULL, '7up', '/images/7up.jpg', '7up', 15000, 20, 1),
                                                                                                 (12, '2023-03-01 01:16:46.463', NULL, 'Nước suối', '/images/Nước_suối.jpg', 'Nước suối', 10000, 33, 1),
                                                                                                 (13, '2023-03-01 01:16:46.463', NULL, 'Sting', '/images/Sting.jpg', 'Sting', 15000, 58, 1),
                                                                                                 (14, '2023-03-01 01:16:46.463', NULL, 'Redbull', '/images/Redbull.jpg', 'Redbull', 20000, 46, 1),
                                                                                                 (15, '2023-03-01 01:16:46.463', NULL, 'Thẻ garena 100k', '/images/Thẻ_garena_100k.jpg', 'Thẻ garena 100k', 100000, 41, 2),
                                                                                                 (16, '2023-03-01 01:16:46.463', NULL, 'Thẻ garena 200k', '/images/Thẻ_garena_200k.jpg', 'Thẻ garena 200k', 200000, 53, 2),
                                                                                                 (17, '2023-03-01 01:16:46.463', NULL, 'Thẻ viettel 100k', '/images/Thẻ_viettel_100k.jpg', 'Thẻ viettel 100k', 100000, 20, 2),
                                                                                                 (18, '2023-03-01 01:16:46.463', NULL, 'Thẻ mobi 50k', '/images/Thẻ_mobi_50k.jpg', 'Thẻ mobi 50k', 50000, 50, 2),
                                                                                                 (19, '2023-03-01 01:16:46.463', NULL, 'Phở', '/images/Phở.jpg', 'Phở', 30000, -1, 0),
                                                                                                 (20, '2023-03-01 01:16:46.463', NULL, 'Cà phê', '/images/Cà_phê.jpg', 'Cà phê', 15000, 14, 1),
                                                                                                 (21, '2023-03-01 01:16:46.463', NULL, 'Bánh mì trứng', '/images/Bánh_mì_trứng.jpg', 'Bánh mì trứng', 20000, -1, 0),
                                                                                                 (22, '2023-03-01 01:16:46.463', NULL, 'Bánh mì thịt', '/images/Bánh_mì_thịt.jpg', 'Bánh mì thịt', 20000, -1, 0);

-- ALTER TABLE `Account` ADD CONSTRAINT `UK_de34gsw4qt8auhffbna969ahp` UNIQUE (`username`);

-- ALTER TABLE `Invoice` CHANGE `total` `total` DECIMAL(10,2) DEFAULT 0;

-- ALTER TABLE `ComputerUsage` ADD CONSTRAINT `FK1ukgw2h7yqj0uqhs4kln7b0tv` FOREIGN KEY (`computerID`) REFERENCES `Computer` (`id`);
-- ALTER TABLE `ComputerUsage` ADD CONSTRAINT `FKru3cjhi3eagngqfgdf3ap0w0f` FOREIGN KEY (`usedBy`) REFERENCES `Account` (`id`);

-- ALTER TABLE `Employee` ADD CONSTRAINT `FK94h4kungplj8clo62i971c85o` FOREIGN KEY (`accountID`) REFERENCES `Account` (`id`);

-- ALTER TABLE `Invoice` ADD CONSTRAINT `FK6hqjk9ej9r883fy7sgep9efn7` FOREIGN KEY (`createdBy`) REFERENCES `Employee` (`id`);
-- ALTER TABLE `Invoice` ADD CONSTRAINT `FK937sc7o18kooa1fhxm57wld1w` FOREIGN KEY (`createdToAccountId`) REFERENCES `Account` (`id`);
-- ALTER TABLE `Invoice` ADD CONSTRAINT `FKmhlwjt2ll4e2gnxrx2i3yfljd` FOREIGN KEY (`computerId`) REFERENCES `Computer` (`id`);

-- ALTER TABLE `InvoiceDetail` ADD CONSTRAINT `FK2pri5nh9cre1wwfky1gd4egw6` FOREIGN KEY (`productId`) REFERENCES `Product` (`id`);
-- ALTER TABLE `InvoiceDetail` ADD CONSTRAINT `FKb5m8jjhsq5jxvhdbwty3d05sq` FOREIGN KEY (`invoiceId`) REFERENCES `Invoice` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

-- ALTER TABLE `Message` ADD CONSTRAINT `FK_Message_Session` FOREIGN KEY (`sessionId`) REFERENCES `Session` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

-- ALTER TABLE `Session` ADD CONSTRAINT `FKa5yuno1td1sbamjqf31ccqbde` FOREIGN KEY (`usingBy`) REFERENCES `Account` (`id`);
-- ALTER TABLE `Session` ADD CONSTRAINT `FKgy250fk87rpr8iwyiwbq3b5oi` FOREIGN KEY (`computerID`) REFERENCES `Computer` (`id`);

