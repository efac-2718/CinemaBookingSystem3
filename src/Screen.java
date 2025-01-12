public class Screen extends Theater{

    private int numberOfColumns;
    private int numberOfRows;
    private Seat[][] seats;
    int index = 0;


    public Screen(int numberOfColumns,int numberOfRows) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.seats = new Seat[numberOfColumns][numberOfRows];

    }
    public void initialiseSeat(int columnNumber, int rowNumber){
        seats[columnNumber][rowNumber] = new Seat(columnNumber, rowNumber);
    }
    public void initialiseSeat(int columnNumber, int rowNumber,String userName){
        seats[columnNumber][rowNumber] = new Seat(columnNumber , rowNumber, userName);
    }


    public void addSeats(String userID,int column,int row){
            Login l = Login.getLoginObjectFromStorage(userID);
            Seat seat = findSeatByNumber(column, row);
            seat.reserveSeat(l);
    }

    public Seat findSeatByNumber(int column,int row){
            return seats[column][row];
    }

    public void reserveTheSeatAtRequiredPosition(int index,Login l){
        int index1 = 0;
        for(Seat[] s1: seats){
            for(Seat s2:s1){
                if(index1 == index){
                    s2.reserveSeat(l);
                }
                index1++;
            }
        }
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
        for (int i = 0; i < seats.length; i++) { // Outer loop for rows
            for (int j = 0; j < seats[i].length; j++) { // Inner loop for seats in each row
                if (!seats[i][j].getStatus()) {
                    System.out.println("Seat Number: " + index);
                }
                index++;
            }
        }

    }


    public String toString(){
        String s = "@"+numberOfRows+"_"+numberOfColumns;
        int index1 = 0;
        for(Seat[] column: seats){
            int index2 = 0;
            for(Seat row: column){
                String seatNumber = "@"+index1+"__"+index2+row;
                s = s + seatNumber;
                index2++;
            }
            index1++;
        }
        return s;
    }

}
