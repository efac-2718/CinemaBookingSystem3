import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MovieAddingProcedure {

    private int numberOfDays;
    private Calendar startDate = Calendar.getInstance();
    List<Date> dates = new ArrayList<>();
    int numberOfRows;
    int numberOfColumns;
    double price;

    public MovieAddingProcedure(){
        Scanner read = new Scanner(System.in);
        System.out.println("When will the movie start screening");
        System.out.print("Date: ");
        int date = read.nextInt();
        System.out.print("Month: ");
        int month = read.nextInt();
        System.out.print("Year: ");
        int year = read.nextInt();
        startDate.set(year,month-1,date);
        numberOfColumns = numberOfColumns();
        numberOfRows = numberOfRows();
        price = price();
        System.out.print("Enter the number of days the movie will be screening: ");
        numberOfDays = read.nextInt();
        dates = addTimesToTheDateList(listOfDatesForInstances(createListOfintances()));
        System.out.println("Successfully initialised dates and times");

    }

    private List<Calendar> createListOfintances(){
        int index = 0;
        List<Calendar> list = new ArrayList<>();
        int amount = 0;
        while(index<numberOfDays){
            Calendar cTemp = (Calendar) startDate.clone();
            cTemp.add(Calendar.DAY_OF_MONTH,amount);
            amount++;
            list.add(cTemp);
            index++;
        }
        return list;
    }

    public List<Date> listOfDatesForInstances(List<Calendar> list){
        List<Date> newList = new ArrayList<>();
        int limit = list.size();
        int index = 0;

        while(index<limit){

            int date = (list.get(index)).get(Calendar.DAY_OF_MONTH);
            int month = (list.get(index)).get(Calendar.MONTH) + 1;
            int year = (list.get(index)).get(Calendar.YEAR);
            Date d = new Date(year,month,date);
            newList.add(d);
            index++;
        }

        return newList;
    }

    public int numberOfColumns(){
        System.out.print("Enter the number of rows in the hall: ");
        Scanner read = new Scanner(System.in);
        int number = read.nextInt();
        return number;
    }

    public int numberOfRows(){
        System.out.print("Enter the number of seats in each row: ");
        Scanner read = new Scanner(System.in);
        int number = read.nextInt();
        return number;
    }

    public double price(){
        System.out.print("Enter the price of the movie: ");
        Scanner read = new Scanner(System.in);
        double price = read.nextInt();
        return price;
    }

    public List<Date> addTimesToTheDateList(List<Date> list){

        List<Date> newList = new ArrayList<>();

        System.out.println("How many times will the movie screen on,");
        for(Date d: list){
            System.out.print(d.toString1()+" :");
            Scanner read = new Scanner(System.in);
            int times = read.nextInt();
            int index = 0;
            while(index<times){
                System.out.println("Enter time "+(index+1)+" , in (hour:minute) format: ");
                Scanner read1 = new Scanner(System.in);
                System.out.print("Hour: ");
                int hour = read1.nextInt();
                System.out.print("Minute: ");
                int minutes = read1.nextInt();
                Time t = new Time(hour,minutes);
                t.addScreen(numberOfColumns,numberOfRows,price);
                d.addTime(t);
                index++;
            }
            newList.add(d);
        }

        return newList;
    }

    public List<Date> getDates(){
        return dates;
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }

}
