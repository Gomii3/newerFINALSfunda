public class finalsCustomer extends finalsUserDetails {

    private int complaintID;

    public finalsCustomer() {
    }

    public finalsCustomer(int ID, String firstName, String lastName, String email, String password, int complaintID) {
        super(ID, firstName, lastName, email, password);
        this.complaintID = complaintID;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }
}

