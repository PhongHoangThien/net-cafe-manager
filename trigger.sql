use Internet;

DELIMITER $$

CREATE TRIGGER deleteAccountSetEmployeeAccountNull
    AFTER UPDATE ON Account
    FOR EACH ROW
BEGIN
    DECLARE isDeleted DATETIME;
	DECLARE accountId INT;
    IF OLD.deletedAt <> NEW.deletedAt THEN
        

        SET isDeleted = NEW.deletedAt;
        SET accountId = NEW.id;

        IF isDeleted IS NOT NULL THEN
    UPDATE Employee SET accountID = NULL WHERE accountID = accountId;
END IF;
END IF;
END$$

DELIMITER ;
DELIMITER $$

CREATE TRIGGER trg_INVOICEDETAIL_INSERT
    AFTER INSERT ON InvoiceDetail
    FOR EACH ROW
BEGIN
    DECLARE total FLOAT;
    DECLARE invoiceId INT;
    DECLARE invoiceType INT;
    
    SET invoiceId = NEW.invoiceId;
    SET total = (SELECT SUM(price * quantity) FROM InvoiceDetail WHERE invoiceId = invoiceId);
    UPDATE Invoice SET total = total WHERE id = invoiceId;

    SELECT `type` INTO invoiceType FROM Invoice WHERE id = invoiceId LIMIT 1;

    IF invoiceType = 1 THEN
    UPDATE Session SET serviceCost = total WHERE computerID = (SELECT computerID FROM InvoiceDetail WHERE invoiceId = invoiceId LIMIT 1);
END IF;
END$$

DELIMITER ;
DELIMITER $$

CREATE TRIGGER updateProductStockWhenCreateInvoiceDetail
    AFTER INSERT ON InvoiceDetail
    FOR EACH ROW
BEGIN
    DECLARE invoiceId INT;
    DECLARE invoiceType INT;
    DECLARE quantity INT;
    DECLARE productId INT;
    DECLARE stock INT;

    SET invoiceId = NEW.invoiceId;
    SET quantity = NEW.quantity;
    SET productId = NEW.productId;

    SELECT `type` INTO invoiceType FROM Invoice WHERE id = invoiceId LIMIT 1;
    SELECT stock INTO stock FROM Product WHERE id = productId LIMIT 1;

    IF stock > 0 THEN
        IF invoiceType = 1 THEN
    UPDATE Product SET stock = stock - quantity WHERE id = productId;
    ELSE
    UPDATE Product SET stock = stock + quantity WHERE id = productId;
END IF;
END IF;
END$$

DELIMITER ;
DELIMITER $$

CREATE TRIGGER updateProductStockWhenDeleteInvoice
    AFTER UPDATE ON Invoice
    FOR EACH ROW
BEGIN
    DECLARE deletedAt DATETIME;
	DECLARE invoiceId INT;
    IF OLD.deletedAt <> NEW.deletedAt THEN
        

        SET deletedAt = NEW.deletedAt;
        SET invoiceId = NEW.id;

        IF deletedAt IS NOT NULL THEN
    DELETE FROM InvoiceDetail WHERE invoiceId = invoiceId;
END IF;
END IF;
END$$

DELIMITER ;


