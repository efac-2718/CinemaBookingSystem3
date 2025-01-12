import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    String name;
    private String telephoneNumber;
    int userID;
    private String emailAddress;
    private String password;
    private static int count = loadCounter();

    public Login(){
        this.name = null;
        this.telephoneNumber = null;
        this.userID = 1000+count;
        this.password = null;
        this.emailAddress = null;
    }

    public Login(String name,String telephoneNumber,int userID,String password, String emailAddress){
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.userID = 1000+count;
        this.password = password;
        this.emailAddress = emailAddress;
        count++;
        saveCounter(count);
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
            System.out.println("An error occurred. No such file Exist. Enter correct username");
        }

        return list;
    }

    public static Login getLoginObjectFromStorage(int userID){
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

    private static int loadCounter() {
        File file = new File("counter.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error reading counter file. Starting from 0.");
            }
        }
        return 0;
    }

    private static void saveCounter(int counter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("counter.txt"))) {
            writer.write(Integer.toString(counter));
        } catch (IOException e) {
            System.err.println("Error saving counter to file.");
        }
    }

}
