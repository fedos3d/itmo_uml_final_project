package order;

import java.util.Date;

public class Receipt {
    private long id;
    private Date date;

    public Receipt(long id, Date date, Integer price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getPrice() {
        return price;
    }

    private Integer price;
}
