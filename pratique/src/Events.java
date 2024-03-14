import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Events {
    private LocalDateTime date;
    private String address;
    private String eventType;
    private String description;
    private Float ticketPrice;
    private Boolean ticketsForSale;
    private Integer maxUsers;
    private List<String> users;

    Events(LocalDateTime date, String address, String eventType, String description, Float ticketPrice,
            Boolean ticketsForSale, Integer maxUsers) {
        this.date = date;
        this.address = address;
        this.eventType = eventType;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.ticketsForSale = ticketsForSale;
        this.maxUsers = maxUsers;

        this.users = new ArrayList<>();
    }

    // geters
    public LocalDateTime getDate() {
        return this.date;
    }

    public String getAdress() {
        return this.address;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getDescription() {
        return this.description;
    }

    public float getTicketPrice() {
        return this.ticketPrice;
    }

    public Boolean getTicketsForSale() {
        return this.ticketsForSale;
    }

    public Integer getMaxUsers() {
        return this.maxUsers;
    }

    public List<String> getUsers() {
        return this.users;
    }

    public Boolean checkUsersIsParticipant(String user) {
        return this.users.contains(user);
    }

    // Setters
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTicketsForSale(Boolean ticketsForSale) {
        this.ticketsForSale = ticketsForSale;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public void addUserToEvent(String user) {
        this.users.add(user);
    }

    public void removeUserFromEvent(String user) {
        this.users.remove(user);
    }
}
