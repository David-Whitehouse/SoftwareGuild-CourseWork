drop database if exists SupesDatabase;

create database SupesDatabase;

use SupesDatabase;


create table Locations (
	Id int primary key auto_increment,
    `Name` varchar(100) not null,
    `Description` varchar(500) not null,
    Address varchar(200) not null,
    Latitude decimal(9,6) not null,
    Longitude decimal(9,6) not null);

create table Organizations (
	Id int primary key auto_increment,
	`Name` varchar(100) not null,
    `Description` varchar(500) not null,
    Address varchar(200) not null);
    
create table Powers (
	Id int primary key auto_increment,
    `Name` varchar(100) not null);
    
create table Supes (
	Id int primary key auto_increment,
    `Name` varchar(100) not null,
    `Description` varchar(500) not null,
    PowerId int not null,
    foreign key fk_Supes_PowerId (PowerId)
    references Powers (Id));
    
    create table Sightings (
	Id int primary key auto_increment,
    `Date` Date not null,
    LocationId int not null,
    SupeId int not null,
    foreign key fk_Sightings_LocationId (LocationId)
    references Locations (Id),
    foreign key fk_Sightings_SupeId (SupeId)
    references Supes (Id));
    
create table SupeOrganization (
	SupeId int not null,
    OrgId int not null,
    primary key (SupeId, OrgId),
    foreign key fk_SupeOrganization_SupeId (SupeId)
    references Supes(Id),
    foreign key fk_SupeOrganization_OrgId (OrgId)
    references Organizations(Id));

	
    
    
