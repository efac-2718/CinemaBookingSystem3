public class Screen extends Theater{

    private int uniqueIdentifier;
    private static int  count = 0;
    private int numberOfColumns;
    private int numberOfRows;
    private Seat[][] seats;
    private double price;


    public Screen(double price,int numberOfColumns,int numberOfRows){
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.seats = new Seat[numberOfColumns][numberOfRows];
        this.price = price;
        this.uniqueIdentifier = 100+count;
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

    public int getUniqueIdentifier(){
        return uniqueIdentifier;
    }

    public String toString(){
        String s = uniqueIdentifier+"@"+numberOfRows+"_"+numberOfColumns;
        int index1 = 0;
        for(Seat[] column: seats){
            int index2 = 0;
            for(Seat row: column){
                String seatNumber = "@"+index1+"__"+index2+"#"+row;
                s = s + seatNumber;
                index2++;
            }
            index1++;
        }
        return s;
    }

}
