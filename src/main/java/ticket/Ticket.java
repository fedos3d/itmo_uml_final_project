package ticket;

import passenger.Passenger;
import view.View;
import view.ViewFlightTickets;

import java.util.ArrayList;
import java.util.Date;

public abstract class Ticket {
    private ArrayList<Passenger> passengers;
    private String company;
    private int price;
    private String destinationLocation;
    private String departLocation;

    public Ticket(ArrayList<Passenger> passengers, String company, int price, String destinationLocation,
                  String departLocation, Date startDate, Date endDate, String departurePlace, String arrivalPlace,
                  String travelID) {
        this.passengers = passengers;
        this.company = company;
        this.price = price;
        this.destinationLocation = destinationLocation;
        this.departLocation = departLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.travelID = travelID;
    }

    private Date startDate;
    private Date endDate;
    private String departurePlace;

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public String getCompany() {
        return company;
    }

    public float getPrice() {
        return price;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDepartLocation() {
        return departLocation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public String getTravelID() {
        return travelID;
    }

    private String arrivalPlace;
    private String travelID;

    public Ticket(View view, ArrayList<Passenger> passengers) {
        this.passengers = passengers;
        this.company = view.getCompany();
        this.price = view.getPrice();
        this.destinationLocation = view.getDestination();
        this.departLocation = view.getDeparture();
        this.startDate = view.getDepartureDate();
        this.endDate = view.getArrivalDate();
        this.travelID = view.getTravelID();
    }
}
