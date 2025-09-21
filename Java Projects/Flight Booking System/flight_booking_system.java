package pinnacle_labs;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class flight_booking_system {

    static class Seat {
        String seatId;
        boolean booked;

        Seat(String seatId) {
            this.seatId = seatId;
            this.booked = false;
        }

        public String toString() {
            return seatId + (booked ? " (X)" : " (Available)");
        }
    }

    static class Flight {
        String flightId;
        String origin;
        String destination;
        LocalDateTime departure;
        double price;
        ArrayList<Seat> seats;

        Flight(String flightId, String origin, String destination, LocalDateTime departure, double price, ArrayList<Seat> seats) {
            this.flightId = flightId;
            this.origin = origin;
            this.destination = destination;
            this.departure = departure;
            this.price = price;
            this.seats = seats;
        }

        ArrayList<Seat> getAvailableSeats() {
            ArrayList<Seat> avail = new ArrayList<>();
            for (Seat s : seats) {
                if (!s.booked) avail.add(s);
            }
            return avail;
        }

        Seat findSeatById(String id) {
            for (Seat s : seats) {
                if (s.seatId.equalsIgnoreCase(id)) return s; 
            }
            return null;
        }

        
        public String toString() {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return String.format("%s: %s -> %s at %s | Price: ₹%.2f",
                    flightId, origin, destination, departure.format(fmt), price);
        }
    }

    static class Passenger {
        String name;
        String email;

        Passenger(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    static class Booking {
        String bookingId;
        Flight flight;
        Seat seat;
        Passenger passenger;
        boolean paid;

        Booking(Flight flight, Seat seat, Passenger passenger) {
            this.bookingId = UUID.randomUUID().toString().substring(0, 8);
            this.flight = flight;
            this.seat = seat;
            this.passenger = passenger;
            this.paid = false;
        }

        
        public String toString() {
            return String.format("Booking %s | Flight %s | Seat %s | Passenger %s | Paid: %s",
                    bookingId, flight.flightId, seat.seatId, passenger.name, paid ? "Yes" : "No");
        }
    }

    static class PaymentProcessor {
        private Random rnd = new Random();

        public boolean processPayment(double amount, String method) {
            method = method.toLowerCase();
            ArrayList<String> validMethods = new ArrayList<>(Arrays.asList("credit", "debit", "upi"));
            if (!validMethods.contains(method)) return false;
            return rnd.nextDouble() < 0.85; 
        }
    }

    static class FlightService {
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        void addFlight(Flight f) {
            flights.add(f);
        }

        ArrayList<Flight> listFlights() {
            return flights;
        }

        Flight getFlight(String id) {
            for (Flight f : flights) {
                if (f.flightId.equalsIgnoreCase(id)) return f; 
            }
            return null;
        }

        Booking createBooking(String flightId, String seatId, Passenger p) {
            Flight f = getFlight(flightId);
            if (f == null) return null;
            Seat s = f.findSeatById(seatId);
            if (s == null || s.booked) return null;
            s.booked = true;
            Booking b = new Booking(f, s, p);
            bookings.add(b);
            return b;
        }

        boolean makePayment(String bookingId, double amount, String method) {
            for (Booking b : bookings) {
                if (b.bookingId.equalsIgnoreCase(bookingId)) {
                    boolean ok = paymentProcessor.processPayment(amount, method);
                    if (ok) {
                        b.paid = true;
                        return true;
                    } else {
                        b.seat.booked = false; 
                        bookings.remove(b);
                        return false;
                    }
                }
            }
            return false;
        }
    }

    static ArrayList<Seat> buildSeats(int rows, int seatsPerRow) {
        ArrayList<Seat> list = new ArrayList<>();
        for (int r = 1; r <= rows; r++) {
            for (int c = 0; c < seatsPerRow; c++) {
                char col = (char) ('A' + c);
                list.add(new Seat(r + "" + col));
            }
        }
        return list;
    }

    static void printSeats(ArrayList<Seat> seats, int seatsPerRow) {
        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).toString() + " ");
            if ((i + 1) % seatsPerRow == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    static void printAvailableSeats(ArrayList<Seat> seats, int seatsPerRow) {
        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).seatId + " ");
            if ((i + 1) % seatsPerRow == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FlightService service = new FlightService();

        service.addFlight(new Flight("FL100", "Chennai", "Bengaluru",
                LocalDateTime.now().plusDays(1).withHour(9).withMinute(30),
                3500, buildSeats(5, 4)));
        service.addFlight(new Flight("FL200", "Bengaluru", "Mumbai",
                LocalDateTime.now().plusDays(2).withHour(15).withMinute(0),
                5200, buildSeats(6, 6)));
        service.addFlight(new Flight("FL300", "Delhi", "Kolkata",
                LocalDateTime.now().plusDays(3).withHour(11).withMinute(45),
                6000, buildSeats(8, 6)));
        service.addFlight(new Flight("FL400", "Hyderabad", "Chennai",
                LocalDateTime.now().plusDays(4).withHour(7).withMinute(15),
                4200, buildSeats(4, 4)));
        service.addFlight(new Flight("FL500", "Pune", "Delhi",
                LocalDateTime.now().plusDays(5).withHour(19).withMinute(0),
                7000, buildSeats(7, 6)));

        System.out.println("===== Welcome to Flight Booking System =====");

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1) List flights");
            System.out.println("2) View seats for a flight");
            System.out.println("3) Book a seat");
            System.out.println("4) Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\nAvailable flights:");
                    for (Flight f : service.listFlights()) {
                        System.out.println(f.toString());
                    }
                    break;

                case "2":
                    System.out.print("Enter flight id: ");
                    String fid = sc.nextLine().trim();
                    Flight flight = service.getFlight(fid);
                    if (flight == null) {
                        System.out.println("Flight not found.");
                    } else {
                        System.out.println("Seats (X = booked):");
                        int seatsPerRow = 6; 
                        printSeats(flight.seats, seatsPerRow);
                    }
                    break;

                case "3":
                    System.out.print("Enter flight id: ");
                    fid = sc.nextLine().trim();
                    flight = service.getFlight(fid);
                    if (flight == null) {
                        System.out.println("Flight not found.");
                        break;
                    }
                    System.out.println("Available seats:");
                    printAvailableSeats(flight.getAvailableSeats(), 6);
                    System.out.print("Enter seat id to book: ");
                    String seatId = sc.nextLine().trim();
                    System.out.print("Enter passenger name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter passenger email: ");
                    String email = sc.nextLine().trim();

                    Passenger p = new Passenger(name, email);
                    Booking booking = service.createBooking(fid, seatId, p);
                    if (booking == null) {
                        System.out.println("Could not reserve seat. It may be already booked or invalid.");
                        break;
                    }

                    System.out.println("\n===== Booking Summary =====");
                    System.out.println(booking.flight.toString());
                    System.out.println("Passenger: " + booking.passenger.name);
                    System.out.println("Seat: " + booking.seat.seatId);
                    System.out.print("\nEnter Payment Method (Credit / Debit / UPI): ");
                    String method = sc.nextLine().trim();

                    boolean paid = service.makePayment(booking.bookingId, booking.flight.price, method);
                    if (paid) {
                        System.out.println("Payment successful! Booking confirmed.");
                        System.out.println(booking.toString());
                    } else {
                        System.out.println("Payment failed. Seat has been released. Please try booking again.");
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
