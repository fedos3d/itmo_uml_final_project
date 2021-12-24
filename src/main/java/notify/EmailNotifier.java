package notify;

public class EmailNotifier extends Notifier{
    private String mail;

    @Override
    public String toString() {
        return "EmailNotifier{" +
                "mail='" + mail + '\'' +
                '}';
    }

    public EmailNotifier(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public void sendNotificatoin() {
        //send email notification
    }
}
