package ticket;

import passenger.Passenger;
import view.ViewBusTickets;
import view.ViewFlightTickets;

import java.util.ArrayList;
import java.util.Date;

public class BusTicket extends Ticket {
    private Luggage[] luggages;
    private int seat;

    public Luggage[] getLuggages() {
        return luggages;
    }

    public int getSeat() {
        return seat;
    }

    public BusTicket(ArrayList<Passenger> passengers, String company, int price, String destinationLocation,
                     String departLocation, Date startDate, Date endDate, String departurePlace, String arrivalPlace,
                     String travelID, Luggage[] luggages, int seat) {
        super(passengers, company, price, destinationLocation, departLocation, startDate, endDate, departurePlace,
                arrivalPlace, travelID);
        this.luggages = luggages;
        this.seat = seat;
    }
    public BusTicket(ViewBusTickets view, ArrayList<Passenger> passengers) {
        super(view, passengers);
    }
}
