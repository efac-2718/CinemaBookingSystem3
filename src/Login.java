import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    String name;
    private String telephoneNumber;
    int userID;
    private String emailAddress;
    private String password;
    private static int count = 0;

    public Login(){
        this.name = null;
        this.telephoneNumber = null;
        this.userID = 0;
        this.password = null;
        this.emailAddress = null;
    }

    public Login(String name,String telephoneNumber,int userID,String password, String emailAddress){
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.userID = userID;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public static Login addUser(){

        Scanner read = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = read.next();
        System.out.print("Enter telephone Number: ");
        String tnumber = read.next();
        System.out.print("Enter email: ");
        String email = read.next();
        int userID = 1000+count;
        count++;
        System.out.println("Your username is "+userID);
        System.out.print("Enter Password:");
        String password = read.next();
        Login user = new Login(name,tnumber,userID,password,email);

        return user;
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

    public static ArrayList<String> getUserInfo(int userID){

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
            System.out.println("An error occurred.");
        }

        return list;
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

}
