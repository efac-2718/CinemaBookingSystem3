import java.util.ArrayList;
import java.util.Scanner;

public class Authenticator {

    static String userID;

    static ArrayList<String> details = new ArrayList<>();

    public static ArrayList<String> getUserInfo(){
        System.out.print("Enter the user ID: ");
        Scanner read = new Scanner(System.in);
        userID = read.next();
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

    public static String getUserID() {
        return userID;
    }
}
