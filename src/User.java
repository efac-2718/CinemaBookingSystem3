import java.util.Scanner;

public class User {

    static String userID;

    static boolean state = false;

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
            Authenticator.getUserInfo();
            userID = Authenticator.getUserID();
            if(Authenticator.authenticate(Authenticator.details)){
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
}
