CREATE DATABASE generation_expresss;
USE generation_expresss;

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('ADMIN','DRIVER') NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `drivers` (
  `id` varchar(255) NOT NULL,
  `available` bit(1) NOT NULL,
  `license` enum('A1','A2','B1','B2','B3','C1','C2','C3') NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKojm6yjeacqc5cthc73k5twsnj` (`user_id`),
  CONSTRAINT `FKfscpnjt46gco44xh86l99rxh7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `vehicles` (
  `id` varchar(255) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `capacity_kg` decimal(10,2) NOT NULL,
  `license_plate` varchar(6) NOT NULL,
  `status` enum('AVAILABLE','MAINTENANCE','ON_ROUTE','OUT_OF_SERVICE') NOT NULL,
  `type` enum('CAR','MOTORCYCLE','TRAILER','TRUCK','VAN') NOT NULL,
  `driver_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaashphrwfd4ts511y8vj785ia` (`driver_id`),
  CONSTRAINT `FKaashphrwfd4ts511y8vj785ia` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`)
);
CREATE TABLE `shipping_person` (
  `id` varchar(255) NOT NULL,
  `shipping_person_type` enum('RECIPIENT','SENDER') NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `document_number` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` int NOT NULL,
  PRIMARY KEY (`id`)
);

 CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `assigned_date` datetime(6) DEFAULT NULL,
  `estimated_delivery_date` datetime(6) NOT NULL,
  `request_date` datetime(6) NOT NULL,
  `status` enum('ASSIGNED','CANCELLED','DELIVERED','IN_TRANSIT','PENDING') NOT NULL,
  `total_cost` decimal(10,2) NOT NULL,
  `tracking_number` varchar(255) NOT NULL,
  `weight_kg` decimal(10,2) NOT NULL,
  `driver_id` varchar(255) DEFAULT NULL,
  `recipient_id` varchar(255) DEFAULT NULL,
  `sender_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKnew938pg97mqegt6j0irfoimc` (`tracking_number`),
  KEY `FKqohd0dujmkcb12rcjy4b1fj4u` (`driver_id`),
  KEY `FKogc6h3c3ai369ck7h3q0wmckm` (`recipient_id`),
  KEY `FK1jp643spwfawuuuig6sgmd65j` (`sender_id`),
  CONSTRAINT `FK1jp643spwfawuuuig6sgmd65j` FOREIGN KEY (`sender_id`) REFERENCES `shipping_person` (`id`),
  CONSTRAINT `FKogc6h3c3ai369ck7h3q0wmckm` FOREIGN KEY (`recipient_id`) REFERENCES `shipping_person` (`id`),
  CONSTRAINT `FKqohd0dujmkcb12rcjy4b1fj4u` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`)
);

CREATE TABLE `orders_status_history` (
  `id` varchar(255) NOT NULL,
  `observations` varchar(255) DEFAULT NULL,
  `shipment_status` enum('ASSIGNED','CANCELLED','DELIVERED','IN_TRANSIT','PENDING') NOT NULL,
  `update_at` datetime(6) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlue3eb6149ebq5hqidajrl44p` (`order_id`),
  CONSTRAINT `FKlue3eb6149ebq5hqidajrl44p` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

CREATE TABLE `routes` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `destination` varchar(255) NOT NULL,
  `estimated_time_minutes` int NOT NULL,
  `origin` varchar(255) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK57j3m0ufgblfjwf1or2jpyc99` (`order_id`),
  CONSTRAINT `FKqr5gx2jvqrw3earpapikyn31l` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

CREATE TABLE `deliveries` (
  `id` varchar(255) NOT NULL,
  `delivered_at` datetime(6) NOT NULL,
  `delivery_photo` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(100) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk36n9p5v7dd96hpgkwybvbogt` (`order_id`),
  CONSTRAINT `FK7isx0rnbgqr1dcofd5putl6jw` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

INSERT INTO users (id, created_at, email, first_name, last_name, password, role) VALUES
('USR001', NOW(), 'juan.perez@gmail.com', 'Juan', 'Pérez', '$2a$10$password1', 'DRIVER'),
('USR002', NOW(), 'carlos.lopez@gmail.com', 'Carlos', 'López', '$2a$10$password2', 'DRIVER'),
('USR003', NOW(), 'maria.gomez@gmail.com', 'María', 'Gómez', '$2a$10$password3', 'DRIVER'),
('USR004', NOW(), 'andres.ramirez@gmail.com', 'Andrés', 'Ramírez', '$2a$10$password4', 'DRIVER'),
('USR005', NOW(), 'laura.torres@gmail.com', 'Laura', 'Torres', '$2a$10$password5', 'DRIVER'),
('USR006', NOW(), 'daniel.rojas@gmail.com', 'Daniel', 'Rojas', '$2a$10$password6', 'DRIVER'),
('USR007', NOW(), 'sofia.castro@gmail.com', 'Sofía', 'Castro', '$2a$10$password7', 'DRIVER'),
('USR008', NOW(), 'miguel.herrera@gmail.com', 'Miguel', 'Herrera', '$2a$10$password8', 'DRIVER'),
('USR009', NOW(), 'camila.vargas@gmail.com', 'Camila', 'Vargas', '$2a$10$password9', 'DRIVER'),
('USR010', NOW(), 'admin@generationexpress.com', 'Administrador', 'Sistema', '$2a$10$admin', 'ADMIN');

INSERT INTO drivers (id, available, license, user_id) VALUES
('DRV001', b'1', 'C2', 'USR001'),
('DRV002', b'1', 'C3', 'USR002'),
('DRV003', b'1', 'B2', 'USR003'),
('DRV004', b'0', 'C1', 'USR004'),
('DRV005', b'1', 'C2', 'USR005'),
('DRV006', b'1', 'B3', 'USR006'),
('DRV007', b'0', 'C3', 'USR007'),
('DRV008', b'1', 'C1', 'USR008'),
('DRV009', b'1', 'B2', 'USR009'),
('DRV010', b'1', 'C2', NULL);

INSERT INTO vehicles (id, brand, capacity_kg, license_plate, status, type, driver_id) VALUES
('VEH001','Chevrolet',2500,'ABC123','AVAILABLE','TRUCK','DRV001'),
('VEH002','Hino',5000,'DEF456','ON_ROUTE','TRUCK','DRV002'),
('VEH003','Renault',1200,'GHI789','AVAILABLE','VAN','DRV003'),
('VEH004','Mazda',800,'JKL321','MAINTENANCE','CAR','DRV004'),
('VEH005','JAC',3500,'MNO654','AVAILABLE','TRUCK','DRV005'),
('VEH006','Foton',2800,'PQR987','ON_ROUTE','VAN','DRV006'),
('VEH007','Isuzu',4500,'STU741','OUT_OF_SERVICE','TRUCK','DRV007'),
('VEH008','Nissan',1000,'VWX852','AVAILABLE','VAN','DRV008'),
('VEH009','Kia',700,'YZA963','AVAILABLE','CAR','DRV009'),
('VEH010','Suzuki',180,'BCD159','AVAILABLE','MOTORCYCLE','DRV010');

INSERT INTO shipping_person
(id, shipping_person_type, address, city, document_number, email, first_name, last_name, phone)
VALUES
('SP001','SENDER','Cra 45 #10-20','Medellín','1010101010','ana@gmail.com','Ana','Martínez',300123456),
('SP002','RECIPIENT','Calle 80 #25-10','Bogotá','2020202020','pedro@gmail.com','Pedro','Sánchez',301234567),
('SP003','SENDER','Cra 20 #15-40','Cali','3030303030','lina@gmail.com','Lina','Rodríguez',302345678),
('SP004','RECIPIENT','Av 30 #18-15','Barranquilla','4040404040','jorge@gmail.com','Jorge','Ruiz',303456789),
('SP005','SENDER','Cra 70 #45-10','Bucaramanga','5050505050','diana@gmail.com','Diana','Moreno',304567890),
('SP006','RECIPIENT','Calle 12 #5-60','Pereira','6060606060','felipe@gmail.com','Felipe','García',305678901),
('SP007','SENDER','Cra 18 #90-12','Cartagena','7070707070','paula@gmail.com','Paula','Navarro',310111222),
('SP008','RECIPIENT','Calle 25 #30-40','Manizales','8080808080','oscar@gmail.com','Óscar','Mejía',311222333),
('SP009','SENDER','Cra 9 #40-20','Santa Marta','9090909090','juliana@gmail.com','Juliana','Ortiz',312333444),
('SP010','RECIPIENT','Calle 50 #20-90','Ibagué','1001001001','sergio@gmail.com','Sergio','Jiménez',313444555);

INSERT INTO orders
(id, assigned_date, estimated_delivery_date, request_date, status, total_cost, tracking_number, weight_kg, driver_id, sender_id, recipient_id)
VALUES
('ORD001',NOW(),DATE_ADD(NOW(),INTERVAL 2 DAY),NOW(),'ASSIGNED',120000, 'TRK000001',150,'DRV001','SP001','SP002'),
('ORD002',NOW(),DATE_ADD(NOW(),INTERVAL 1 DAY),NOW(),'IN_TRANSIT',85000,'TRK000002',80,'DRV002','SP003','SP004'),
('ORD003',NOW(),DATE_ADD(NOW(),INTERVAL 3 DAY),NOW(),'PENDING',230000,'TRK000003',450,'DRV003','SP005','SP006'),
('ORD004',NOW(),DATE_ADD(NOW(),INTERVAL 2 DAY),NOW(),'DELIVERED',95000,'TRK000004',65,'DRV004','SP007','SP008'),
('ORD005',NOW(),DATE_ADD(NOW(),INTERVAL 5 DAY),NOW(),'ASSIGNED',180000,'TRK000005',300,'DRV005','SP009','SP010'),
('ORD006',NOW(),DATE_ADD(NOW(),INTERVAL 4 DAY),NOW(),'PENDING',75000,'TRK000006',40,'DRV006','SP001','SP004'),
('ORD007',NOW(),DATE_ADD(NOW(),INTERVAL 2 DAY),NOW(),'IN_TRANSIT',210000,'TRK000007',520,'DRV007','SP003','SP006'),
('ORD008',NOW(),DATE_ADD(NOW(),INTERVAL 1 DAY),NOW(),'ASSIGNED',68000,'TRK000008',25,'DRV008','SP005','SP008'),
('ORD009',NOW(),DATE_ADD(NOW(),INTERVAL 6 DAY),NOW(),'PENDING',305000,'TRK000009',650,'DRV009','SP007','SP010'),
('ORD010',NOW(),DATE_ADD(NOW(),INTERVAL 2 DAY),NOW(),'ASSIGNED',98000,'TRK000010',95,'DRV010','SP009','SP002');

INSERT INTO orders_status_history
(id, observations, shipment_status, update_at, order_id)
VALUES
('HIS001','Pedido asignado','ASSIGNED',NOW(),'ORD001'),
('HIS002','En camino','IN_TRANSIT',NOW(),'ORD002'),
('HIS003','Esperando conductor','PENDING',NOW(),'ORD003'),
('HIS004','Entrega realizada','DELIVERED',NOW(),'ORD004'),
('HIS005','Pedido asignado','ASSIGNED',NOW(),'ORD005'),
('HIS006','Pendiente','PENDING',NOW(),'ORD006'),
('HIS007','Vehículo en ruta','IN_TRANSIT',NOW(),'ORD007'),
('HIS008','Conductor asignado','ASSIGNED',NOW(),'ORD008'),
('HIS009','Esperando despacho','PENDING',NOW(),'ORD009'),
('HIS010','Asignado al conductor','ASSIGNED',NOW(),'ORD010');

INSERT INTO routes
(id, created_at, destination, estimated_time_minutes, origin, order_id)
VALUES
('RUT001',NOW(),'Bogotá',480,'Medellín','ORD001'),
('RUT002',NOW(),'Barranquilla',600,'Cali','ORD002'),
('RUT003',NOW(),'Pereira',180,'Bucaramanga','ORD003'),
('RUT004',NOW(),'Manizales',90,'Cartagena','ORD004'),
('RUT005',NOW(),'Ibagué',300,'Santa Marta','ORD005'),
('RUT006',NOW(),'Bogotá',420,'Medellín','ORD006'),
('RUT007',NOW(),'Pereira',240,'Cali','ORD007'),
('RUT008',NOW(),'Manizales',110,'Bucaramanga','ORD008'),
('RUT009',NOW(),'Ibagué',350,'Cartagena','ORD009'),
('RUT010',NOW(),'Bogotá',450,'Santa Marta','ORD010');

INSERT INTO deliveries
(id, delivered_at, delivery_photo, receiver_name, order_id)
VALUES
('DEL001',NOW(),'delivery1.jpg','Pedro Sánchez','ORD001'),
('DEL002',NOW(),'delivery2.jpg','Jorge Ruiz','ORD002'),
('DEL003',NOW(),'delivery3.jpg','Felipe García','ORD003'),
('DEL004',NOW(),'delivery4.jpg','Óscar Mejía','ORD004'),
('DEL005',NOW(),'delivery5.jpg','Sergio Jiménez','ORD005'),
('DEL006',NOW(),'delivery6.jpg','Jorge Ruiz','ORD006'),
('DEL007',NOW(),'delivery7.jpg','Felipe García','ORD007'),
('DEL008',NOW(),'delivery8.jpg','Óscar Mejía','ORD008'),
('DEL009',NOW(),'delivery9.jpg','Sergio Jiménez','ORD009'),
('DEL010',NOW(),'delivery10.jpg','Pedro Sánchez','ORD010');