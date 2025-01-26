import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaBookingSystem {

     static ArrayList<Movie> movies = new ArrayList<>();
    private static String[] dataByParts;
    public CinemaBookingSystem(){
        //getMoviesFromStorage();
    }

    public static void addMovie(Movie movie){
        movies.add(movie);
        addMoviesToStorage();
        System.out.println("Movie added successfully");
    }

    public static Movie findMovie(String name){
        for(Movie s: movies){
            if(s.getName().equals(name)){
                return s;
            }
        }
        System.out.println("The movie does not exist");
        return null;
    }

    public static Movie findMovieByIndex(int index){
        if(index>0 && index<=movies.size()){
            return movies.get(index-1);
        }else{
            return null;
        }
    }

    public static void removeMovie(String name){
        if(findMovie(name) != null) {
            movies.remove(findMovie(name));
            addMoviesToStorage();
            System.out.println("Movie removal successful");
        } else{
            System.out.println("The movie does not exist");}
    }
    public static boolean removeMovie(int movieNumber){
        if(movieNumber>0 && movieNumber<=movies.size()){
            movies.remove(movieNumber-1);
            addMoviesToStorage();
            System.out.println("Movie removal successful");
            return true;}
        else if(movies.size() == 0){
            System.out.println("Movie list is empty");
            return true;
        }else{
            System.out.println("Invalid input");
            return false;}
    }

    public static void showAllMovies(){
        int index = 1;
        for(Movie m: movies){
            System.out.println(index +"."+m.getName());
            index++;
        }
    }

    public static void addMoviesToStorage(){
        File f = new File("movies.txt");
            try {
                FileWriter file = new FileWriter("movies.txt");
                for(Movie m: movies) {
                    file.write(m.toString()+"\n");
                }
                file.close();
            } catch (IOException e) {
                System.out.println("File do not exist");
            }
    }

    public static void getMoviesFromStorage(){
        try {
            File file = new File("movies.txt");
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                dataByParts = data.split("--");
                String name = dataByParts[0];
                double price = Double.parseDouble(dataByParts[1]);
                int dateCount = Integer.parseInt(dataByParts[2]);           // First 2 indexes (0,1)
                int dateIndexCount = 0;
                List<Date> dateList = new ArrayList<>();
                int dateIndex = 3;
                while(dateIndexCount<dateCount){
                    Date d = createDateObject(dateIndex);
                    int timeCount = Integer.parseInt(dataByParts[dateIndex+1]);
                    int timeCountUpdateIndex = 1;
                    while(timeCountUpdateIndex<=timeCount){
                        Time t = createTimeObject(((dateIndex+1)+timeCountUpdateIndex));
                        d.addTime(t);
                        timeCountUpdateIndex++;
                        }
                    dateList.add(d);
                    dateIndex += (timeCount + 2);
                    dateIndexCount++;
                    }
                Movie m = new Movie(name);
                m.addDateList(dateList);
                m.addPrice(price);
                movies.add(m);
                }
            read.close();
            }
            catch (FileNotFoundException e){
                System.out.println("An error occurred.");
            }
    }

    private static Date createDateObject(int dateIndex){
        String[] dateParts = dataByParts[dateIndex].split("/");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        Date d = new Date(day,month,year);

        return d;
    }

    private static Time createTimeObject(int totalIndex){
        String[] timeParts1 = dataByParts[totalIndex].split(":");
        String[] timeParts2 = timeParts1[1].split("~");
        int hour = Integer.parseInt(timeParts1[0]);
        int minutes = Integer.parseInt(timeParts2[0]);
        Time t = new Time(hour,minutes);
        String[] timeParts3 = timeParts2[1].split("@");
        String[] timeParts4 = timeParts3[1].split("_");
        int numberOfRows = Integer.parseInt(timeParts4[0]);
        int numberOfColumns = Integer.parseInt((timeParts4[1]));
        t.addScreen(numberOfColumns,numberOfRows);
        int index = 2;
        while(index<timeParts3.length){
            String[] timeParts5 = timeParts3[index].split("#");
            String[] seatParts1 = timeParts5[0].split("__");
            int rowNumber = Integer.parseInt(seatParts1[1]);
            int columnNumber = Integer.parseInt(seatParts1[0]);
            String username1 = timeParts5[1];
            String username2 = username1.trim();
            if(username2.equals("999")){
                t.screen.initialiseSeat(columnNumber,rowNumber);
            }else{
                t.screen.initialiseSeat(columnNumber,rowNumber,username2);
            }

            index++;
        }

        return t;
    }

}
