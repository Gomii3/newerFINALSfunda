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

    private List<String> loggedIn = new ArrayList<>();

    private Scanner otherscan = new Scanner(System.in);


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run(){
        String action,statusCheck,match,employeeOption, displayCustomer, customerOptions, concern,concernStatus, work;
        int choose,concernID;
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
                    match= login();

                    if(match.equalsIgnoreCase("employeematch")){
                        System.out.println("Welcome Employee");
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        do{
                            System.out.println("Would you like to view [C] the customer list or [L] the concerns list or [U] to update a concern");
                            System.out.println("If you are done then type [E] to exit");
                            employeeOption = scan.nextLine();
                            if(employeeOption.equalsIgnoreCase("c")){
                                System.out.println("The following is a list of all the customers in this database");
                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                if(custCheck==0){
                                    System.out.println("There is currently no customers registered in the database");
                                }else {
                                    for (int i = 1; i <= customers.size(); i++) {
                                        this.customer = customers.get(i - 1);
                                        displayCustomer = this.customer.getFirstName() + " " + this.customer.getLastName();
                                        System.out.println("Customer ID " + i + " :" + displayCustomer);
                                    }
                                }
                            } else if (employeeOption.equalsIgnoreCase("l")) {
                                System.out.println("The following is a list of all the concerns in this database");
                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                displayCustomerConcerns();

                            } else if (employeeOption.equalsIgnoreCase("u")) {
                                displayCustomerConcerns();
                                System.out.println("Please select an concern ID to work on");
                                concernID = scan.nextInt();
                                concernStatus = this.concerns.getStatus();
                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                System.out.println("You chose Concern ID : " + this.concerns.getID());
                                System.out.println("The concern is : " + this.concerns.getDetails());
                                System.out.println("STATUS : "+ concernStatus);
                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                this.concerns = Concerns.get(concernID-1);

                                if(concernStatus.equalsIgnoreCase("open")){
                                    System.out.println("Would you like to set the status of this concern to [IN PROGRESS]? [Y/N]");
                                    work = otherscan.nextLine();
                                    if (work.equalsIgnoreCase("y")){
                                        this.concerns.setStatus("IN PROGRESS");
                                        System.out.println("This concern is currently being worked on [IN PROGRESS]");
                                    }else if(work.equalsIgnoreCase("n")){
                                        System.out.println("Ok, returning to options");
                                    }else {
                                        System.out.println("Invalid option returning to main");
                                    }
                                } else if (concernStatus.equalsIgnoreCase("IN PROGRESS")) {
                                    System.out.println("This concern is currently in progress, would you like to mark this concern [CLOSED]? [Y/N]");
                                    work = otherscan.nextLine();
                                    if (work.equalsIgnoreCase("Y")){
                                        this.concerns.setStatus("CLOSED");
                                        System.out.println("You have set this concern status to closed");
                                    } else if (work.equalsIgnoreCase("N")) {
                                        System.out.println("understood, returning to main");
                                    }else {
                                        System.out.println("sorry, invalid option. Returning to main");
                                    }
                                }


                            }
                        }while (!employeeOption.equalsIgnoreCase("E"));
                    }else if(match.equalsIgnoreCase("customerMatch")){
                        System.out.println("Welcome valued customer");
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                        do {
                            System.out.println("Would you like to write a concern? if yes [Y] or [L] (Log out)");
                            customerOptions = scan.nextLine();

                            if (customerOptions.equalsIgnoreCase("Y")) {
                                System.out.println("Hello! you have chosen to file a concern");
                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                                System.out.println("Please type in your concern");
                                concern = scan.nextLine();
                                concernID = Concerns.size() + 1;
                                this.concerns = new concerns(concernID,concern,"OPEN");
                                Concerns.add(this.concerns);

                                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                                System.out.println("Concern ID : " + this.concerns.getID());
                                System.out.println("Concern details : " + this.concerns.getDetails());
                                System.out.println("Concern status : " + this.concerns.getStatus());

                                System.out.println("Your concern is now submitted");
                                System.out.println("Please remember this, your concern ID is " + concernID);

                            }  else if (customerOptions.equalsIgnoreCase("L")) {
                                System.out.println("Exiting the database, thank you for your time");
                            }
                        }while(!customerOptions.equalsIgnoreCase("l"));
                    }
                }
            }else{
                System.out.println("Sorry invalid option");
            }
            if (action.equalsIgnoreCase("e")){
                System.out.println("Thank you for using the program");
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                System.out.println("Now exiting");
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
    public String login(){
        String email, password, employeeEmailCheck, employeePasswordCheck,customerEmailCheck,customerPasswordCheck,match;
        int index, counter, empSize,custSize, forSize;
        match = "";
        forSize = 0;

        System.out.println("Please type in your email");
        email = scan.nextLine();
        System.out.println("Please type in your password");
        password = scan.nextLine();

        empSize = employees.size();
        custSize = customers.size();

        if (!(empSize==0)||!(custSize==0)){
            if(empSize>custSize){
                forSize = empSize;
            }else{
                forSize = custSize;
            }
        }

        for (int i = 1; i <= forSize ; i++){
            if (!(empSize <= 0)){
                this.employee = employees.get(i-1);
                employeeEmailCheck = employee.getEmail();
                employeePasswordCheck = employee.getPassword();
                if(email.equalsIgnoreCase(employeeEmailCheck)){
                    if ((password.equalsIgnoreCase(employeePasswordCheck))){
                        index = i;
                        match = "employeeMatch";
                        return match;
                    }else {
                        System.out.println("incorrect Password");
                        match = "incorrect";
                        return match;
                    }
            }
            }else if(!(custSize==0)) {
                this.customer = customers.get(i-1);
                customerEmailCheck = customer.getEmail();
                customerPasswordCheck = customer.getPassword();
                if(email.equalsIgnoreCase(customerEmailCheck)){
                    if(password.equalsIgnoreCase(customerPasswordCheck)){
                        index = i;
                        match = "customerMatch";
                        return match;
                    }
                }else{
                    System.out.println("Incorrect Email");
                    match = "incorrect";
                    return match;
                }
            }else{
                System.out.println("Sorry there are no employees and or customers in the database");
            }
        }
        return "null";
    }
    public void displayCustomerConcerns() {
        String concernStatus, concernDetails;
        int concernID, concernsCheck;

        concernsCheck = Concerns.size();

        if (concernsCheck == 0) {
            System.out.println("There is no concerns available");
        } else{
            for (int i = 1; i <= Concerns.size(); i++) {
                this.concerns = Concerns.get(i - 1);
                concernDetails = this.concerns.getDetails();
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println("Concern ID : " + this.concerns.getID());
                System.out.println("Concern details : " + concernDetails);
                System.out.println("Concern status : " + this.concerns.getStatus());
            }
        }
    }
}