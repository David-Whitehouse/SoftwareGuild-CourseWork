use hoteldatabase;

-- Query #1
select GuestFirstName, GuestLastName, RoomNumber, StartDate, EndDate
from reservations
inner join roomreservation on reservations.ReservationNumber = roomreservation.ReservationNumber
inner join guests on reservations.GuestId = guests.GuestId
where EndDate <= '2023-07-31';

-- Query #2
select GuestFirstName, GuestLastName, Rooms.RoomNumber, StartDate, EndDate
from reservations
inner join roomreservation on reservations.ReservationNumber = roomreservation.ReservationNumber
inner join rooms on roomreservation.RoomNumber = rooms.RoomNumber
inner join roomamenities on rooms.RoomNumber = roomamenities.roomNumber
inner join guests on reservations.GuestId = guests.GuestId
where roomamenities.AmenityId = 3;

-- Query #3
select Rooms.RoomNumber, GuestFirstName, GuestLastName, StartDate, NumberOfAdults+NumberOfChildren totalpeople
from  reservations
inner join roomreservation on reservations.ReservationNumber = roomreservation.ReservationNumber
inner join rooms on roomreservation.RoomNumber = rooms.RoomNumber
inner join guests on reservations.GuestId = guests.GuestId
where GuestLastName = "Cullison";

-- Query #4 Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.
select Rooms.RoomNumber, roomreservation.ReservationNumber,  (sum(AmenityCost) + basePrice +
if(NumberOfAdults>StandardOccupancy, (NumberOfAdults-StandardOccupancy)*ExtraPersonCost, 0))*DateDiff(EndDate, StartDate) totalCost
from Rooms
left join roomreservation on Rooms.RoomNumber = roomreservation.RoomNumber
inner join roomamenities on rooms.RoomNumber = roomamenities.RoomNumber
inner join amenities on roomamenities.AmenityId = amenities.AmenityId
inner join roomtype on rooms.TypeName = roomtype.TypeName
left outer join reservations on roomreservation.ReservationNumber = reservations.ReservationNumber 
group by roomreservation.roomNumber, roomreservation.ReservationNumber;


-- Query #5
select *, (numberOfAdults + NumberOfChildren) totalGuests
from Rooms
inner join RoomReservation on Rooms.RoomNumber = RoomReservation.RoomNumber
inner join Reservations on RoomReservation.ReservationNumber = Reservations.ReservationNumber
where (numberOfAdults + NumberOfChildren) >= 3 AND 
((StartDate Between '2023-04-01' AND '2023-04-30') OR 
(EndDate Between '2023-04-01' AND '2023-04-30'));

-- Query #6
Select GuestFirstName, GuestLastName, Count(reservations.GuestId) NumberOfReservations
from Guests
inner join reservations on guests.GuestId = reservations.GuestId
group by GuestLastName
order by NumberOfReservations DESC, GuestLastName;

-- Query #7
Select GuestFirstName, GuestLastName, Address, City, State, Zip, Phone
from Guests
Where Phone = '2318932755'; 












 