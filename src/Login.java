import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

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

    public static Login addUser(){

        Scanner read = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = read.nextLine();
        System.out.print("Enter telephone Number: ");
        String tnumber = read.nextLine();
        System.out.print("Enter email: ");
        String email = read.nextLine();
        System.out.println("Enter userID: ");
        String userID = read.next();
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
            System.out.println("An error occurred. No such file Exist. Enter correct username");
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

}
