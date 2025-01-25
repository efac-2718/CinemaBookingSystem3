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
        System.out.println("Enter your role: \n\t1.Admin\n\t2.User\n\t3.Exit");

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
        while (!(c == 4)) {
            System.out.println("\n1.Add new movie");
            System.out.println("2.Show list of All movies");
            System.out.println("3.Remove movie");
            System.out.println("4.Exit");

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
                            System.out.println("Enter the name of the movie: ");
                            String name = read.next();
                            CinemaBookingSystem.removeMovie(name);

                }else if (c == 4){
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
            System.out.print("Enter the number of days the movie will be screening: ");
            numberOfDays = read.nextInt();
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

    public int numberOfColumns(){
        System.out.print("Enter the number of rows in the hall: ");
        Scanner read = new Scanner(System.in);
        int number = read.nextInt();
        return number;
    }

    public int numberOfRows(){
        System.out.print("Enter the number of seats in each row: ");
        Scanner read = new Scanner(System.in);
        int number = read.nextInt();
        return number;
    }

    public double price(){
        System.out.print("Enter the price of the movie: ");
        Scanner read = new Scanner(System.in);
        double price = read.nextInt();
        return price;
    }

    public List<Date> addTimesToTheDateList(List<Date> list){

        List<Date> newList = new ArrayList<>();

        System.out.println("How many times will the movie screen on,");
        for(Date d: list){
            System.out.print(d.toString1()+" :");
            Scanner read = new Scanner(System.in);
            int times = read.nextInt();
            int index = 0;
            while(index<times){
                System.out.println("Enter time "+(index+1)+" , in (hour:minute) format: ");
                Scanner read1 = new Scanner(System.in);
                System.out.print("Hour: ");
                int hour = read1.nextInt();
                System.out.print("Minute: ");
                int minutes = read1.nextInt();
                Time t = new Time(hour,minutes);
                t.addScreen(numberOfColumns,numberOfRows);
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
    public void cleanscereen(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
        }
    }
    public static String getUserID() {
        return userID;
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
            fileWriter.write("\n"+movieIndex+"-"+seatNumber);
        } catch (IOException e) {
            System.out.println("Internal error");
        }
    }


    public static void main(String[] args){
        Console c1 = new Console();
    }

}