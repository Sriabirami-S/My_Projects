# Flight Booking System

## Overview
The **Flight Booking System** is a Java console application that allows users to book flights, select seats, and make payments. It manages flights, seat availability, and passenger bookings with simulated payment processing.  

---

## Features
- **List Flights** – View all available flights with details.  
- **View Seats** – Check seat availability for a specific flight.  
- **Book Seats** – Reserve seats for passengers.  
- **Payment Simulation** – Supports Credit, Debit, and UPI payments with a success probability.  
- **Booking Summary** – Displays a confirmation summary for each successful booking.  

---

## Technologies Used
- **Java** – Core application logic  
- **Java Collections** – `ArrayList` and `UUID` for managing flights, seats, and bookings  
- **Java Time API** – `LocalDateTime` for handling flight timings  
- **Console I/O** – `Scanner` for user input  

---

## Project Structure
```
Java Projects/
└─ Flight Booking System/
└─ flight_booking_system.java
```
---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/Sriabirami-S/My_Projects.git
```
2. Open the project in Eclipse or any Java IDE.

3. Run flight_booking_system.java as a Java Application.

4. Follow the console prompts to:

- List flights

- View seats

- Book a seat

- Make payment

## Usage Example

- List flights – Shows flight ID, origin, destination, departure time, and price.

- View seats – Displays seats with booked seats marked as X.

- Book a seat – Enter flight ID, seat ID, passenger name, and email.

- Payment – Choose payment method (Credit / Debit / UPI). Success is randomly simulated (85% success rate).

## Future Enhancements

- Implement GUI using Java Swing or JavaFX.

- Persist data using a database like MySQL.

- Add user authentication (Admin / Passenger).

- Send email confirmations for bookings.
