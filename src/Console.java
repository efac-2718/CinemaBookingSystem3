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
        System.out.println("Enter your role: \n\t1.Admin\n\t2.User");
        System.out.print("Your choice: ");
        Scanner read = new Scanner(System.in);
        int c = read.nextInt();

        if(c == 1){
            admin();
        }

        if(c == 2){
            user();
            if (state){
                mainProcedure(userID);
            }
        }
    }

    public void admin(){

        System.out.println("\n1.Add new movie");
        System.out.println("2.Show list of All movies");
        System.out.println("3.Remove movie");

        Scanner read = new Scanner(System.in);

        System.out.print("Enter the number of your choice: ");
        int c = read.nextInt();
        read.nextLine();

        if(c == 1){
            System.out.print("\nEnter the name of the movie: ");
            String name = read.nextLine();
            movieAddingProcedure();
            Movie m = new Movie(name);
            m.addDateList(dates);
            m.addPrice(price);
            CinemaBookingSystem.addMovie(m);
        }

        if(c == 2){
            CinemaBookingSystem.showAllMovies();
        }

        if(c == 3){
            System.out.println("Enter the name of the movie: ");
            String name = read.next();
            CinemaBookingSystem.removeMovie(name);
        }

    }

    public void movieAddingProcedure(){
        Scanner read = new Scanner(System.in);
        System.out.println("When will the movie start screening");
        System.out.print("Date: ");
        int date = read.nextInt();
        System.out.print("Month: ");
        int month = read.nextInt();
        System.out.print("Year: ");
        int year = read.nextInt();
        startDate.set(year,month-1,date);
        numberOfColumns = numberOfColumns();
        numberOfRows = numberOfRows();
        price = price();
        System.out.print("Enter the number of days the movie will be screening: ");
        numberOfDays = read.nextInt();
        dates = addTimesToTheDateList(listOfDatesForInstances(createListOfintances()));
        System.out.println("Successfully initialised dates and times");

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
        Scanner read = new Scanner(System.in);
        System.out.println("1.Sign in\n2.Login");
        System.out.print("Your choice: ");
        int c1 = read.nextInt();
        if(c1 == 1){
            Login l = Login.addUser();
            l.addUserToStorage(l);
        }
        if(c1 == 2){
            getUserInfo();
            userID = userID1;
            if(authenticate(details)){
                System.out.println("Login successful");
                state = true;
            }
            else{
                System.out.println("Username or password incorrect. Login unsuccessful");
                state = false;
            }

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
            CinemaBookingSystem.addMoviesToStorage();
            System.out.println("Reserved seat number: "+seatNumber);
            System.out.print("Do you want to reserve another seat?(N/Y): ");
            s1 = read.next();

        }
        System.out.println("Seat(/s) reservation successful");

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
        System.out.print("Enter the user ID: ");
        Scanner read = new Scanner(System.in);
        userID1 = read.next();
        details = Login.getUserInfo(userID1);

        return details;
    }

    public static boolean authenticate(ArrayList<String> e){

        System.out.print("Enter password: ");
        Scanner read = new Scanner(System.in);
        String password1 = read.next();
        String password2 = password1.trim();
        if(e.get(4).equals(password2)){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
        Console c1 = new Console();
    }

}