import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        CinemaBookingSystem.getMoviesFromStorage();
        System.out.println("\n********* Welcome to the Cinema Booking System *********");
        System.out.println("Enter your role: \n\t1.Admin\n\t2.User");
        System.out.print("Your choice: ");
        Scanner read = new Scanner(System.in);
        int c = read.nextInt();

        if(c == 1){
            Admin c1 = new Admin();
        }

        if(c == 2){
            User u1 = new User();
            if (User.state){
                MainProcedure mp = new MainProcedure(User.getUserID());
            }
        }
    }
}