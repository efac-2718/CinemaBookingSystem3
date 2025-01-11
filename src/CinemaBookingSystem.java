import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaBookingSystem {

    private static ArrayList<Movie> movies = new ArrayList<>();
    private static String[] dataByParts;
    public CinemaBookingSystem(){
        getMoviesFromStorage();
    }

    public static void addMovie(Movie movie){
        movies.add(movie);
        addMoviesToStorage();
        System.out.println("Movie added successfully");
    }

    public static Movie findMovie(String name){
        for(Movie s: movies){
            if(s.getName().equals(name)){
                System.out.println("The movie "+ name + " Exists");
                return s;
            }
        }
        System.out.println("The movie does not exist");
        return null;
    }

    public Movie findMovieByIndex(int index){
        return movies.get(index-1);
    }

    public static void removeMovie(String name){
        movies.remove(findMovie(name));
        addMoviesToStorage();
        System.out.println("Movie removal successful");
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
                int dateCount = Integer.parseInt(dataByParts[1]);           // First 2 indexes (0,1)
                int dateIndexCount = 0;
                List<Date> dateList = new ArrayList<>();
                int dateIndex = 2;
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
        String[] timeParts = dataByParts[totalIndex].split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        Time t = new Time(hour,minutes);

        return t;
    }

}
