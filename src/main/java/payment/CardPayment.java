package payment;

import java.util.Date;

public class CardPayment extends PaymentMethod{
    private int cardID;
    private Date expireDate;
    private String cardHolder;
    private String cvv;

    public int getCardID() {
        return cardID;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCvv() {
        return cvv;
    }

    public CardPayment(int cardID, Date expireDate, String cardHolder, String cvv) {
        this.cardID = cardID;
        this.expireDate = expireDate;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
    }
}
