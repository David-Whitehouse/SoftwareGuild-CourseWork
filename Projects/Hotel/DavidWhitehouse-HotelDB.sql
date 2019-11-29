drop database if exists HotelDatabase;

create database HotelDatabase;

use HotelDatabase;

create table RoomType (
	TypeName varchar(25) primary key,
    StandardOccupancy int not null,
    MaximumOccupancy int not null,
    BasePrice Decimal(6,2) not null,
    ExtraPersonCost Decimal(4,2) not null);
    
create table Guests (
	GuestId int primary key auto_increment,
    GuestFirstName varchar(30) not null,
    GuestLastName varchar(30) not null,
    Address varchar(50) null,
    City varchar(30) null,
    State char(2) null,
    Zip int(5) null,
    Phone bigint not null);
    
create table Amenities(
	AmenityId int primary key auto_increment,
    AmenityName varchar(30),
    AmenityCost Decimal(4,2));
        
create table Rooms(
	RoomNumber int primary key,
    TypeName varchar(25),
    AdaAccessible bit(2),
    foreign key fk_Rooms_TypeName (TypeName)
    references RoomType(TypeName));
    
    create table RoomAmenities(
	RoomNumber int not null,
    AmenityId int not null,
    
    foreign key fk_RoomAmenities_Rooms (RoomNumber)
    references Rooms (RoomNumber),
    foreign key fk_RoomAmenities_Amenities (AmenityId)
    references Amenities(AmenityId));
    
create table Reservations(
	ReservationNumber int primary key auto_increment,
    GuestId int not null,

    StartDate date not null,
    EndDate date not null,
    foreign key fk_Reservations_GuestId (GuestId)
    references Guests (GuestId));
    
create table RoomReservation(
	ReservationNumber int not null,
    RoomNumber int not null,
    NumberOfAdults int not null,
    NumberOfChildren int not null,
    primary key (ReservationNumber, RoomNumber),
    foreign key fk_RoomReservation_Reservations (ReservationNumber)
    references Reservations (ReservationNumber),
    foreign key fk_RoomReservation_rooms (RoomNumber)
    references Rooms (RoomNumber));
    
    
    
    
    
    
    
    
    
