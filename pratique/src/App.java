import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Console.printLogo();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        UserService userService = new UserService();

        Console.println("Welcome to Azeitona's User Management system");
        while (running) {
            Console.println("How can we help you?: ");
            Console.println("1. Manage Users");
            Console.println("2. Manage Events");
            Console.println("3. Quit");
            int choice = Integer.parseInt(Console.getNextLine(scanner));
            Boolean waitForResponse = false;

            switch (choice) {
                case 1:

                    String email = "";
                    String name = "";
                    String cpf = "";
                    String password = "";
                    LocalDate birthday = null;

                    Console.green("\n\nWhat would you like to do next?");
                    Console.println("1. Create a new user");
                    Console.println("2. Update existing user");
                    Console.println("3. Delete user");
                    Console.println("4. Add user to existing event");
                    Console.println("5. Go back to main menu");
                    choice = Integer.parseInt(Console.getNextLine(scanner));

                    switch (choice) {
                        case 1:
                            Console.green("\nCreate New User");
                            Console.println("Please provide the user data: ");
                            Console.println("User's email: ");

                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                email = Console.getNextLine(scanner);
                                if (userService.isValidEmail(email)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red("Please enter a valid email");
                                }
                            }

                            Console.println("User's full name: ");
                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                name = Console.getNextLine(scanner);
                                if (userService.isValidName(name)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red(" Please enter a valid FULL name");
                                }
                            }

                            Console.println("User's cpf: ");
                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                cpf = Console.getNextLine(scanner);
                                if (userService.isValidCpf(cpf)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red("Please enter a valid cpf xxx.xxx.xxx-xx");
                                }
                            }

                            Console.println("User's password: ");
                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                password = Console.getNextLine(scanner);
                                if (userService.isValidPassword(password)) {
                                    waitForResponse = false;
                                } else {
                                    Console.red("password may not contain ,");
                                }
                            }

                            Console.println("User's birthday: ");
                            waitForResponse = true;
                            while (waitForResponse) {
                                scanner = new Scanner(System.in);
                                try {
                                    birthday = DateUtils.parseDate(Console.getNextLine(scanner));
                                    waitForResponse = false;

                                } catch (Exception e) {
                                    Console.red("Please enter a valid date.");
                                }

                            }

                            userService.createUser(email, name, cpf, password, birthday);
                            Console.green("User added to database");

                            break;

                        case 2:
                            Console.green("\nEdit existing user");
                            Console.println("Please provide the user email or typ q to quit: ");
                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                String searchEmail = Console.getNextLine(scanner);
                                Boolean isEmail = userService.isValidEmail(searchEmail);

                                if (!(isEmail)) {
                                    if (searchEmail.toLowerCase() == "q") {
                                        waitForResponse = false;
                                    } else {
                                        Console.red("Please provide a valid CPF or Email");
                                    }
                                } else {
                                    Boolean userExists = userService.userExists(searchEmail);
                                    if (userExists) {
                                        email = searchEmail;

                                        User user = userService.getUserByEmail(searchEmail);
                                        Console.green("\nUpdate user " + searchEmail);
                                        Console.println("Please provide the user data: ");
                                        Console.println("Hint: Leave the field blank and press enter to skip");

                                        Console.println("User's full name: ");
                                        waitForResponse = true;
                                        while (waitForResponse) {

                                            scanner = new Scanner(System.in);
                                            name = Console.getNextLine(scanner);
                                            if (userService.isValidName(name)) {
                                                waitForResponse = false;
                                            }
                                            if (name == "") {
                                                name = user.getName();
                                                waitForResponse = false;
                                            }
                                            if (waitForResponse) {
                                                Console.red(" Please enter a valid FULL name");
                                            }
                                        }

                                        Console.println("User's CPF: ");
                                        waitForResponse = true;
                                        while (waitForResponse) {

                                            scanner = new Scanner(System.in);
                                            cpf = Console.getNextLine(scanner);
                                            if (userService.isValidCpf(cpf)) {
                                                waitForResponse = false;
                                            }
                                            if (cpf == "") {
                                                cpf = user.getCpf();
                                                waitForResponse = false;
                                            }
                                            if (waitForResponse) {
                                                Console.red(" Please enter a valid cpf");
                                            }
                                        }

                                        Console.println("User's password: ");
                                        waitForResponse = true;
                                        while (waitForResponse) {

                                            scanner = new Scanner(System.in);
                                            password = Console.getNextLine(scanner);
                                            if (userService.isValidPassword(password)) {
                                                waitForResponse = false;
                                            }
                                            if (password == "") {
                                                password = user.getPassword();
                                                waitForResponse = false;
                                            }
                                            if (waitForResponse) {
                                                Console.red(" Please enter a valid password");
                                            }
                                        }

                                        Console.println("User's birthday: ");
                                        waitForResponse = true;
                                        while (waitForResponse) {

                                            try {
                                                String date = Console.getNextLine(scanner);
                                                if (date == "") {
                                                    birthday = user.getBirthday();
                                                    waitForResponse = false;
                                                } else {
                                                    birthday = DateUtils.parseDate(date);
                                                    waitForResponse = false;
                                                }

                                            } catch (Exception e) {
                                                Console.red("Please enter a valid date.");
                                            }
                                        }

                                        userService.updateUser(email, name, cpf, password, birthday);
                                    } else {
                                        Console.red("User not found in database");
                                        waitForResponse = false;
                                    }
                                }
                            }
                        case 3:
                            Console.green("\nDelete user");
                            Console.println("Please provide the user email or type q to quit: ");
                            waitForResponse = true;
                            while (waitForResponse) {

                                scanner = new Scanner(System.in);
                                String searchEmail = Console.getNextLine(scanner);
                                Boolean isEmail = userService.isValidEmail(searchEmail);

                                if (!(isEmail)) {
                                    if (searchEmail.toLowerCase() == "q") {
                                        waitForResponse = false;
                                    } else {
                                        Console.red("Please provide a valid CPF or Email");
                                    }
                                }
                                if (isEmail && userService.userExists(searchEmail)) {
                                    userService.removeUser(searchEmail);
                                }
                            }
                            break;

                        default:
                            Console.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    Console.println("Exiting...");
                    running = false;
                    break;
                case 3:
                    Console.println("Exiting...");
                    running = false;
                    break;
                case 4:
                    Console.println("Exiting...");
                    running = false;
                    break;
                case 5:
                    Console.println("Exiting...");
                    running = false;
                    break;
                default:
                    Console.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

}

