public class concerns {

    private int ID;

    private String details,status;


    public concerns() {
    }

    public concerns(int ID, String details, String status) {
        this.ID = ID;
        this.details = details;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
