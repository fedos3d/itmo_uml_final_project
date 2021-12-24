package notify;

public class SMSNotifier extends Notifier{
    private int Number;

    @Override
    public String toString() {
        return "SMSNotifier{" +
                "Number=" + Number +
                '}';
    }

    public int getNumber() {
        return Number;
    }

    public SMSNotifier(int number) {
        Number = number;
    }

    @Override
    public void sendNotificatoin() {
        //Send Nontification via sms
    }
}
