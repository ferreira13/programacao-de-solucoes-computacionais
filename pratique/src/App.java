import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Console.printLogo();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        UserService userService = new UserService();

        System.out.println("Welcome to Azeitona's User Management system");
        while (running) {
            System.out.println("How can we help you?:");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Events");
            System.out.println("3. Quit");
            System.out.print("--->");
            int choice = scanner.nextInt();
            Boolean waitForResponse = false;

            switch (choice) {
                case 1:
                    Console.green("\n\nWhat would you like to do next?");
                    System.out.println("1. Create a new user");
                    System.out.println("2. Update existing user");
                    System.out.println("3. Delete user");
                    System.out.println("4. Add user to existing event");
                    System.out.println("5. Go back to main menu");
                    System.out.print("--->");

                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            Console.green("\nCreate New User");
                            System.out.println("Please provide the user data:");
                            System.out.println("User's email:");

                            waitForResponse = true;
                            String email = "";
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                email = scanner.nextLine();
                                if (userService.emailIsOk(email)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red("Please enter a valid email");
                                }
                            }

                            System.out.println("User's full name:");
                            waitForResponse = true;
                            String name = "";
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                if (userService.nameIsOk(name)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red(" Please enter a valid FULL name");
                                }
                            }

                            System.out.println("User's cpf:");
                            waitForResponse = true;
                            String cpf = "";
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                cpf = scanner.nextLine();
                                if (userService.cpfIsOk(cpf)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red(" Please enter a valid cpf xxx.xxx.xxx-xx");
                                }
                            }

                            System.out.println("User's password:");
                            waitForResponse = true;
                            String password = "";
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                password = scanner.nextLine();
                                if (userService.passwordIsOk(password)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red("password may not contain ,");
                                }
                            }

                            System.out.println("User's birthday:");
                            waitForResponse = true;
                            LocalDate birthday = null;
                            while (waitForResponse) {
                                scanner = new Scanner(System.in);
                                try {
                                    birthday = parseDate(scanner.nextLine());
                                    waitForResponse = false;

                                } catch (Exception e) {
                                    Console.red("Please enter a valid date.");
                                }

                            }

                            userService.createUser(email, name, cpf, password, birthday);
                            Console.yellow(userService.getUserList().toString());

                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
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

    public static LocalDate parseDate(String dateString) {
        String[] parts = dateString.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + dateString);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private static boolean isValidDate(int day, int month, int year) {
        if (year < 1 || year > 9999 || month < 1 || month > 12) {
            return false;
        }

        int daysInMonth = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
            default -> 0;
        };

        return day >= 1 && day <= daysInMonth;
    }

}

