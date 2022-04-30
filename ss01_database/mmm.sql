CREATE DATABASE furama;
USE furama;
CREATE TABLE `position`(
position_id INT PRIMARY KEY AUTO_INCREMENT,
position_name VARCHAR(45)
);
CREATE TABLE education_degree (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45)
);
CREATE TABLE division (
division_id INT PRIMARY KEY AUTO_INCREMENT,
division_name VARCHAR(45)
);
CREATE TABLE `role`(
role_id INT PRIMARY KEY AUTO_INCREMENT,
role_name VARCHAR(255)
);
CREATE TABLE `user` (
username VARCHAR(255) PRIMARY KEY ,
`password` VARCHAR(255)
);
CREATE TABLE user_role (
role_id INT,
username VARCHAR(255),
FOREIGN KEY (role_id) REFERENCES `role`(role_id),
FOREIGN KEY (username) REFERENCES `user`(username)
);
CREATE TABLE employee (
employee_id INT PRIMARY KEY AUTO_INCREMENT,
employee_name VARCHAR(45),
birthday DATE,
id_card VARCHAR(45),
salary DOUBLE,
phone VARCHAR(45),
email VARCHAR(45),
address VARCHAR(45),
position_id INT,
id INT,
division_id INT,
username VARCHAR(255),
FOREIGN KEY (position_id) REFERENCES `position`(position_id),
FOREIGN KEY (id) REFERENCES education_degree(id),
FOREIGN KEY (division_id) REFERENCES division(division_id),
FOREIGN KEY (username) REFERENCES `user`(username)
);
CREATE TABLE customer_type (
customer_type_id INT PRIMARY KEY AUTO_INCREMENT,
customer_type_name VARCHAR(45)
);
create table customer(
customer_id int primary key auto_increment,
customer_type_id int,
customer_name varchar(45) not null,
customer_birthday date not null,
customer_gender bit(1)not null,
customer_id_card varchar(45) not null,
customer_phone varchar(45),
customer_email varchar(45),
customer_address varchar(45),
foreign key (customer_type_id) references customer_type (customer_type_id)
);
CREATE TABLE rent_type (
rent_type_id INT PRIMARY KEY AUTO_INCREMENT,
rent_type_name VARCHAR(45)
);
CREATE TABLE service_type (
service_type_id INT PRIMARY KEY AUTO_INCREMENT,
service_type_name VARCHAR(45)
);
CREATE TABLE service (
service_id INT PRIMARY KEY AUTO_INCREMENT,
service_name VARCHAR(45),
service_area INT,
service_cost DOUBLE,
max_people INT,
rent_type_id INT,
service_type_id INT,
standard_room VARCHAR(45),
description_other_convenience VARCHAR(45),
pool_area DOUBLE,
number_of_floors INT,
FOREIGN KEY (rent_type_id) REFERENCES rent_type(rent_type_id),
FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id)
);
CREATE TABLE contract (
contract_id INT PRIMARY KEY AUTO_INCREMENT,
contract_start_date DATETIME,
contract_end_date DATETIME,
contract_deposit DOUBLE,
contract_total_money DOUBLE,
employee_id INT,
customer_id INT,
service_id INT,
FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
FOREIGN KEY (service_id) REFERENCES service(service_id)
);
CREATE TABLE attach_service (
attach_service_id INT PRIMARY KEY AUTO_INCREMENT,
attach_service_name VARCHAR(45),
attach_service_cost DOUBLE,
attach_service_unit INT,
attach_service_status VARCHAR(45)
);
CREATE TABLE contract_detail (
contract_detail_id INT PRIMARY KEY AUTO_INCREMENT,
contract_id INT,
attach_service_id INT,
quantity INT,
FOREIGN KEY (contract_id) REFERENCES contract(contract_id),
FOREIGN KEY (attach_service_id) REFERENCES attach_service(attach_service_id)
);
INSERT INTO `position` (position_id, position_name)
VALUES(1, 'Quản Lý'),
	 (2, 'Nhân Viên');
     
