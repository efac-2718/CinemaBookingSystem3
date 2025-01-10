import java.util.Scanner;

public class MainProcedure {
    public MainProcedure(){
        CinemaBookingSystem program2 = new CinemaBookingSystem();

        System.out.println("Here is a list of all movies:");
        program2.showAllMovies();
        System.out.print("Select movie(enter number): ");
        Scanner read = new Scanner(System.in);
        int movieNumber = read.nextInt();
        System.out.println("Here is a list of dates and times the movie will screen");
        (program2.findMovieByIndex(movieNumber)).toString2();
    }
}
