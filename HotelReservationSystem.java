import java.util.ArrayList;
import java.util.Scanner;

class Room {
  int roomNumber;
  String category;
  boolean isAvailable;
  double price;

  public Room(int roomNumber, String category, boolean isAvailable, double price) {
    this.roomNumber = roomNumber;
    this.category = category;
    this.isAvailable = isAvailable;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Room Number: " + roomNumber + ", Category: " + category + ", Price: $" + price + ", Available: "
        + (isAvailable ? "Yes" : "No");
  }
}

class Reservation {
  String guestName;
  Room room;
  int nights;
  double totalAmount;

  public Reservation(String guestName, Room room, int nights) {
    this.guestName = guestName;
    this.room = room;
    this.nights = nights;
    this.totalAmount = room.price * nights;
  }

  @Override
  public String toString() {
    return "Reservation for " + guestName + "\nRoom Details: " + room + "\nNights: " + nights + "\nTotal Amount: $"
        + totalAmount;
  }
}

public class HotelReservationSystem {

  private ArrayList<Room> rooms = new ArrayList<>();
  private ArrayList<Reservation> reservations = new ArrayList<>();

  public HotelReservationSystem() {
    // Initialize with some rooms
    rooms.add(new Room(101, "Single", true, 100));
    rooms.add(new Room(102, "Double", true, 150));
    rooms.add(new Room(103, "Suite", true, 250));
    rooms.add(new Room(104, "Single", true, 100));
    rooms.add(new Room(105, "Double", false, 150)); // This room is not available
  }

  public void searchAvailableRooms(String category) {
    System.out.println("Available Rooms in category: " + category);
    for (Room room : rooms) {
      if (room.isAvailable && room.category.equalsIgnoreCase(category)) {
        System.out.println(room);
      }
    }
  }

  public void makeReservation(String guestName, int roomNumber, int nights) {
    for (Room room : rooms) {
      if (room.roomNumber == roomNumber && room.isAvailable) {
        Reservation reservation = new Reservation(guestName, room, nights);
        reservations.add(reservation);
        room.isAvailable = false; // Mark room as not available
        System.out.println("Reservation successful! " + reservation);
        return;
      }
    }
    System.out.println("Room not available or does not exist.");
  }

  public void viewReservations() {
    if (reservations.isEmpty()) {
      System.out.println("No reservations found.");
    } else {
      System.out.println("Current Reservations:");
      for (Reservation reservation : reservations) {
        System.out.println(reservation);
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    HotelReservationSystem system = new HotelReservationSystem();

    while (true) {
      System.out.println("\nHotel Reservation System:");
      System.out.println("1. Search Available Rooms");
      System.out.println("2. Make a Reservation");
      System.out.println("3. View Reservations");
      System.out.println("4. Exit");
      System.out.print("Choose an option (1-4): ");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          System.out.print("Enter room category (Single, Double, Suite): ");
          String category = scanner.nextLine();
          system.searchAvailableRooms(category);
          break;
        case 2:
          System.out.print("Enter guest name: ");
          String guestName = scanner.nextLine();
          System.out.print("Enter room number: ");
          int roomNumber = scanner.nextInt();
          System.out.print("Enter number of nights: ");
          int nights = scanner.nextInt();
          system.makeReservation(guestName, roomNumber, nights);
          break;
        case 3:
          system.viewReservations();
          break;
        case 4:
          System.out.println("Exiting the program. Thank you for using the Hotel Reservation System.");
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Invalid option. Please choose a valid option (1-4).");
      }
    }
  }
}