package helpdesk;

public class HelpRequest {
    private long ID;
    private String message;
    private String contactInfo;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "HelpRequest{" +
                "ID=" + ID +
                ", message='" + message + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public HelpRequest(long ID, String message, String contactInfo) {
        this.ID = ID;
        this.message = message;
        this.contactInfo = contactInfo;
    }
}
