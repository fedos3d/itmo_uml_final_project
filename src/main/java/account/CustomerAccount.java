package account;

import notify.Notifier;
import order.Order;
import passenger.Passenger;
import payment.PaymentMethod;

import java.util.ArrayList;

public class CustomerAccount extends Account {
    private ArrayList<Passenger> passengers;

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }




    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    private String password;
//    private String username;
    private String mail;
    private ArrayList<Order> orders;
    private PaymentMethod paymentMethod;
    private Notifier notifier;

    public CustomerAccount(String mail, Notifier notifier) {
        this.mail = mail;
        this.orders = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.notifier = notifier;
    }
}