INSERT INTO education_degree (id, `name`)
VALUES(1, 'Trung Cấp'),
     (2, 'Cao Đẳng'),
     (3, 'Đại Học'),
     (4, 'Sau Đại Học');
     
INSERT INTO division (division_id, division_name)
VALUES(1, 'Sale-Marketing'),
	 (2, 'Hành chính'),
     (3, 'Phục vụ'),
     (4, 'Quản lý');
     
INSERT INTO employee
VALUES(1, 'Nguyễn Văn An', '1970-11-07','456231786', 10000000, '0901234121', 'annguyen@gmail.com', '295 Nguyễn Tất Thành, Đà Nẵng',	1, 1, 1, 'An'),
     (2, 'Lê Văn Bình', '1997-04-09', '654231234', 7000000, '0934212314', 'binhlv@gmail.com', '22 Yên Bái, Đà Nẵng', 1, 2, 2, 'Bình'),
     (3, 'Hồ Thị Yến', '1995-12-12', '999231723', 14000000, '0412352315', 'thiyen@gmail.com', 'K234/11 Điện Biên Phủ, Gia Lai', 1, 3, 2, 'Yến'),
     (4, 'Võ Công Toản', '1980-04-04', '123231365', 17000000, '0374443232', 'toan0404@gmail.com', '77 Hoàng Diệu, Quảng Trị', 1, 4, 4, 'Toản'),
     (5, 'Nguyễn Bỉnh Phát', '1999-12-09', '454363232', 6000000, '0902341231', 'phatphat@gmail.com', '43 Yên Bái, Đà Nẵng', 2, 1, 1, 'Phát'),
     (6, 'Khúc Nguyễn An Nghi', '2000-11-08', '964542311', 7000000, '0978653213', 'annghi20@gmail.com', '294 Nguyễn Tất Thành, Đà Nẵng', 2, 2, 3, 'Nghi'),
     (7, 'Nguyễn Hữu Hà', '1993-01-01', '534323231', 8000000, '0941234553', 'nhh0101@gmail.com', '4 Nguyễn Chí Thanh, Huế', 2, 3, 2, 'Hà'),
     (8, 'Nguyễn Hà Đông', '1989-09-03', '234414123', 9000000, '0642123111', 'donghanguyen@gmail.com', '111 Hùng Vương, Hà Nội', 2, 4, 4, 'Đông'),
     (9, 'Tòng Hoang', '1982-09-03', '256781231', 6000000, '0245144444', 'hoangtong@gmail.com', '213 Hàm Nghi, Đà Nẵng', 2, 4, 4, 'Hưng'),
     (10, 'Nguyễn Công Đạo', '1994-01-08', '755434343', 8000000, '0988767111', 'nguyencongdao12@gmail.com', '6 Hoà Khánh, Đồng Nai', 2, 3, 2, 'Đạo');
 
INSERT INTO customer_type (customer_type_id, customer_type_name)
VALUES(1, 'Diamond'),
     (2, 'Platinium'),
     (3, 'Gold'),
     (4, 'Silver'),
     (5, 'Member');
     
