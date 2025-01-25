import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login implements Authenticate{

    String name;
    private String telephoneNumber;
    String userID;
    private String emailAddress;
    private String password;

    public Login(){
        this.name = "NoUser";
        this.telephoneNumber = "0000000000";
        this.userID = "999";
        this.password = "123";
        this.emailAddress = "email";
    }

    public Login(String name,String telephoneNumber,String userID,String password, String emailAddress){
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.userID= userID;
        this.password = password;
        this.emailAddress = emailAddress;
    }
    public static Login addUser() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = read.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid name:");
            name = read.nextLine();
        }

        System.out.print("Enter telephone number: ");
        String tnumber = read.nextLine();
        while (!isValidPhoneNumber(tnumber)) {
            System.out.println("Invalid telephone number. Please enter a valid number (only digits, 10-15 characters):");
            tnumber = read.nextLine();
        }

        System.out.print("Enter email: ");
        String email = read.nextLine();
        while (!isValidEmail(email)) {
            System.out.println("Invalid email address. Please enter a valid email:");
            email = read.nextLine();
        }

        System.out.println("Enter userID: ");
        String userID = read.next();
        while (userID.trim().isEmpty()) {
            System.out.println("UserID cannot be empty. Please enter a valid userID:");
            userID = read.next();
        }

        System.out.print("Enter Password: ");
        String password = read.next();
        while (password.trim().isEmpty()) {
            System.out.println("Password cannot be empty. Please enter a valid password:");
            password = read.next();
        }

        Login user = new Login(name, tnumber, userID, password, email);
        return user;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,15}"); // length between 10 and 15
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }


    public void addUserToStorage(Login user){
        String user1 = user.userID + ".txt";
        File f = new File(user1);
        try {
            FileWriter file = new FileWriter(user1);
            file.write(name + "\n" + telephoneNumber + "\n" + userID + "\n" + emailAddress + "\n" + password);
            System.out.println("Successfully added user");
            file.close();
        }
        catch (IOException e){
            System.out.println("Error Writing to file\nUser registration unsuccessful");
        }
    }

    public static ArrayList<String> getUserInfo(String userID){

        ArrayList<String> list = new ArrayList<>();
        try {
            String s = userID+".txt";
            File file = new File(s);
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                data = data.trim();
                list.add(data);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wrong user ID or User Not Existed");

        }

        return list;
    }

    public static Login getSampleLoginObject(){
        Login l = new Login();
        return l;
    }

    public static Login getLoginObjectFromStorage(String userID){
        ArrayList<String> list = new ArrayList<>();
        list = getUserInfo(userID);
        Login loginObject = new Login(list.get(0),list.get(1),userID,list.get(4), list.get(3));

        return loginObject;
    }

    public static void removeUser(String user){
        String user1 = user +".txt";
        File myObj = new File(user1);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public boolean LogAdmin(String AdName, String Pass){
        boolean reVal;
        String[] admins = Admin();
        String aName = admins[0];
        String Passw  = admins[1];
        if ((aName.equals(AdName)&&(aName.equals(Passw)))){
            reVal = true;

    } else{
            reVal =false;
        }

    return reVal;

    }
    public static String[] Admin() {
        ArrayList<String> list = new ArrayList<>();
        try {
            String s = "admin.txt";
            File file = new File(s);
            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {
                String data = read.nextLine().trim();
                list.add(data);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
        }


        return list.toArray(new String[0]);
    }

    public boolean authenticate(ArrayList<String> e){

        System.out.print("Enter password: ");
        Scanner read = new Scanner(System.in);
        String password1 = read.next();
        String password2 = password1.trim();  // remove font spaces
        if(e.get(4).equals(password2)){ // array 5th data is password
            return true;
        }
        else{
            System.out.println("Password incorrect.");

            return false;
        }
    }
}
