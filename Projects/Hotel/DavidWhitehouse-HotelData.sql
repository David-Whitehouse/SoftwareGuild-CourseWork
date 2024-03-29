use Hoteldatabase;

insert into RoomType (TypeName, StandardOccupancy, MaximumOccupancy, BasePrice, ExtraPersonCost) values
	('Single', '2', '2', '149.99', '0.00'),
    ('Double', '2', '4', '174.99', '10.00'),
    ('Suite', '3', '8', '399.99', '20.00');
    
insert into Guests (GuestFirstName, GuestLastName, Address, City, State, Zip, Phone) values
	('David', 'Whitehouse', '', '', '', '55345', '6125810162'), 
    ('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '2915530508'),
    ('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '4782779632'),
    ('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '3084940198'),
    ('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '2147300298'),
    ('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '3775070974'),
    ('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '8144852615'),
    ('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '2794910960'),
    ('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '4463966785'),
    ('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '8347271001'),
    ('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '4463516860'),
    ('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '2318932755');
    
insert into Amenities (AmenityName, AmenityCost) values
	('Microwave', '0'),
    ('Refrigerator', '0'),
    ('Jacuzzi', '25'),
    ('Oven', '0');
    
insert into Rooms (RoomNumber, TypeName, AdaAccessible) values
	('201', 'Double', 0),
    ('202', 'Double', 1),
    ('203', 'Double', 0),
    ('204', 'Double', 1),
    ('205', 'Single', 0),
    ('206', 'Single', 1),
    ('207', 'Single', 0),
    ('208', 'Single', 1),
    ('301', 'Double', 0),
    ('302', 'Double', 1),
    ('303', 'Double', 0),
    ('304', 'Double', 1),
    ('305', 'Single', 0),
    ('306', 'Single', 1),
    ('307', 'Single', 0),
    ('308', 'Single', 1),
    ('401', 'Suite', 1),
    ('402', 'Suite', 1);
    
insert into roomamenities ( RoomNumber, AmenityId) values
	('201', '1'),
    ('201', '3'),
    ('202', '2'),
	('203', '1'),
    ('203', '3'),
    ('204', '2'),
    ('205', '1'),
    ('205', '2'),
    ('205', '3'),
    ('206', '1'),
    ('206', '2'),
	('207', '1'),
    ('207', '2'),
    ('207', '3'),
	('208', '1'),
    ('208', '2'),
    ('301', '1'),
    ('301', '3'),
    ('302', '2'),
    ('303', '1'),
    ('303', '3'),
    ('304', '2'),
	('305', '1'),
    ('305', '2'),
    ('305', '3'),
    ('306', '1'),
    ('306', '2'),
    ('307', '1'),
    ('307', '2'),
    ('307', '3'),
    ('308', '1'),
    ('308', '2'),
    ('401', '1'),
    ('401', '2'),
    ('401', '4'),
    ('402', '1'),
    ('402', '2'),
    ('402', '4');

    
insert into Reservations (GuestId, StartDate, EndDate) values
	('2', '2023-02-02', '2023-02-04'),
    ('3', '2023-02-05', '2023-02-10'),
	('4', '2023-02-22', '2023-02-24'),
    ('5', '2023-03-06', '2023-03-07'),
    ('1', '2023-03-17', '2023-03-20'),
    ('6', '2023-03-18', '2023-03-23'),
    ('7', '2023-03-29', '2023-03-31'),
    ('8', '2023-03-31', '2023-04-05'),
    ('9', '2023-04-09', '2023-04-13'),
    ('10', '2023-04-23', '2023-04-24'),
    ('11', '2023-05-30', '2023-06-02'),
    ('12', '2023-06-10', '2023-06-14'),
    ('6', '2023-06-17', '2023-06-18'),
    ('1', '2023-06-28', '2023-07-02'),
    ('9', '2023-07-13', '2023-07-14'),
    ('10', '2023-07-18', '2023-07-21'),
    ('3', '2023-07-28', '2023-07-29'),
    ('3', '2023-08-30', '2023-09-01'),
    ('2', '2023-09-16', '2023-09-17'),
    ('5', '2023-09-13', '2023-09-15'),
    ('4', '2023-11-22', '2023-11-25'),
    ('2', '2023-11-22', '2023-11-25'),
    ('11', '2023-12-24', '2023-12-28');
    
insert into roomreservation (ReservationNumber, NumberOfAdults, NumberOfChildren, RoomNumber) values
	('1', '1', '0', '308'),
    ('2','2', '1', '203'),
    ('3','1', '0', '305'),
    ('4', '2', '2', '201'),
    ('5', '1', '1', '307'),
    ('6', '3', '0', '302'),
    ('7', '2', '2', '202'),
    ('8', '2', '0', '304'),
    ('9', '1', '0', '301'),
    ('10', '1', '1', '207'),
    ('11', '2', '4', '401'),
    ('12', '2', '0', '206'),
    ('12', '1', '0', '208'),
    ('13', '3', '0', '304'),
    ('14', '2', '0', '205'),
    ('15', '3', '1', '204'),
    ('16', '4', '2', '401'),
    ('17', '2', '1', '303'),
    ('18', '1', '0', '305'),
    ('19', '2', '0', '208'),
    ('20', '2', '2', '203'),
    ('21', '2', '2', '401'),
    ('22', '2', '0', '206'),
    ('22', '2', '2', '301'),
	('23', '2', '0', '302');
    
    
    
