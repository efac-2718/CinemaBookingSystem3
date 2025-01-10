import java.util.ArrayList;
import java.util.Scanner;

public class Authenticator {

    static ArrayList<String> details = new ArrayList<>();

    public static ArrayList<String> getUserInfo(){

        System.out.println("Enter the user ID: ");
        Scanner read = new Scanner(System.in);
        int userID = read.nextInt();
        details = Login.getUserInfo(userID);

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