Insert INTO customer(customer_id, customer_name, customer_birthday, customer_gender, customer_id_card, customer_phone, customer_email, customer_address, customer_type_id) values
(1,	'Nguyen Thi Hao', '1970-11-07', 0, 643431213,	0945423362,	'thihao07@gmail.com', '23 Nguyen Hoang, Da Nang', 5),
(2,	'Pham Xuan Dieu', '1992-08-08', 1, 865342123,	0954333333,	'xuandieu92@gmail.com', 'K77/22 Thai Phien, Quang Tri',	3),
(3,	'Truong Dinh nghe',	'1990-02-27',	1, 488645199, 0373213122, 'nghenhan2702@gmail.com', 'K323/12 Ong Ich Khiem, Vinh', 1),
(4,	'Duong Van Quang', '1981-07-08', 1, 543432111,	0490039241,	'duongquan@gmail.com', 'K453/12 Le Loi, Da Nang', 1),
(5,	'Hoang Tran Nhi Nhi', '1995-12-09', 0, 795453345,	0312345678,	'nhinhi123@gmail.com', '224 Ly Thai To, Gia Lai', 4),
(6,	'Ton Nu Moc Chau', '2005-12-06', 0, 732434215, 0988888844, 'tonnuchau@gmail.com',	'37 Yen The, Da Nang', 4),
(7,	'Nguyen My Kim', '1984-04-08', 0,	856453123,	0912345698,	'kimcuong84@gmail.com',	'K123/45 Le Loi, Ho Chi Minh', 1),
(8,	'Nguyen Thi Hao', '1999-04-08', 0, 965656433,	0763212345,	'haohao99@gmail.com', '55 Nguyen Van Linh, Kon Tum', 3),
(9,	'Tran Dai Danh', '1994-07-01', 1,	432341235, 0643343433, 'danhhai99@gmail.com', '24 Ly Thuong Kiet, Quang Ngai', 1),
(10, 'Nguyen Tam Dac', '1989-07-01', 1, 344343432, 0987654321, 'dactam@gmail.com', '22 Ngo Quyen, Da Nang', 2);
INSERT INTO rent_type (rent_type_id, rent_type_name )
VALUES(1, 'year'),
     (2, 'month'),
     (3, 'day'),
     (4, 'hour');
     
INSERT INTO service_type (service_type_id, service_type_name)
VALUES(1, 'Villa'),
     (2, 'House'),
     (3, 'Room');
     
INSERT INTO service
VALUES(1, 'Villa Beach Front', 25000, 10000000, 10, 3, 1, 'vip', 'Có hồ bơi', 500, 4),
     (2, 'House Princess 01', 14000, 5000000, 7, 2, 2, 'vip', 'Có thêm bếp nướng', null, 3),
     (3, 'Room Twin 01', 5000, 1000000, 2, 4, 3, 'normal', 'Có tivi', null, null),
     (4, 'Villa No Beach Front', 22000, 9000000, 8, 3, 1, 'normal', 'Có hồ bơi', 300, 3),
     (5, 'House Princess 02', 10000, 4000000, 5, 3, 2, 'normal', 'Có thêm bếp nướng', null, 2),
     (6, 'Room Twin 02', 3000, 900000, 2, 4, 3, 'normal', 'Có tivi', null, null);

INSERT INTO attach_service
VALUES(1, 'Karaoke', 10000, 'giờ', 'tiện nghi, hiện tại'),
     (2, 'Thuê xe máy', 10000, 'chiếc', 'hỏng 1 xe'),
     (3, 'Thuê xe đạp', 20000, 'chiếc', 'tốt'),
     (4, 'Buffet buổi sáng', 15000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
     (5, 'Buffet buổi trưa', 90000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
     (6, 'Buffet buổi tối', 16000, 'suất', 'đầy đủ đồ ăn, tráng miệng');
     
INSERT INTO contract
VALUES(1, '2020-12-08', '2020-12-08', 0, 3, 1, 3),
     (2, '2020-07-14', '2020-07-21', 200000, 7, 3, 1),
     (3, '2021-03-15', '2021-03-17', 50000, 3, 4, 2),
     (4, '2021-01-14', '2021-01-18', 100000, 7, 5, 5),
     (5, '2021-07-14', '2021-07-15', 0, 7, 2, 6),
     (6, '2021-06-01', '2021-06-03', 0, 7, 7, 6),
     (7, '2021-09-02', '2021-09-05', 100000, 7, 4, 4),
     (8, '2021-06-17', '2021-06-18', 150000, 3, 4, 1),
     (9, '2020-11-19', '2020-11-19', 0, 3, 4, 3),
     (10, '2021-04-12', '2021-04-14', 0, 10, 3, 5),
     (11, '2021-04-25', '2021-04-25', 0, 2, 2, 1),
     (12, '2021-05-25', '2021-05-27', 0, 7, 10, 1);
     
INSERT INTO contract_detail
VALUES(1,  2, 4, 5),
     (2, 2, 5, 8),
     (3, 2, 6, 15),
     (4, 3, 1, 1),
     (5, 3, 2, 11),
     (6, 1, 3, 1),
     (7, 1, 2, 2),
     (8, 12, 2, 2);
