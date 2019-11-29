drop database if exists GuessTheNumberDb;

create database GuessTheNumberDb;

use GuessTheNumberDb;

create table Games (
	GameId int primary key auto_increment,
    TargetNum int not null,
    IsGameOver boolean not null);

create table Guesses (
	GuessId int primary key auto_increment,
    GuessNum int not null,
    PartialMatchCount int not null,
    ExactMatchCount int not null,
    GameId int not null,
    `TimeStamp` datetime not null,
    foreign key fk_Guesses_GameId (GameId)
    references Games(GameId));
    
