import java.util.ArrayList;
import java.util.List;

public class Date {

    private int day;
    private int month;
    private int year;
    private List<Time> times;
    private int timeCount;

    public Date(int day,int month,int year){
        this.day = day;
        this.month = month;
        this.year = year;
        this.times = new ArrayList<>();
    }

    public void addTime(Time time){
        times.add(time);
    }

    public List<Time> getTimes(){
        return times;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public String toString1(){
        return String.format("%02d/%02d/%02d", day, month, year);
    }

    @Override
    public String toString(){
        addTimeCount();
        String s = String.format("%02d/%02d/%02d", day, month, year) +"--"+ timeCount;
        for(Time t: times){
            String sTemp = s +"--"+ t;
            s = sTemp;
        }

        return s;
    }

    public void addTimeCount(){
        timeCount = times.size();
    }

    public int getTimeCount(){
        return timeCount;
    }
}
