package view;

import java.util.Date;

public abstract class View {
    public int getAmount_of_passangers() {
        return amount_of_passangers;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getCompany() {
        return company;
    }

    public String getTravelID() {
        return travelID;
    }

    @Override
    public String toString() {
        return "View{" +
                "amount_of_passangers=" + amount_of_passangers +
                ", price=" + price +
                '}';
    }

    public int getPrice() {
        return price;
    }

    private int amount_of_passangers;
    private int price;
    private String departure;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

    private String company;
    private String travelID;

}
