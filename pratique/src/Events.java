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
    private Integer maxAtendants;
    private List<String> atendants;

    Events(LocalDateTime date, String address, String eventType, String description, Float ticketPrice,
            Boolean ticketsForSale, Integer maxAtendants) {
        this.date = date;
        this.address = address;
        this.eventType = eventType;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.ticketsForSale = ticketsForSale;
        this.maxAtendants = maxAtendants;

        this.atendants = new ArrayList<>();
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

    public Integer getmaxAtendants() {
        return this.maxAtendants;
    }

    public List<String> getAtendats() {
        return new ArrayList<>(this.atendants);
    }

    public Boolean isUsersAtendant(String atendant) {
        return this.atendants.contains(atendant);
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

    public void setmaxAtendants(Integer maxAtendants) {
        this.maxAtendants = maxAtendants;
    }

    public void addUserToEventAtendats(String user) {
        this.atendants.add(user);
    }

    public void removeUserFromEventAtendants(String user) {
        this.atendants.remove(user);
    }
}
