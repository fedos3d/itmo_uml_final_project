package view;

import ticket.FlightServiceClass;
import ticket.TrainServiceClass;

import java.util.Date;
import java.util.Random;

public class ViewTrainTickets extends View {
    private String departure;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private int price;
    private String company;

    @Override
    public String getDeparture() {
        return departure;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public Date getDepartureDate() {
        return departureDate;
    }

    @Override
    public Date getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public String getTravelID() {
        return travelID;
    }

    @Override
    public int getAmount_of_passangers() {
        return amount_of_passangers;
    }

    public TrainServiceClass getSclass() {
        return sclass;
    }

    @Override
    public String toString() {
        return "ViewTrainTickets{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                ", company='" + company + '\'' +
                ", travelID='" + travelID + '\'' +
                ", amount_of_passangers=" + amount_of_passangers +
                ", sclass=" + sclass +
                '}';
    }

    private String travelID;

    public ViewTrainTickets(String departure, String destination, Date departureDate, Date arrivalDate,
                            int amount_of_passangers,
                            TrainServiceClass sclass) {
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

    int amount_of_passangers;
    TrainServiceClass sclass;
}
