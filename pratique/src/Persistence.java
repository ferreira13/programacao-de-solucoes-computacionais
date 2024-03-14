import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Persistence {

    public static void writeUserToFile(String filePath, User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("User Info:\n")
                .append("Email: ").append(user.getEmail()).append("\n")
                .append("Name: ").append(user.getName()).append("\n")
                .append("CPF: ").append(user.getCpf()).append("\n")
                .append("Birthday: ").append(user.getBirthday()).append("\n\n");

        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes());
            System.out.println("User data written to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write user data to file: " + e.getMessage());
        }
    }

    public static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            User user = null;
            for (String line : lines) {
                if (line.startsWith("Email: ")) {
                    if (user != null) {
                        users.add(user);
                    }
                    user = new User();
                    user.setEmail(line.substring(7));
                } else if (line.startsWith("Name: ")) {
                    user.setName(line.substring(6));
                } else if (line.startsWith("CPF: ")) {
                    user.setCpf(line.substring(5));
                } else if (line.startsWith("Birthday: ")) {
                    user.setBirthday(LocalDate.parse(line.substring(10)));
                }
            }
            if (user != null) {
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Failed to read user data from file: " + e.getMessage());
        }
        return users;
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
