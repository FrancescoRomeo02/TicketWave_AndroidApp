package it.marcosoft.ticketwave;

public class CardData {
    private String destination;
    private String dateFrom;
    private String dateTo;

    public CardData(String destination, String dateFrom, String dateTo) {
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
