package view;

import java.util.Date;
import java.util.Random;

public class ViewBusTickets extends View{
    private String departure;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private int price;

    private String company;
    private String travelID;
    int amount_of_passangers;

    public String getDeparture() {
        return departure;
    }

    @Override
    public String toString() {
        return "ViewBusTickets{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                ", company='" + company + '\'' +
                ", travelID='" + travelID + '\'' +
                ", amount_of_passangers=" + amount_of_passangers +
                '}';
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

    public int getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }

    public String getTravelID() {
        return travelID;
    }

    public int getAmount_of_passangers() {
        return amount_of_passangers;
    }

    public ViewBusTickets(String departure, String destination, Date departureDate, Date arrivalDate, int amount_of_passangers) {
        Random random = new Random();
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.amount_of_passangers = amount_of_passangers;
        this.departureDate.setHours(random.nextInt(24));
        this.arrivalDate.setHours(random.nextInt(24));
        this.price = random.nextInt(25000 - 3000) + 3000;
        this.company = "FilightCompany";
        this.travelID = Integer.toString(random.nextInt(124));
    }


}
