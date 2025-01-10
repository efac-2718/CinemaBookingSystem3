import java.util.Scanner;

public class UserConsole {

    static boolean state = false;
    public UserConsole(){
        Scanner read = new Scanner(System.in);
        System.out.println("1.Sign in\n2.Login");
        int c1 = read.nextInt();
        if(c1 == 1){
            Login l = Login.addUser();
            l.addUserToStorage(l);
        }
        if(c1 == 2){
            Authenticator.getUserInfo();
            if(Authenticator.authenticate(Authenticator.details)){
                System.out.println("Login successful");
                state = true;
            }
            else{
                System.out.println("Username or password incorrect. Login unseccessful");
                state = false;
            }

        }
    }
}
