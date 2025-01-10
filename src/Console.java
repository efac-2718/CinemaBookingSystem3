import java.util.Scanner;

public class Console {

    public Console(){

        System.out.println("\n1.Add new movie");
        System.out.println("2.Show list of All movies");
        System.out.println("3.Remove movie");

        Scanner read = new Scanner(System.in);

        System.out.print("Enter the number of your choice: ");
        int c = read.nextInt();

        if(c == 1){
            System.out.print("\nEnter the name of the movie: ");
            String name = read.next();
            MovieAddingProcedure am = new MovieAddingProcedure();
            Movie m = new Movie(name);
            m.addDateList(am.dates);
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
}
