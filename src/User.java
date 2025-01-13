import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    static String userID;
    static boolean state = false;
    int[] position;
    Movie movie;
    static String userID1;
    static ArrayList<String> details = new ArrayList<>();

    public User(){
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


}

