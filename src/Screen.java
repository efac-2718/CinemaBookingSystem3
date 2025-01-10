public class Screen extends Theater{

    private int numberOfColumns;
    private int numberOfRows;
    private Seat[][] seats;
    private double price;


    public Screen(String name,String location, int numberOfScreens,double price,int numberOfColumns,int numberOfRows){
        super(name,location,numberOfScreens);
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.seats = new Seat[numberOfColumns][numberOfRows];
        this.price = price;
    }

    public Seat findSeatByNumber(int column,int row){
            return seats[column][row];
    }

    public void showAllSeatsWithStatus(){

        int columnIndex = 0;
        int rowIndex = 0;
        for(Seat[] seat: seats){
            System.out.print("Seat Number: "+ columnIndex);
            for(Seat s: seat) {
                System.out.print(rowIndex);
                if(s.getStatus()){
                    System.out.println(": Booked");
                }else{
                    System.out.println(": Not Booked");
                }
                rowIndex++;
            }
            columnIndex++;
        }
    }

    public void showAllFreeSeats(){
        int columnIndex = 0;
        int rowIndex = 0;
        for(Seat[] seat: seats){
            for(Seat s: seat) {
                if(!s.getStatus()) {
                    System.out.println("Seat Number: " + columnIndex + rowIndex);
                }
                rowIndex++;
            }
            columnIndex++;
        }
    }

    public double getPrice(){
        return price;
    }
}
