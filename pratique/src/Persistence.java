import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Persistence {
    private final static Integer ATRIBUTES_PER_LINE = 6;

    public static List<User> readUsersFromFile(String filePath) {
        List<User> userList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == ATRIBUTES_PER_LINE - 1) {
                    String email = parts[0];
                    String name = parts[1];
                    String cpf = parts[2];
                    LocalDate birthday = LocalDate.parse(parts[3]);
                    String password = parts[4];
                    User user = new User(email, name, cpf, password, birthday);
                    userList.add(user);
                }
            }
            System.out.println("Users data read from " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to read users data from file: " + e.getMessage());
        }
        return userList;
    }

    public static void writeUserToFile(String filePath, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getEmail()).append(",")
                .append(user.getName()).append(",")
                .append(user.getCpf()).append(",")
                .append(user.getBirthday()).append(",")
                .append(user.getPassword()).append("\n");

        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes(), StandardOpenOption.APPEND);
            System.out.println("User data written to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write user data to file: " + e.getMessage());
        }
    }

    public static void removeUserFromFile(String filePath, String userEmail) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> filteredLines = lines.stream()
                    .filter(line -> !line.contains("Email: " + userEmail))
                    .collect(Collectors.toList());
            Files.write(Paths.get(filePath), filteredLines);
            System.out.println("User removed from file");
        } catch (IOException e) {
            System.err.println("Failed to remove user from file: " + e.getMessage());
        }
    }

    public static void updateUserInFile(String filePath, User updatedUser) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> updatedLines = lines.stream()
                    .map(line -> {
                        if (line.startsWith("Email: " + updatedUser.getEmail())) {
                            return "Email: " + updatedUser.getEmail() + "\n" +
                                    "Name: " + updatedUser.getName() + "\n" +
                                    "CPF: " + updatedUser.getCpf() + "\n" +
                                    "Birthday: " + updatedUser.getBirthday() + "\n";
                        }
                        return line;
                    })
                    .collect(Collectors.toList());
            Files.write(Paths.get(filePath), updatedLines);
            System.out.println("User updated in file");
        } catch (IOException e) {
            System.err.println("Failed to update user in file: " + e.getMessage());
        }
    }

    public void updateUser(String path, User user) {
        removeUserFromFile(path, user.getEmail());

        writeUserToFile(path, user);
    }

    public static void writeEventsToFile(String filePath, List<Events> eventsList) {
        StringBuilder sb = new StringBuilder();
        for (Events event : eventsList) {
            sb.append("Event Info:\n")
                    .append("Date: ").append(event.getDate()).append("\n")
                    .append("Address: ").append(event.getAdress()).append("\n")
                    .append("Event Type: ").append(event.getEventType()).append("\n")
                    .append("Description: ").append(event.getDescription()).append("\n")
                    .append("Ticket Price: ").append(event.getTicketPrice()).append("\n")
                    .append("Tickets For Sale: ").append(event.getTicketsForSale()).append("\n")
                    .append("Max Attendees: ").append(event.getmaxAtendees()).append("\n")
                    .append("Attendees: ").append(String.join(", ", event.getAtendats())).append("\n\n");
        }

        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes());
            System.out.println("Event data written to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write event data to file: " + e.getMessage());
        }
    }

    public static List<Events> readEventsFromFile(String filePath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Failed to read event data from file: " + e.getMessage());
            return null;
        }

        List<Events> eventsList = new ArrayList<>();
        Events event = null;

        for (String line : lines) {
            if (line.startsWith("Event Info:")) {
                // Start of a new event entry
                if (event != null) {
                    eventsList.add(event);
                }
                event = new Events();
            } else if (line.startsWith("Date: ")) {
                event.setDate(LocalDateTime.parse(line.substring(6)));
            } else if (line.startsWith("Address: ")) {
                event.setAddress(line.substring(9));
            } else if (line.startsWith("Event Type: ")) {
                event.setEventType(line.substring(12));
            } else if (line.startsWith("Description: ")) {
                event.setDescription(line.substring(13));
            } else if (line.startsWith("Ticket Price: ")) {
                event.setTicketPrice(Float.parseFloat(line.substring(14)));
            } else if (line.startsWith("Tickets For Sale: ")) {
                event.setTicketsForSale(Boolean.parseBoolean(line.substring(18)));
            } else if (line.startsWith("Max Attendees: ")) {
                event.setmaxAtendees(Integer.parseInt(line.substring(15)));
            } else if (line.startsWith("Attendees: ")) {
                String[] attendees = line.substring(11).split(", ");
                for (String attendee : attendees) {
                    event.addUserToEventAtendats(attendee);
                }
            }
        }

        if (event != null) {
            eventsList.add(event); // Add the last event
        }

        return eventsList;
    }
}
