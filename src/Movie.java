import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String name;
    private List<Date> dates;
    private Screen screen = new Screen("K-zone","Kandana",4, 2000,10,10);
    private int dateCount;
    public Movie(String name){
        this.name = name;
        this.dates = new ArrayList<>();

    }

    public void addDate(Date date){
        dates.add(date);
    }

    public void addDateList(List<Date> d){
        dates = d;
    }

    public List<Date> getDate(){
        return dates ;
    }

    public String getName(){
        return name;
    }


    public Screen getScreen(){
        return screen;
    }

    public double getPrice(){
        Screen screen1 = getScreen();
        return screen1.getPrice();
    }

    @Override
    public String toString(){

        addDateCount();
        String s = getName()+"--"+dateCount;
        for(Date d: dates){
            String sTemp = s + "--" + d;
            s = sTemp;
        }
        return s;
    }

    public void toString2(){

        int index = 1;
        for(Date d: dates){
            for(Time t: d.getTimes()){
                System.out.println(index+"."+d+" : "+t);
                index++;
            }
        }
    }

    public void addDateCount(){
        dateCount = dates.size();
    }

    public int getDateCount(){
        return dateCount;
    }
}
