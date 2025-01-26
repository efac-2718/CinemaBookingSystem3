import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Console {

    private int numberOfDays;
    private Calendar startDate = Calendar.getInstance();
    List<Date> dates = new ArrayList<>();
    int numberOfRows;
    int numberOfColumns;
    double price;
    static String userID;
    static boolean state = false;
    int[] position;
    Movie movie;
    static String userID1;
    static ArrayList<String> details = new ArrayList<>();

    public Console() {

        CinemaBookingSystem.getMoviesFromStorage();

        System.out.println("\n********* Welcome to the Cinema Booking System *********");
        System.out.println("Enter your role: \n\t1.Admin\n\t2.User\n\t3.Exit\n");

        int c=0;
        do {
            System.out.print("Your choice: ");
            try {
                Scanner read = new Scanner(System.in);

                c = read.nextInt();
            } catch (Exception e) {

            }

            if (c == 1) {
                loginAdmin();
            } else if (c == 2) {
                user();
                if (state) {
                    mainProcedure(userID);
                }
            }else if (c == 3){
                System.out.println("Exiting System, Have a nice day!");
                System.exit(0);
            }else{
                System.out.println("Invalid input");
            }
        }while(!((c == 1)||(c ==2)||(c == 3)));

    }


    public void loginAdmin(){
        String adName;
        String PassW;
        boolean logState;
        do {
            Scanner read = new Scanner(System.in);
            System.out.println("Enter Admin Name");
            adName = read.next();

            System.out.println("Enter Admin Password");
            PassW = read.next();
            Login Ad = new Login();
            logState =(Ad.LogAdmin(adName, PassW));
            if (logState) {
                admin();
            } else {
                System.out.println("Wrong Admin Name Password");
                System.out.println("Enter Valid Data");

            }
        }while(!(logState));
    }

    public void admin() {
        int c = 0;
        while (!(c == 5)) {
            System.out.println("\n1.Add new movie");
            System.out.println("2.Show list of All movies");
            System.out.println("3.Remove movie");
            System.out.println("4.Change admin password");
            System.out.println("5.Exit");

            Scanner read = new Scanner(System.in);
            System.out.print("Enter the number of your choice: ");
            try {
                c = read.nextInt();
                read.nextLine();

                if (c == 1) {
                    System.out.print("\nEnter the name of the movie: ");
                    String name = read.nextLine();
                    movieAddingProcedure();
                    Movie m = new Movie(name);
                    m.addDateList(dates);
                    m.addPrice(price);
                    CinemaBookingSystem.addMovie(m);
                }
                else if (c == 2) {
                    System.out.println("List of All movies");
                    CinemaBookingSystem.showAllMovies();

                }
                else if (c == 3) {
                    System.out.println("Remove Movie");
                    CinemaBookingSystem.showAllMovies();
                    System.out.print("Enter the number of the movie you want to select: ");
                    int movieNumber = read.nextInt();
                    while(!CinemaBookingSystem.removeMovie(movieNumber)){
                        System.out.println("Invalid Input");
                    }


                }else if(c == 4){
                    Login.changeAdminPassword();
                }else if (c == 5){
                    System.out.println("Closing Application .....");
                }
                else{
                    System.out.println("Invalid input");
                }

            } catch (Exception e) {
                System.out.println("Valid Input");
            }

        }
    }


    public void movieAddingProcedure(){
        Scanner read = new Scanner(System.in);
        System.out.println("When will the movie start screening");
        int year = 0000;
        int month = 00;
        int date = 0;
        try{
            boolean correctdate = false;
            do {
                System.out.print("Year: ");
                year = read.nextInt();

                // Validate year range
                if (year < 2024 || year > 2100) {
                    System.out.println("Enter Valid Year");
                } else {
                    System.out.print("Month: ");
                    month = read.nextInt();

                    if (month < 1 || month > 12) {
                        System.out.println("Enter Valid Month");
                    } else {
                        System.out.print("Date: ");
                        date = read.nextInt();

                        if (date < 1 || date > 31) {
                            System.out.println("Enter Valid Date");
                        } else {
                            if (month == 4 || month == 6 || month == 9 || month == 11) {
                                if (date > 30) {
                                    System.out.println("Invalid Date for the given Month");
                                } else {
                                    correctdate = true;
                                }
                            }
                            else if (month == 2) {
                                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                                    if (date > 29) {
                                        System.out.println("Invalid Date for February in a Leap Year");
                                    } else {
                                        correctdate = true;
                                    }
                                } else {
                                    if (date > 28) {
                                        System.out.println("Invalid Date for February in a Non-Leap Year");
                                    } else {
                                        correctdate = true;
                                    }
                                }
                            }
                            else {
                                correctdate = true;

                            }
                        }
                    }
                }
            } while (!correctdate);
            startDate.set(year,month-1,date);
            numberOfColumns = numberOfColumns();
            numberOfRows = numberOfRows();
            price = price();
            numberOfDays = screeningnumberOfDays();
            dates = addTimesToTheDateList(listOfDatesForInstances(createListOfintances()));
            System.out.println("Successfully initialised dates and times");
        } catch (Exception e) {
            System.out.println("Enter valid data");
        }
    }

    private List<Calendar> createListOfintances(){
        int index = 0;
        List<Calendar> list = new ArrayList<>();
        int amount = 0;
        while(index<numberOfDays){
            Calendar cTemp = (Calendar) startDate.clone();
            cTemp.add(Calendar.DAY_OF_MONTH,amount);
            amount++;
            list.add(cTemp);
            index++;
        }
        return list;
    }

    public List<Date> listOfDatesForInstances(List<Calendar> list){
        List<Date> newList = new ArrayList<>();
        int limit = list.size();
        int index = 0;

        while(index<limit){

            int date = (list.get(index)).get(Calendar.DAY_OF_MONTH);
            int month = (list.get(index)).get(Calendar.MONTH) + 1;
            int year = (list.get(index)).get(Calendar.YEAR);
            Date d = new Date(year,month,date);
            newList.add(d);
            index++;
        }

        return newList;
    }
    public int screeningnumberOfDays(){
        int noScreening = getnubData("days the movie will be screening",70);
        return noScreening;

    }
    public int numberOfColumns() {
        int number = getnubData("Columns in the hall",999);
        return number;
    }
    public int numberOfRows(){
        int number = getnubData("Row in the hall",999);
        return number;
    }
    public int getnubData(String Input, int endNum){
        Scanner read = new Scanner(System.in);
        int number = -1;
        while (true) {
            System.out.printf("Enter the number of %s (1-%d): ",Input,endNum);
            if (read.hasNextInt()) {
                number = read.nextInt();
                if (number >= 1 && number <= endNum) {
                    break;
                } else {
                    System.out.printf("Invalid input. Please enter a number between 1 and %d.",endNum);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                read.next();
            }
        }
        return number;
    }


    public double price(){
        Scanner read = new Scanner(System.in);
        double priceM = 0.00;
        while (true) {
            System.out.print("Enter the price of the movie:  ");
            if (read.hasNextDouble()) {
                priceM = read.nextDouble();
                if (priceM >= 1 && priceM <= 99999) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a Price, Valid price range: 1 and 99999.");
                }
            } else {
                System.out.println("Invalid input. Enter Price Correctly");
                read.next();
            }
        }
        return priceM;
    }



    public List<Date> addTimesToTheDateList(List<Date> list) {
        List<Date> newList = new ArrayList<>();
        Scanner read = new Scanner(System.in);

        System.out.println("How many times will the movie screen on?");
        for (Date d : list) {
            System.out.print(d.toString() + " : ");
            int times;

            while (true) {
                if (read.hasNextInt()) {
                    times = read.nextInt();
                    if (times > 0) {
                        break; // check input is grater than zro and then true exit the loop
                    } else {
                        System.out.println("Invalid input. Please enter a positive integer for the number of times.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    read.next();
                }
            }

            int index = 0;
            while (index < times) {
                System.out.println("Enter time " + (index + 1) + " in (hour:minute) format: ");
                int hour = -1, minutes = -1;
                while (true) {
                    System.out.print("Hour: ");
                    if (read.hasNextInt()) {
                        hour = read.nextInt();
                        if (hour >= 0 && hour <= 6) {
                            break; // check correct hour
                        } else {
                            System.out.println("Invalid input. Hour must be between 0 and 6.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter an integer for hour.");
                        read.next();
                    }
                }


                while (true) {
                    System.out.print("Minute: ");
                    if (read.hasNextInt()) {
                        minutes = read.nextInt();
                        if (minutes >= 0 && minutes <= 59) {
                            break;
                        } else {
                            System.out.println("Invalid input. Minutes must be between 0 and 59.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter an integer for minutes.");
                        read.next();
                    }
                }

                Time t = new Time(hour, minutes);
                d.addTime(t);
                index++;
            }
            newList.add(d);
        }

        return newList;
    }

    public List<Date> getDates(){
        return dates;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }

    public void user(){
        int c1 =0;outer:
        do {
            Scanner read = new Scanner(System.in);
            System.out.println("1.Sign in\n2.Login \n3.Back");
            System.out.print("Your choice: ");
            c1 = read.nextInt();
            if (c1 == 1) {
                Login l = Login.addUser();
                l.addUserToStorage(l);
                System.out.println("Please log into Your Account");
                c1 = 0;
//                details = Login.getUserInfo(l.userID);
//                state = true;



            }else if (c1 == 2) {
                getUserInfo();
                userID = userID1;
                Authenticate a = new Login(details.get(0),details.get(1),details.get(2),details.get(4),details.get(3));
                if (a.authenticate(details)) {
                    System.out.println("Login successful");
                    state = true;
                } else {
                    System.out.println("Login unsuccessful");
                    state = false;
                    user();
                }

            } else if (c1 == 3) {
                System.out.println("Going to Back");
                // cleanscereen();    //for clean screen
                new Console();
            } else{
                System.out.println("Enter Valid Input");
            }
        }while (!((c1 == 1)||(c1 == 2)||(c1 ==3)));
    }


    public void mainProcedure(String userID){

        System.out.println("Here is a list of all movies:");
        CinemaBookingSystem.showAllMovies();
        System.out.print("Select movie(enter number): ");
        Scanner read = new Scanner(System.in);
        int movieNumber = read.nextInt();
        System.out.println("Here is a list of dates and times the movie will screen");
        movie = CinemaBookingSystem.findMovieByIndex(movieNumber);
        movie.toString2();
        System.out.print("Your choice: ");
        int occurence = read.nextInt();
        position = movie.getTimeObject(occurence);
        System.out.println("Here is a list of all free seats");
        findAndDisplaySeats();
        String s1 = "yes";
        while(!((s1.equalsIgnoreCase("no"))|s1.equalsIgnoreCase("n"))){
            System.out.print("Enter the seat number you wish to reserve: ");
            read.nextLine();
            int seatNumber = read.nextInt();
            Login l = Login.getLoginObjectFromStorage(userID);
            findAndReserveRequestedSeat(seatNumber,l);
            appendSeatNumberToLoginFile(seatNumber,userID,movieNumber);
            CinemaBookingSystem.addMoviesToStorage();
            System.out.println("Reserved seat number: "+seatNumber);
            System.out.print("Do you want to reserve another seat?(N/Y): ");
            s1 = read.next();

        }

        Login l = Login.getLoginObjectFromStorage(userID);
        System.out.println("Seat(/s) reservation successful\n");
        System.out.println("Here is your receipt: ");
        l.printReceipt();

    }

    public void findAndDisplaySeats(){
        List<Date> dates = movie.getDate();
        List<Time> times = (dates.get(position[0])).getTimes();
        Screen requiredScreen = (Screen) times.get(position[1]).getScreen();
        requiredScreen.showAllFreeSeats();
    }

    public void findAndReserveRequestedSeat(int seatNumber,Login l) {

        List<Date> dates = movie.getDate();
        List<Time> times = (dates.get(position[0])).getTimes();
        Screen requiredScreen = (Screen) times.get(position[1]).getScreen();
        requiredScreen.reserveTheSeatAtRequiredPosition(seatNumber,l);
    }

    public static ArrayList<String> getUserInfo(){
        do{
            System.out.print("Enter the user ID: ");
            Scanner read = new Scanner(System.in);
            userID1 = read.next();
            details = Login.getUserInfo(userID1);
            if (details.isEmpty()){ //check return
                System.out.println("Enter valid UserID");
            }

        }while((details.isEmpty()));
        return details;
    }

    public void appendSeatNumberToLoginFile(int seatNumber,String name,int movieIndex){
        try (FileWriter fileWriter = new FileWriter(name+".txt", true)) {
            fileWriter.write("\n"+movieIndex+"-"+position[0]+"-"+position[1]+"-"+seatNumber);
        } catch (IOException e) {
            System.out.println("Internal error");
        }
    }


    public static void main(String[] args){
        Console c1 = new Console();
    }

}