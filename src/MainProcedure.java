import java.util.List;
import java.util.Scanner;

public class MainProcedure {

    int[] position;
    Movie movie;

    public MainProcedure(){

        CinemaBookingSystem.getMoviesFromStorage();
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
        System.out.print("Enter the seat number you wish to reserve: ");
        int seatNumber = read.nextInt();

    }

    public void findAndDisplaySeats(){
        List<Date> dates = movie.getDate();
        List<Time> times = (dates.get(position[0])).getTimes();
        Screen requiredScreen = (Screen) times.get(position[1]).getScreen();
        requiredScreen.showAllFreeSeats();
    }

    public Seat findRequestedSeat(int seatNumber){
        if(seatNumber>=0 && seatNumber<100){
            int column = seatNumber/10;
            int row = seatNumber%10;
            List<Date> dates = movie.getDate();
            List<Time> times = (dates.get(position[0])).getTimes();
            Screen requiredScreen = (Screen) times.get(position[1]).getScreen();
            return requiredScreen.findSeatByNumber(column,row);
        }
        if(seatNumber>=100 && seatNumber<1000){
            int column = seatNumber/100;
            int row = seatNumber%100;
            List<Date> dates = movie.getDate();
            List<Time> times = (dates.get(position[0])).getTimes();
            Screen requiredScreen = (Screen) times.get(position[1]).getScreen();
            return requiredScreen.findSeatByNumber(column,row);
        }
        if(seatNumber>=)
    }
}
