INSERT INTO customers (id, name, email, address) VALUES (1, 'Carlos Perez', 'carlos.perez@example.com', '789 Pine St, Sometown, USA');
INSERT INTO customers (id, name, email, address) VALUES (2, 'Ana Gomez', 'ana.gomez@example.com', '101 Maple St, Anothertown, USA');

INSERT INTO categories (category_name, category_type) VALUES ('Ropa', 'Vestimenta');
INSERT INTO categories (category_name, category_type) VALUES ('Juguetes', 'Infantil');

INSERT INTO products (name, category_id) VALUES ('Camiseta', 1);
INSERT INTO products (name, category_id) VALUES ('Muñeca', 2);

INSERT INTO deliveries (type, status) VALUES ('Economica', 'En Camino');
INSERT INTO deliveries (type, status) VALUES ('Urgente', 'Entregado');

INSERT INTO orders (order_Date, customer_id, delivery_id) VALUES (CURRENT_DATE, 1, 1);
INSERT INTO orders (order_Date, customer_id, delivery_id) VALUES (CURRENT_DATE, 2, 2);