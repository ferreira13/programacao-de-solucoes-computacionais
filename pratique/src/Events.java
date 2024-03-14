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
    private Integer maxAtendees;
    private List<String> atendees;

    public Events() {

    }

    public Events(LocalDateTime date, String address, String eventType, String description, Float ticketPrice,
            Boolean ticketsForSale, Integer maxAtendees) {
        this.date = date;
        this.address = address;
        this.eventType = eventType;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.ticketsForSale = ticketsForSale;
        this.maxAtendees = maxAtendees;

        this.atendees = new ArrayList<>();
    }

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

    public Integer getmaxAtendees() {
        return this.maxAtendees;
    }

    public List<String> getAtendats() {
        return new ArrayList<>(this.atendees);
    }

    public Boolean isUsersAtendant(String atendant) {
        return this.atendees.contains(atendant);
    }

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

    public void setmaxAtendees(Integer maxAtendees) {
        this.maxAtendees = maxAtendees;
    }

    public void addUserToEventAtendats(String user) {
        this.atendees.add(user);
    }

    public void removeUserFromEventAtendees(String user) {
        this.atendees.remove(user);
    }
}
