import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner scan = new Scanner(System.in);

    private finalsCustomer customer;

    private finalsEmployee employee;

    private concerns concerns = new concerns();

    private List<finalsEmployee> employees = new ArrayList<>();

    private List<finalsCustomer> customers = new ArrayList<>();

    private List<concerns> Concerns = new ArrayList<>();


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        String action,statusCheck;
        do{
            System.out.println("Would you like to [R] register, [L] login [E] to exit the program");
            action = scan.nextLine();

            if(action.equalsIgnoreCase("r")){
                System.out.println("You have chosen to Register");
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                System.out.println("Are you an Employee? or a Customer?");
                statusCheck = scan.nextLine();

                if(statusCheck.equalsIgnoreCase("employee")||statusCheck.equalsIgnoreCase("customer")){
                    register(statusCheck);
                }else{
                    System.out.println("Sorry invalid option");
                }
            } else if (action.equalsIgnoreCase("l")) {
                int empCheck,custCheck;

                empCheck = employees.size();
                custCheck = customers.size();

                System.out.println("You have chosen to Login");
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");


                if(empCheck == 0 && custCheck == 0){
                    System.out.println("The database is empty");
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("Please register first");
                }else{
                    login();
                }
            }

        }while(!action.equalsIgnoreCase("E"));
    }

    public void register(String statusCheck){
        String firstName,lastName,email,password;
        double salary;
        int IDNumber;
        boolean registering = true;

        do{
        System.out.println("Please type your first name");
        firstName = scan.nextLine();

        System.out.println("please type your last name");
        lastName = scan.nextLine();

        System.out.println("please type your email");
        email = scan.nextLine();

        System.out.println("please type your password");
        password = scan.nextLine();

        if (statusCheck.equalsIgnoreCase("employee")){
            System.out.println("Please type your Salary");
            salary = scan.nextDouble();

            IDNumber= employees.size() + 1;
            this.employee = new finalsEmployee(IDNumber, firstName,lastName,email,password,salary);
            employees.add(employee);
            System.out.println("Registering successful");
            registering = false;
        } else{
            IDNumber= customers.size() + 1;
            this.customer = new finalsCustomer(IDNumber, firstName, lastName, email, password, IDNumber);
            customers.add(this.customer);
            System.out.println("Registering successful");
            registering = false;
        }
    }while(registering == true);
    }
    public void login(){
        String email, password, employeeEmailCheck, employeePasswordCheck,customerEmailCheck,customerPasswordCheck,match,
         status;
        int index;
        match = "";

        System.out.println("Please type in your email");
        email = scan.nextLine();
        System.out.println("Please type in your password");
        password = scan.nextLine();

        for (int i = 0; i<employees.size();i++){
            this.employee = employees.get(i);
            this.customer = customers.get(i);
            employeeEmailCheck = employee.getEmail();
            employeePasswordCheck = employee.getPassword();
            customerEmailCheck = customer.getEmail();
            customerPasswordCheck = customer.getPassword();

            if(email.equalsIgnoreCase(employeeEmailCheck)){
                if ((password.equalsIgnoreCase(employeePasswordCheck))){
                    index = i;
                    match = "employeeMatch";
                }else {
                    System.out.println("incorrect Password");
                }
            }else if(email.equalsIgnoreCase(customerEmailCheck)){
                if(password.equalsIgnoreCase(customerPasswordCheck)){
                    index = i;
                    match = "customerMatch";
                }
            }else{
                System.out.println("Incorrect Email");
                break;
            }
        }
        if(match.equalsIgnoreCase("employeeMatch")){

        }
    }
}