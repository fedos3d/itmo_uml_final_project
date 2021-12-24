package view;

import ticket.FlightServiceClass;

import java.util.Date;
import java.util.Random;

public class ViewFlightTickets extends View {
    private String departure;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private int price;
    private String company;
    private String travelID;
    int amount_of_passangers;
    FlightServiceClass sclass;


    public String getCompany() {
        return company;
    }

    public String getTravelID() {
        return travelID;
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

    public int getPrice() {
        return price;
    }

    public int getAmount_of_passangers() {
        return amount_of_passangers;
    }

    public FlightServiceClass getSclass() {
        return sclass;
    }

    @Override
    public String toString() {
        return
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                ", amount_of_passangers=" + amount_of_passangers +
                ", sclass=" + sclass +
                        ", company=" + company +
                        ", travelID=" + travelID +
                '}';
    }


    public ViewFlightTickets(String departure, String destination, Date departureDate, Date arrivalDate,
                             int amount_of_passangers, FlightServiceClass sclass) {
        Random random = new Random();
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureDate.setHours(random.nextInt(24));
        this.arrivalDate = arrivalDate;
        this.arrivalDate.setHours(random.nextInt(24));
        this.amount_of_passangers = amount_of_passangers;
        this.sclass = sclass;
        this.price = random.nextInt(25000 - 3000) + 3000;
        this.company = "FilightCompany";
        this.travelID = Integer.toString(random.nextInt(124));
    }


}
