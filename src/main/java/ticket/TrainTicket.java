package ticket;

import passenger.Passenger;
import view.ViewTrainTickets;

import java.util.ArrayList;
import java.util.Date;

public class TrainTicket extends Ticket{
    private TrainServiceClass trainServiceClass;
    private int seat;

    public TrainServiceClass getTrainServiceClass() {
        return trainServiceClass;
    }

    public int getSeat() {
        return seat;
    }

    public int getCar() {
        return car;
    }

    private int car;

    public TrainTicket(ArrayList<Passenger> passengers, String company, int price, String destinationLocation,
                       String departLocation, Date startDate, Date endDate, String departurePlace, String arrivalPlace,
                       String travelID, TrainServiceClass trainServiceClass, int seat, int car) {
        super(passengers, company, price, destinationLocation, departLocation, startDate, endDate, departurePlace,
                arrivalPlace, travelID);
        this.trainServiceClass = trainServiceClass;
        this.seat = seat;
        this.car = car;
    }
    public TrainTicket(ViewTrainTickets view, ArrayList<Passenger> passengers) {
        super(view, passengers);
        this.trainServiceClass = view.getSclass();
    }
}
