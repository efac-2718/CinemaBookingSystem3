import java.util.Scanner;

public class MainConsole {

    public static void main(String[] args) {
        System.out.println("\n********* Welcome to the Cinema Booking System *********");
        System.out.println("Enter your role: \n\t1.Admin\n\t2.User");
        Scanner read = new Scanner(System.in);
        int c = read.nextInt();

        if(c == 1){
            CinemaBookingSystem.getMoviesFromStorage();
            Console c1 = new Console();
        }

        if(c == 2){
            UserConsole u1 = new UserConsole();
            if (UserConsole.state){
                MainProcedure mp = new MainProcedure();
            }
        }
    }
}