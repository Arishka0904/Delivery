delete from product;

INSERT INTO product (id, product_name, category, price, depth, height, weight, width,
quantity_on_pallet, quantity_in_warehouse, current_version) VALUES
(1, 'product1', 'FIRST_CATEGORY', 0.01, 1, 1, 1, 1, 1, 1, false),
(2, 'product2', 'SECOND_CATEGORY', 99.99, 2, 2, 2, 2, 2, 2, true),
(3, 'product3', 'THIRD_CATEGORY', 1, 3, 3, 3, 3, 3, 3, true),
(4, 'product3', 'THIRD_CATEGORY', 1, 3, 3, 3, 3, 3, 3, true),
(5, 'product3', 'SECOND_CATEGORY', 1, 3, 3, 3, 3, 3, 3, true);

