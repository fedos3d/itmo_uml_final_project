package ticket;

import passenger.Passenger;
import view.ViewFlightTickets;

import java.util.ArrayList;
import java.util.Date;

public class FlightTicket extends Ticket{
    public FlightServiceClass getFlightServiceClass() {
        return flightServiceClass;
    }

    public Luggage[] getLuggages() {
        return luggages;
    }

    public String getSeat() {
        return seat;
    }

    private FlightServiceClass flightServiceClass;
    private Luggage[] luggages;
    private String seat;

    public FlightTicket(ArrayList<Passenger> passengers, String company, int price, String destinationLocation,
                        String departLocation, Date startDate, Date endDate, String departurePlace, String arrivalPlace,
                        String travelID, FlightServiceClass flightServiceClass, Luggage[] luggages, String seat) {
        super(passengers, company, price, destinationLocation, departLocation, startDate, endDate, departurePlace,
                arrivalPlace, travelID);
        this.flightServiceClass = flightServiceClass;
        this.luggages = luggages;
        this.seat = seat;
    }
    public FlightTicket(ViewFlightTickets view, ArrayList<Passenger> passengers) {
        super(view, passengers);
        this.flightServiceClass = view.getSclass();
    }
}
