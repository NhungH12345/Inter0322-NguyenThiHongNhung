CREATE DATABASE case_study;
USE case_study;
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
type_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45)
);
create table customer(
id int primary key auto_increment,
type_id int,
`name`varchar(45) not null,
birthday date not null,
gender bit(1)not null,
id_card varchar(45) not null,
phone varchar(45),
email varchar(45),
address varchar(45),
foreign key (type_id) references customer_type (type_id)
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
VALUES(1, 'Qu???n L??'),
	 (2, 'Nh??n Vi??n');
     
INSERT INTO education_degree (id, `name`)
VALUES(1, 'Trung C???p'),
     (2, 'Cao ?????ng'),
     (3, '?????i H???c'),
     (4, 'Sau ?????i H???c');
     
INSERT INTO division (division_id, division_name)
VALUES(1, 'Sale-Marketing'),
	 (2, 'H??nh ch??nh'),
     (3, 'Ph???c v???'),
     (4, 'Qu???n l??');
     
INSERT INTO employee
VALUES(1, 'Nguy???n V??n An', '1970-11-07','456231786', 10000000, '0901234121', 'annguyen@gmail.com', '295 Nguy???n T???t Th??nh, ???? N???ng',	1, 1, 1, 'An'),
     (2, 'L?? V??n B??nh', '1997-04-09', '654231234', 7000000, '0934212314', 'binhlv@gmail.com', '22 Y??n B??i, ???? N???ng', 1, 2, 2, 'B??nh'),
     (3, 'H??? Th??? Y???n', '1995-12-12', '999231723', 14000000, '0412352315', 'thiyen@gmail.com', 'K234/11 ??i???n Bi??n Ph???, Gia Lai', 1, 3, 2, 'Y???n'),
     (4, 'V?? C??ng To???n', '1980-04-04', '123231365', 17000000, '0374443232', 'toan0404@gmail.com', '77 Ho??ng Di???u, Qu???ng Tr???', 1, 4, 4, 'To???n'),
     (5, 'Nguy???n B???nh Ph??t', '1999-12-09', '454363232', 6000000, '0902341231', 'phatphat@gmail.com', '43 Y??n B??i, ???? N???ng', 2, 1, 1, 'Ph??t'),
     (6, 'Kh??c Nguy???n An Nghi', '2000-11-08', '964542311', 7000000, '0978653213', 'annghi20@gmail.com', '294 Nguy???n T???t Th??nh, ???? N???ng', 2, 2, 3, 'Nghi'),
     (7, 'Nguy???n H???u H??', '1993-01-01', '534323231', 8000000, '0941234553', 'nhh0101@gmail.com', '4 Nguy???n Ch?? Thanh, Hu???', 2, 3, 2, 'H??'),
     (8, 'Nguy???n H?? ????ng', '1989-09-03', '234414123', 9000000, '0642123111', 'donghanguyen@gmail.com', '111 H??ng V????ng, H?? N???i', 2, 4, 4, '????ng'),
     (9, 'T??ng Hoang', '1982-09-03', '256781231', 6000000, '0245144444', 'hoangtong@gmail.com', '213 H??m Nghi, ???? N???ng', 2, 4, 4, 'H??ng'),
     (10, 'Nguy???n C??ng ?????o', '1994-01-08', '755434343', 8000000, '0988767111', 'nguyencongdao12@gmail.com', '6 Ho?? Kh??nh, ?????ng Nai', 2, 3, 2, '?????o');
 
INSERT INTO customer_type (customer_type_id, customer_type_name)
VALUES(1, 'Diamond'),
     (2, 'Platinium'),
     (3, 'Gold'),
     (4, 'Silver'),
     (5, 'Member');
     
INSERT INTO customer
VALUES(1, 5, 'Nguyen Thi Hao', '1970-11-07', 'Nu', 'thihao07@gmail.com', '0945423362', '23 Nguy???n Ho??ng, ???? N???ng'),
     (2, 3, 'Pham Xuan Dieu', '1992-08-08', 'Nam', 'xuandieu92@gmail.com', '0954333333', 'K77/22 Th??i Phi??n, Qu???ng Tr???'),
     (3, 1, 'Truong ??inh Nghe', 'customer1990-02-27','Nu', 'nghenhan2702@gmail.com', '0373213122', 'K323/12 ??ng ??ch Khi??m, Vinh'),
     (4, 1, 'Duong Van Quan', '1981-07-08', 'Nam', 'duongquan@gmail.com', '0490039241', 'K453/12 L?? L???i, ???? N???ng'),
     (5, 4, 'Hoang Tran Nhi Nhi', '1995-12-09', 'Nu', 'nhinhi123@gmail.com',  '0312345678', '224 L?? Th??i T???, Gia Lai'),
     (6, 4, 'Ton Nu Moc Chau', '2005-12-06', 'Nu', 'tonnuchau@gmail.com', '0988888844', '37 Y??n Th???, ???? N???ng'),
     (7, 1, 'Nguyen My Kim', '1984-04-08', 'Nam', 'kimcuong84@gmail.com',  '0912345698', 'K123/45 L?? L???i, H??? Ch?? Minh'),
     (8, 3, 'Nguyen Thi Hao', '1999-04-08', 'Nam', 'haohao99@gmail.com', '0763212345', '55 Nguy???n V??n Linh, Kon Tum'),
     (9, 1, 'Tran Dai Danh', '1994-07-01', 'Nu', 'danhhai99@gmail.com',  '0643343433', '24 L?? Th?????ng Ki???t, Qu???ng Ng??i'),
     (10, 2, 'Nguyen Tam Dac', '1989-07-01', 'Nu', 'actam@gmail.com', '0987654321', '0987654321', '22 Ng?? Quy???n, ???? N???ng');
     
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
VALUES(1, 'Villa Beach Front', 25000, 10000000, 10, 3, 1, 'vip', 'C?? h??? b??i', 500, 4),
     (2, 'House Princess 01', 14000, 5000000, 7, 2, 2, 'vip', 'C?? th??m b???p n?????ng', null, 3),
     (3, 'Room Twin 01', 5000, 1000000, 2, 4, 3, 'normal', 'C?? tivi', null, null),
     (4, 'Villa No Beach Front', 22000, 9000000, 8, 3, 1, 'normal', 'C?? h??? b??i', 300, 3),
     (5, 'House Princess 02', 10000, 4000000, 5, 3, 2, 'normal', 'C?? th??m b???p n?????ng', null, 2),
     (6, 'Room Twin 02', 3000, 900000, 2, 4, 3, 'normal', 'C?? tivi', null, null);

INSERT INTO attach_service
VALUES(1, 'Karaoke', 10000, 'gi???', 'ti???n nghi, hi???n t???i'),
     (2, 'Thu?? xe m??y', 10000, 'chi???c', 'h???ng 1 xe'),
     (3, 'Thu?? xe ?????p', 20000, 'chi???c', 't???t'),
     (4, 'Buffet bu???i s??ng', 15000, 'su???t', '?????y ????? ????? ??n, tr??ng mi???ng'),
     (5, 'Buffet bu???i tr??a', 90000, 'su???t', '?????y ????? ????? ??n, tr??ng mi???ng'),
     (6, 'Buffet bu???i t???i', 16000, 'su???t', '?????y ????? ????? ??n, tr??ng mi???ng');
     
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
