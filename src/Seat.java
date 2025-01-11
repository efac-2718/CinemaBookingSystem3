import java.util.Calendar;

public class Seat {

    boolean booked;
    Login login;

    public Seat (){
        this.booked = false;
        this.login = null;
    }

    public Seat(Login userID){
        this.booked = true;
        this.login = userID;
    }

    public void reserveSeat(Login user){
        if(booked){
            System.out.println("Error. Seat already booked");
        }
        else{
            booked = true;
            login = user;
        }
    }

    public boolean getStatus(){
        return booked;
    }

    public String toString(){
            return "#"+login.userID;
    }

    public void printReceipt(String name){
        Movie m = CinemaBookingSystem.findMovie(name);
        System.out.println("********* Receipt *********");
        System.out.println("Name: "+login.name);
        System.out.println("User ID: "+login.userID);
        System.out.println("Movie : "+m.getName());
        System.out.println("Price: ");
        System.out.println("Date of Screening :");
        System.out.println("Time of screening :");
        System.out.println("Theater :"+ m.getPrice());
        System.out.println("****************************");
        System.out.println("Receipt printed on :"+ Calendar.getInstance());
        System.out.println("****************************");
    }
}
