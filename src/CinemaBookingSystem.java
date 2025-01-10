import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaBookingSystem {

    private static ArrayList<Movie> movies = new ArrayList<>();
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
        return movies.get(index+1);
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
        for(Movie m: movies){
            try {
                FileWriter file = new FileWriter("movies.txt");
                file.write(m.toString());
                file.close();
            } catch (IOException e) {
                System.out.println("File do not exist");
            }
        }
    }

    public static void getMoviesFromStorage(){
        try {
            File file = new File("movies.txt");
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                String[] databyparts = data.split("--");
                String name = databyparts[0];
                int dateCount = Integer.parseInt(databyparts[1]);
                int index = 0;
                List<Date> dateList = new ArrayList<>();
                int dateIndex = 2;
                while(index<dateCount){
                    String[] dateParts = databyparts[dateIndex].split("/");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);
                    Date d = new Date(day,month,year);
                    int timeCount = Integer.parseInt(databyparts[dateIndex+1]);
                    dateIndex = dateIndex + timeCount + 1;
                    int totalIndex = 4;
                    totalIndex = totalIndex+timeCount+2;
                    int index1 = 0;
                    while(index1<timeCount){
                        String[] timeParts = databyparts[totalIndex].split(":");
                        int hour = Integer.parseInt(timeParts[0]);
                        int minutes = Integer.parseInt(timeParts[1]);
                        Time t = new Time(hour,minutes);
                        d.addTime(t);
                        index1++;
                        }
                    index++;
                    dateList.add(d);
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

    public static void main(String[] args){
        CinemaBookingSystem.getMoviesFromStorage();
    }

}
