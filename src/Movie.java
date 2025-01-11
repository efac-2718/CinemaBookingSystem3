import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String name;
    private List<Date> dates;
    private Theater screen;
    private int dateCount;
    int datePrintingCount;
    int timePrintingCount;
    int[][] printingPosition;

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


    public Theater getScreen(){
        return screen;
    }

    public double getPrice(){
        Screen screen1 = (Screen) getScreen();
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
        datePrintingCount = 0;
        for(Date d: dates){
            timePrintingCount = 0;
            for(Time t: d.getTimes()){
                System.out.println(index+"."+d.toString1()+" : "+t);
                timePrintingCount++;
                printingPosition[datePrintingCount][timePrintingCount] = index;
                index++;
            }
            datePrintingCount++;
        }
    }

    public void addDateCount(){
        dateCount = dates.size();
    }

    public int getDateCount(){
        return dateCount;
    }

    public int[] getTimeObject(int n){

        int[] index;
        int[] index0 = new int[]{0,0};
        int index1 = 0;
        for(int[] i:printingPosition){
            int index2 = 0;
            for(int ii:i){
                if(ii==n){
                    index = new int[]{index1,index2};
                    return index;
                }
                index2++;
            }
            index1++;
        }
        return index0;
    }


}
