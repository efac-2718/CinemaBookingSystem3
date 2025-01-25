import java.util.Calendar;

public class Seat {

    int column;
    int row;
    boolean booked;
    Login login;

    public Seat (int column,int row){
        this.column = column;
        this.row = row;
        this.booked = false;
        this.login = Login.getSampleLoginObject();
    }

    public Seat (int column, int row,String userID){
        this.column = column;
        this.row = row;
        this.booked = true;
        this.login = Login.getLoginObjectFromStorage(userID);
    }

    public void reserveSeat(Login user){
            booked = true;
            login = user;
    }

    public boolean getStatus(){
        return booked;
    }

    public String toString(){
            return "#"+login.userID;
    }

    /*public String<> getDetailsToPrintReceipt(String name){
        Movie m = CinemaBookingSystem.findMovie(name);
        System.out.println("********* Receipt *********");
        System.out.println("Name: "+login.name);
        System.out.println("User ID: "+login.userID);
        System.out.println("Movie : "+m.getName());
        System.out.println("Price: "+ m.getPrice());
        System.out.println("****************************");
        System.out.println("Receipt printed on :"+ Calendar.getInstance());
        System.out.println("****************************");
    }*/
}
