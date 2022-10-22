import java.util.HashMap;

public class finalsEmployee extends finalsUserDetails {


    private double salary;


    public finalsEmployee() {
    }

    public finalsEmployee(int ID, String firstName, String lastName, String email, String password, double salary) {
        super(ID, firstName, lastName, email, password);
        setSalary(salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
