import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Console.printLogo();
        System.out.println("Welcome to Azeitona User management system");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("How can we help you?:");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Events");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Managing users");
                    // Add your logic here
                    break;
                case 2:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                case 3:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

