import java.util.List;
import java.util.Scanner;

public class MainProcedure {

    int[] position;
    Movie movie;

    public MainProcedure(int userID){

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
}
