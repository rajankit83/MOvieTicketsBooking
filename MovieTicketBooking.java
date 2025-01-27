import java.util.*;

class User {
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class Ticket {
    private String movieName;
    private int seatNumber;
    private int screeningTime;
    
    public Ticket(String movieName, int seatNumber, int screeningTime) {
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.screeningTime = screeningTime;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public String getMovieName() {
        return movieName;
    }
    
    public int getScreeningTime() {
        return screeningTime;
    }
    
    @Override
    public String toString() {
        return "Movie: " + movieName + ", Seat: " + seatNumber + ", Screening Time: " + screeningTime;
    }
}

public class MovieTicketBooking {
    private static Map<String, User> users = new HashMap<>();
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final int TOTAL_SEATS = 10;
    
    public static void main(String[] args) {
        System.out.println("Welcome to Movie Ticket Booking System");
        
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. View All Booked Tickets\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    viewAllBookedTickets();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    
    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try a different one.");
            return;
        }
        
        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }
    
    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful!");
            userMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    
    private static void userMenu() {
        while (true) {
            System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. View Tickets\n4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    viewTickets();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    
    private static void bookTicket() {
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter screening time (1-3): ");
        int screeningTime = scanner.nextInt();
        
        if (screeningTime < 1 || screeningTime > 3) {
            System.out.println("Invalid screening time.");
            return;
        }
        
        System.out.print("Enter seat number (1-" + TOTAL_SEATS + "): ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine();
        
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            System.out.println("Invalid seat number.");
            return;
        }
        
        for (Ticket ticket : tickets) {
            if (ticket.getMovieName().equalsIgnoreCase(movieName) && ticket.getScreeningTime() == screeningTime && ticket.getSeatNumber() == seatNumber) {
                System.out.println("This seat is already booked for the selected movie and screening time.");
                return;
            }
        }
        
        tickets.add(new Ticket(movieName, seatNumber, screeningTime));
        System.out.println("Ticket booked successfully!");
    }
    
    private static void cancelTicket() {
        System.out.print("Enter movie name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter screening time: ");
        int screeningTime = scanner.nextInt();
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine();
        
        Iterator<Ticket> iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getMovieName().equalsIgnoreCase(movieName) && ticket.getScreeningTime() == screeningTime && ticket.getSeatNumber() == seatNumber) {
                iterator.remove();
                System.out.println("Ticket canceled successfully!");
                return;
            }
        }
        
        System.out.println("Ticket not found!");
    }
    
    private static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        
        System.out.println("Your Booked Tickets:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
    
    private static void viewAllBookedTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets have been booked yet.");
            return;
        }
        
        System.out.println("All Booked Tickets:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
