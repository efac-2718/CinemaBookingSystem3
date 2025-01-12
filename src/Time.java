public class Time {

    private int hour;
    private int minutes;
    Screen screen;

    public Time(int hour,int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }

    public void addScreen(int numberOfColumns, int numberOfRows){
        this.screen = new Screen(numberOfColumns,numberOfRows);

    }

    public void initialiseScreenObject(){

    }

    public int getHour(){
        return hour;
    }

    public int getMinutes(){
        return minutes;
    }

    @Override
    public String toString(){
        String s = String.format("%02d:%02d", hour,minutes)+"~"+screen;
        return s;
    }

    public String toString2(){
        String s = String.format("%02d:%02d", hour,minutes);
        return s;
    }

    public Theater getScreen(){
        return screen;
    }
}
