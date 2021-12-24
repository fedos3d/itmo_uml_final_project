package order;

import ticket.Ticket;
import view.ViewFlightTickets;

import java.util.Date;

public class Order {
    private long id;
    private Ticket ticket;
    private Date orderDate;
    private Integer price;
    private Receipt receipt;
    private ViewFlightTickets view;

    public long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Integer getPrice() {
        return price;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public ViewFlightTickets getView() {
        return view;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", receipt=" + receipt +
                ", view=" + view +
                '}';
    }

    public Order(long id, Ticket ticket, Date orderDate, Integer price) {
        this.id = id;
        this.ticket = ticket;
        this.orderDate = orderDate;
        this.price = price;
        this.receipt = new Receipt(this.id, orderDate, price);
    }
}
