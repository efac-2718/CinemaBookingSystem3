public class Time {

    private int hour;
    private int minutes;

    public Time(int hour,int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour(){
        return hour;
    }

    public int getMinutes(){
        return minutes;
    }

    @Override
    public String toString(){
        String s = String.format("%02d:%02d", hour,minutes);
        return s;
    }
}
