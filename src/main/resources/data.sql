INSERT INTO product (id, name, price) VALUES
  (1001, 'Blumentopf', 5.99),
  (1002, 'Rindenmulch', 16.99),
  (1003, 'Wasserwaage', 19.99),
  (1004, 'Schnitzelbrötchen', 3.50),
  (1005, 'Schraube', 0.22),
  (1006, 'Dampfstrahler', 150.00);

INSERT INTO inventory (product_id, minimum_order, quantity_in_stock) VALUES
  (1001, 1, 1502),
  (1002, 1, 821),
  (1003, 1, 766),
  (1004, 1, 0),
  (1005, 10, 5000000),
  (1006, 1, 387);