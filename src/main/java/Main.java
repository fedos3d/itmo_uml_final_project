import account.CustomerAccount;
import account.StaffAccount;
import helpdesk.HelpRequest;
import notify.EmailNotifier;
import order.Order;
import passenger.Passenger;
import passenger.Sex;
import payment.CardPayment;
import ticket.*;
import view.View;
import view.ViewBusTickets;
import view.ViewFlightTickets;
import view.ViewTrainTickets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Boolean logged = false;
    private static String curUser = null;
    private static Boolean staffRole = false;
    private static Map<String, CustomerAccount> customers = new HashMap<>();
    private static Map<String, StaffAccount> staff = new HashMap<>();
    private static ArrayList<HelpRequest> helpRequests = new ArrayList<>();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static long ids = 1;
    private static long helpIDS = 1;
    private static long ticketIDS = 1;


    public static void main(String[] args) {
        printCommandSet();
    }

    public static void printCommandSet() {
        System.out.println("You can:");
        if (!logged) {
            System.out.println("Login - press 1");
            System.out.println("Register - press 2");
            System.out.println("View Tickets - press 3");
            System.out.println("Send Help Request - press 9");

        } else if (logged && !staffRole) {
            System.out.println("Logout - press 4");
            System.out.println("View Tickets - press 3");
            System.out.println("List Passengers - press 8");
            System.out.println("Send Help Request - press 9");
            System.out.println("List orders - press 10");
            System.out.println("Add Passenger - 11");
            System.out.println("Add Payment Method - 13");
            System.out.println("Check FAQ - press 15");
        } else if (logged && staffRole) {
            System.out.println("Logout - press 4");
            System.out.println("Work with help request - 12");
        }
        int command = scanner.nextInt();
        switch (command) {
            case (1):
                login();
                break;
            case (2):
                register();
                break;
            case (3):
                printviewTicketsPanel();
                break;
            case (4):
                logout();
                break;
            case (8):
                listPassangers();
                break;
            case (10):
                listOrders();
                break;
            case (11):
                addPassenger(false);
                break;
            case (13):
                addPaymentMethod(false);
            case (9):
                sendHelpRequest();
                break;
            case (12):
                printRequestPanel();
                break;
            case (15):
                printFAQ();
                break;
            default:
                printCommandSet();
                break;
        }
    }

    //LOGIN BLOCK

    public static void userLogin() {
        System.out.println("PLease enter username: ");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        if (customers.containsKey(username)) {
            if (Objects.equals(customers.get(username).getPassword(), password)) {
                logged = true;
                curUser = username;
                System.out.println("You are now logged in as: " + username);
                printCommandSet();
            } else {
                System.out.println("Password is incorrect");
                userLogin();
            }
        } else {
            System.out.println("There is no such user");
            userLogin();
        }
    }
    public static void staffLogin() {
        System.out.println("PLease enter username: ");
        String username = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        if (staff.containsKey(username)) {
            if (Objects.equals(staff.get(username).getPassword(), password)) {
                logged = true;
                curUser = username;
                staffRole = true;
                System.out.println("(Staff) You are now logged in as: " + username);
                printCommandSet();
            } else {
                System.out.println("Password is incorrect");
                staffLogin();
            }
        } else {
            System.out.println("There is no such user");
            staffLogin();
        }
    }
    public static void login() {
        System.out.println("Set login enivronment:");
        System.out.println("press 1 for User Accounts, press 2 for Staff Accounts");
        int command = scanner.nextInt();
        if (command == 1) {
            userLogin();
        } else {
            staffLogin();
        }
    }

    //REGISTRATION BLOCK

    private static boolean checkUserNameAndPassword(String username, String password) {
        if (customers.containsKey(username)) {
            System.out.println("Account with such a name already exists");
            return false;
        }
        return true;
    }
    public static boolean checkEmailAddress(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }


    public static void customerRegister() {
        System.out.println("Make up a username: ");
        String username = scanner.next();
        System.out.println("Make up a password");
        String password = scanner.next();
        if (!checkUserNameAndPassword(username, password)) {
            customerRegister();
        } else {
            System.out.println("Fill in your email:");
            String email = scanner.next();
            if (!checkEmailAddress(email)) {
                System.out.println("Incorrect email, please enter valid email");
                customerRegister();
            } else {
                CustomerAccount newcust = new CustomerAccount(email, new EmailNotifier(email));
                newcust.setUsername(username);
                newcust.setPassword(password);
                newcust.setId(ids++);
                customers.put(username, newcust);
                System.out.println("You are successfully registered!");
                printCommandSet();
            }
        }
    }

    public static void staffRegister() {
        System.out.println("Make up a username: ");
        String username = scanner.next();
        System.out.println("Make up a password");
        String password = scanner.next();
        if (!checkUserNameAndPassword(username, password)) {
            customerRegister();
        } else {
            System.out.println("Fill in your first name:");
            String firstName = scanner.next();
            System.out.println("Fill in your last name: ");
            String lastname = scanner.next();
            StaffAccount newstuff = new StaffAccount(firstName, lastname);
            newstuff.setUsername(username);
            newstuff.setPassword(password);
            newstuff.setId(ids++);
            staff.put(username, newstuff);
            System.out.println("Staff account successfully registered!");
            printCommandSet();
        }
    }
    public static void register() {
        System.out.println("Pick account type: ");
        System.out.println("press 1 to create customer account, press 2 to create staff account");
        int command = scanner.nextInt();
        if (command == 1) {
            customerRegister();
        } else {
            staffRegister();
        }
    }




    public static void logout() {
        logged = false;
        curUser = null;
        staffRole = false;
        System.out.println("You are now logged out");
        printCommandSet();
    }
    public static void sendHelpRequest() {
        System.out.println("Describe a problem you faces: ");
        String message = scanner.nextLine();
        System.out.println("Please leave contact info in any form you want: ");
        String contactInfo = scanner.nextLine();
        helpRequests.add(new HelpRequest(helpIDS++, message, contactInfo));
        System.out.println("Your request was successfully sent! We will contact you as soon as possible!");
        printCommandSet();
    }
    public static void printRequestPanel() {
        System.out.println("press 1 to start working with next help request, press 2 to exit request panel");
        int command = scanner.nextInt();
        if (command == 1) {
            if (!helpRequests.isEmpty()) {
                System.out.println(helpRequests.get(0).toString());
                System.out.println("press 1 to mark this request as resolved, press 2 to quit this request:");
                command = scanner.nextInt();
                if (command == 1) {
                    helpRequests.remove(0);
                    printRequestPanel();
                } else {
                    printRequestPanel();
                }
            } else {
                System.out.println("Request query is empty, there is nothing to do, returning to main menu...");
                printCommandSet();
            }
        }
    }
    public static void printviewTicketsPanel() {
        //here would be api calls to fetch tickets info;
        System.out.println("Select tickets type:");
        System.out.println("press 1 - plane, press 2 - bus, press 3 - train");
        int command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
            case (1):
                printViewPlaneTickets();
                break;
            case (2):
                printViewBusTickets();
                break;
            case (3):
                printTrainTickets();
                break;
            default:
                System.out.println("Something went wrong, returning to main menu...");
                printCommandSet();
        }
    }
    public static void printViewPlaneTickets() {
        System.out.println("You are watching plane tickets:");
        System.out.println("Select departure: ");
        String depature = scanner.nextLine();
        System.out.println("Select destination: ");
        String destination = scanner.nextLine();
        System.out.println("Select Departure Date (in form \"dd/MM/yyyy\"): ");
        String sDate1 = scanner.nextLine();
        Date departureDate;
        try {
            departureDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Select Arrival Date (in form \"dd/MM/yyyy\"): ");
        String sDate2 = scanner.nextLine();
        Date arrivalDate;
        try {
            arrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Specify amount of passengers: ");
        int amount_of_passengers = scanner.nextInt();
        System.out.println("Specify service class: ");
        System.out.println("press 1 - First Class, press 2 - Economy Class, press 3 - Business Class");
        FlightServiceClass sclass;
        int command = scanner.nextInt();
        switch (command) {
            case (1):
                sclass = FlightServiceClass.FirstClass;
                break;
            case (2):
                sclass = FlightServiceClass.EconomyClass;
                break;
            case (3):
                sclass = FlightServiceClass.BusinessClass;
                break;
            default:
                sclass = FlightServiceClass.EconomyClass;
        }
        fetchTicketsWithParamsFlight(depature, destination, departureDate, arrivalDate, amount_of_passengers, sclass);
    }

    public static void printViewBusTickets() {
        System.out.println("You are watching bus tickets:");
        System.out.println("Select departure: ");
        String depature = scanner.nextLine();
        System.out.println("Select destination: ");
        String destination = scanner.nextLine();
        System.out.println("Select Departure Date (in form \"dd/MM/yyyy\"): ");
        String sDate1 = scanner.nextLine();
        Date departureDate;
        try {
            departureDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Select Arrival Date (in form \"dd/MM/yyyy\"): ");
        String sDate2 = scanner.nextLine();
        Date arrivalDate;
        try {
            arrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Specify amount of passengers: ");
        int amount_of_passengers = scanner.nextInt();
        scanner.nextLine();
        fetchTicketsWithParamsBus(depature, destination, departureDate, arrivalDate, amount_of_passengers);
    }

    public static void printTrainTickets() {
        System.out.println("You are watching plane tickets:");
        System.out.println("Select departure: ");
        String depature = scanner.nextLine();
        System.out.println("Select destination: ");
        String destination = scanner.nextLine();
        System.out.println("Select Departure Date (in form \"dd/MM/yyyy\"): ");
        String sDate1 = scanner.nextLine();
        Date departureDate;
        try {
            departureDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Select Arrival Date (in form \"dd/MM/yyyy\"): ");
        String sDate2 = scanner.nextLine();
        Date arrivalDate;
        try {
            arrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Something went wrong, returning to menu...");
            printCommandSet();
            return;
        }
        System.out.println("Specify amount of passengers: ");
        int amount_of_passengers = scanner.nextInt();
        System.out.println("Specify service class: ");
        System.out.println("press 1 - PlazCart, press 2 - First Class, press 3 - Second Class, press 4 - Common Car, " +
                "press 5 - Luxe, press 6 - Soft");
        TrainServiceClass sclass;
        int command = scanner.nextInt();
        switch (command) {
            case (1):
                sclass = TrainServiceClass.Plazcart;
                break;
            case (2):
                sclass = TrainServiceClass.FirstClass;
                break;
            case (3):
                sclass = TrainServiceClass.SecondClass;
                break;
            case (4):
                sclass = TrainServiceClass.CommonCar;
                break;
            case (5):
                sclass = TrainServiceClass.Luxe;
                break;
            case (6):
                sclass = TrainServiceClass.Soft;
                break;
            default:
                sclass = TrainServiceClass.Plazcart;
        }
        fetchTicketsWithParamsTrain(depature, destination, departureDate, arrivalDate, amount_of_passengers, sclass);
    }

    public static void fetchTicketsWithParamsFlight(String departure, String destination, Date departureDate,
                                              Date arrivalDate, int amount_of_passangers, FlightServiceClass sclass) {
        Random random = new Random();
        int amount_of_tickets_to_show = random.nextInt(10 - 2) + 2;
        ArrayList<ViewFlightTickets> viewTickets = new ArrayList<ViewFlightTickets>();
        for (int i = 0; i < amount_of_tickets_to_show; i++) {
            viewTickets.add(new ViewFlightTickets(departure, destination, departureDate, arrivalDate,
                    amount_of_passangers, sclass));
        }
        for (int i = 0; i < viewTickets.size(); i++) {
            System.out.println("Ticket_id: " + i + ", " + viewTickets.get(i).toString());
        }
        System.out.println("Please pick one ticket. Enter it's id: (or you can quit by entering 15");
        int command = scanner.nextInt();
        if (command == 15) {
            printCommandSet();
        } else {
            proceedToCheckOut(viewTickets.get(command));
        }

    }

    public static void fetchTicketsWithParamsBus(String departure, String destination, Date departureDate,
                                                 Date arrivalDate, int amount_of_passangers) {
        Random random = new Random();
        int amount_of_tickets_to_show = random.nextInt(10 - 2) + 2;
        ArrayList<ViewBusTickets> viewTickets = new ArrayList<ViewBusTickets>();
        for (int i = 0; i < amount_of_tickets_to_show; i++) {
            viewTickets.add(new ViewBusTickets(departure, destination, departureDate, arrivalDate,
                    amount_of_passangers));
        }
        for (int i = 0; i < viewTickets.size(); i++) {
            System.out.println("Ticket_id: " + i + ", " + viewTickets.get(i).toString());
        }
        System.out.println("Please pick one ticket. Enter it's id: (or you can quit by entering 15");
        int command = scanner.nextInt();
        if (command == 15) {
            printCommandSet();
        } else {
            proceedToCheckOut(viewTickets.get(command));
        }

    }


    public static void fetchTicketsWithParamsTrain(String departure, String destination, Date departureDate,
                                                    Date arrivalDate, int amount_of_passangers, TrainServiceClass sclass) {
        Random random = new Random();
        int amount_of_tickets_to_show = random.nextInt(10 - 2) + 2;
        ArrayList<ViewTrainTickets> viewTickets = new ArrayList<ViewTrainTickets>();
        for (int i = 0; i < amount_of_tickets_to_show; i++) {
            viewTickets.add(new ViewTrainTickets(departure, destination, departureDate, arrivalDate,
                    amount_of_passangers, sclass));
        }
        for (int i = 0; i < viewTickets.size(); i++) {
            System.out.println("Ticket_id: " + i + ", " + viewTickets.get(i).toString());
        }
        System.out.println("Please pick one ticket. Enter it's id: (or you can quit by entering 15");
        int command = scanner.nextInt();
        if (command == 15) {
            printCommandSet();
        } else {
            proceedToCheckOut(viewTickets.get(command));
        }
    }

    public static void proceedToCheckOut(View view) {
        if (logged) {
            System.out.println("Proceed to checkout? press 1 to proceed, press 2 to abort");
            int command = scanner.nextInt();
            if (command == 1) {
                CustomerAccount acc = customers.get(curUser);
                while (acc.getPassengers().isEmpty() || acc.getPassengers().size() < view.getAmount_of_passangers()) {
                    System.out.println("Before proceeding you need to add passangers");
                    addPassenger(true);
                }
                while (acc.getPaymentMethod() == null) {
                    System.out.println("Before proceeding you need to add payment method");
                    addPaymentMethod(true);
                }
                System.out.println("Ticket info once again:");
                System.out.println(view.toString());
                System.out.println("Confirm Payment: press 1 - to proceed and pay, press 2 - to abort");
                command = scanner.nextInt();
                if (command == 1) {
                    System.out.println("Openning Payment Gateway....");
                    //Proceed Payment
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Payment Succeded!");
                    if (view.getClass() == ViewBusTickets.class) {
                        acc.getOrders().add(new Order(ticketIDS++, new BusTicket((ViewBusTickets) view,
                                acc.getPassengers()), new Date(), view.getPrice()));
                    } else if (view.getClass() == ViewFlightTickets.class) {
                        acc.getOrders().add(new Order(ticketIDS++, new FlightTicket((ViewFlightTickets) view,
                                acc.getPassengers()), new Date(), view.getPrice()));
                    } else if (view.getClass() == ViewTrainTickets.class) {
                        acc.getOrders().add(new Order(ticketIDS++, new TrainTicket((ViewTrainTickets) view,
                                acc.getPassengers()), new Date(), view.getPrice()));

                    }
                    System.out.println("Would you like to download tickets right now? " +
                            "(press 1 to download, press 2 to skip)");
                    command = scanner.nextInt();
                    if (command == 1) {
                        System.out.println("Tickets downloaded");
                    }
                    System.out.println("Information will be sent to endpoints specified in settings:");
                    System.out.println(acc.getNotifier().toString());
                    acc.getNotifier().sendNotificatoin();
                    printCommandSet();
                } else {
                    printCommandSet();
                }
            } else {
                printCommandSet();
            }

        } else {
            System.out.println("You need to login first before proceeding to checkout. Exiting to main menu...");
            printCommandSet();
        }
    }
    public static void addPassenger(Boolean proced) {
        if (logged) {
            System.out.println("Here we will be adding passengers to your account");
            System.out.println("Specify sex: (press 1 for men, press 2 for woman)");
            Sex sex = Sex.MALE;
            int command = scanner.nextInt();
            scanner.nextLine();
            if (command == 2) {
                sex = Sex.FEMALE;
            }
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter surname:");
            String surname = scanner.next();
            System.out.println("Enter birth date (in form \"dd/MM/yyyy\")");
            String str = scanner.next();
            scanner.nextLine();
            Date birthDate;
            try {
                birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Something went wrong, returning to menu...");
                printCommandSet();
                return;
            }
            System.out.println("Enter passport id number: ");
            String pass_id = scanner.nextLine();
            customers.get(curUser).getPassengers().add(new Passenger(name, surname, pass_id, birthDate, sex));
            System.out.println("New passanger successfully added!");
            if (proced) {

            } else {
                printCommandSet();
            }

        } else {
            System.out.println("Your are not logged in, returning to main menu...");
            printCommandSet();
        }

    }
    public static void addPaymentMethod(Boolean proced) {
        if (logged) {
            System.out.println("Here we will be adding payment method (card payment)");
            System.out.println("Enter Card number:");
            String card_num = scanner.nextLine();
            System.out.println("Enter expire date (in form \\\"dd/MM/yyyy\\\"\"");
            String date_exp = scanner.next();
            scanner.nextLine();
            Date expireDate;
            try {
                expireDate = new SimpleDateFormat("dd/MM/yyyy").parse(date_exp);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Something went wrong, returning to menu...");
                printCommandSet();
                return;
            }
            System.out.println("Enter Card Holder Name");
            String cardHolder = scanner.nextLine();
            System.out.println("Enter cvv code: ");
            String cvv_code = scanner.next();
            CardPayment cp = new CardPayment(Integer.parseInt(card_num), expireDate, cardHolder, cvv_code);
            customers.get(curUser).setPaymentMethod(cp);
            System.out.println("Card was added successfully!");
            if (proced) {

            } else {
                printCommandSet();
            }
        } else {
            System.out.println("Your are not logged in, returning to main menu...");
            printCommandSet();
        }
    }
    public static void listPassangers() {
        System.out.println("List all passengers that you add: ");
        var pass = customers.get(curUser).getPassengers();
        for (int i = 0; i < pass.size(); i++) {
            System.out.println(pass.get(i).toString());
        }
        printCommandSet();
    }
    public static void listOrders() {
        System.out.println("List all orders: ");
        var pass = customers.get(curUser).getOrders();
        for (int i = 0; i < pass.size(); i++) {
            System.out.println(pass.get(i).toString());
        }
        printCommandSet();
    }
    public static void printFAQ() {
        System.out.println("Some FAQ!");
        printCommandSet();
    }

}
