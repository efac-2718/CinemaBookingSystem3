public class Screen extends Theater{

    private int numberOfColumns;
    private int numberOfRows;
    private Seat[][] seats;
    int index = 0;


    public Screen(int numberOfColumns,int numberOfRows) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.seats = new Seat[numberOfColumns][numberOfRows];
        for(int i = 0 ; i<numberOfColumns;i++){
            for(int j = 0 ; j<numberOfRows; j++){
                seats[i][j] = new Seat(i,j);
            }
        }


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
        for(int i = 0; i<seats.length;i++){
            for(int j = 0; j<seats[i].length;j++){
                index1 = Integer.parseInt(i+""+j);
                if(index1 == index){
                    seats[i][j].reserveSeat(l);
                }
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

        int lenght = seats.length;
        for (int i = 0; i < lenght; i++) {
            int rowLenght = seats[i].length;// Outer loop for rows
            for (int j = 0; j < rowLenght; j++) { // Inner loop for seats in each row
                if (!seats[i][j].getStatus()) {
                    System.out.print(seats[i][j].column+""+seats[i][j].row+"\t\t");
                }else{
                    System.out.print("X"+"\t\t");
                }
                index++;
            }
            System.out.println();
        }

    }
    public String toString(){

        int lenght = seats.length;
        String s = "@"+numberOfRows+"_"+numberOfColumns;
        for (int i = 0; i < lenght; i++) {
            int rowLenght = seats[i].length;
            for (int j = 0; j < rowLenght; j++) {
                String seatNumber = "@"+seats[i][j].column+"__"+seats[i][j].row+seats[i][j];
                s = s + seatNumber;
            }
        }
        return s;
    }

}

