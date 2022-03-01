
public class Board {

    // instance variables
    private final int numOfPlayers;
    private int size;

    // constructor for board
    public Board(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.size = (numOfPlayers + 1) * (numOfPlayers + 1);
    }

    // getter to return num of players
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getSize() {
        return this.size;
    }

    // method to generate board of correct size according to num of players
    public String[][] generateBoard() {

        // declare char 2D array
        String[][] ticTacToeBoard;

        // board dimensions = numOfPlayers + 1 ; need to + 2 to account for the coordinates
        ticTacToeBoard = new String[numOfPlayers + 2][numOfPlayers + 2];

        return ticTacToeBoard;
    }

    // method print the board
    public void printBoard(String[][] ticTacToeBoard) {

        // initialize row 0, column 0 to empty string
        ticTacToeBoard[0][0] = "  ";

        // loop to print grid
        for (int row = 0; row <= numOfPlayers + 1; row++) {
            for (int column = 0; column <= numOfPlayers + 1; column++) {

                // condition to initialize column numbers
                if (row == 0 && column > 0)

                    // formatting for when printing 10 (since double-digit)
                    if (row == 0 && column == 11)
                        System.out.print(ticTacToeBoard[row][column] = column - 1 + "|");
                    else
                    // int cast to string becomes string ; prints all columns numbers besides 10
                    System.out.print(ticTacToeBoard[row][column] = column - 1 + " |");

                    // condition to initialize row numbers
                else if (row > 0 && column == 0)

                    // formatting for when printing 10 (since double-digit)
                    if (row == 11 && column == 0)
                        System.out.print(ticTacToeBoard[row][column] = row - 1 + "|");
                    else
                    // int cast to string becomes string ; prints all row numbers besides 10
                    System.out.print(ticTacToeBoard[row][column] = row - 1 + " |");

                    // condition that accounts for the inside of grid ; prints the empty string values
                else {
                    if (ticTacToeBoard[row][column] == null) {
                        ticTacToeBoard[row][column] = "  ";
                    }
                    System.out.print(ticTacToeBoard[row][column] + "|");
                }
            }
            System.out.println();

            // fancy '+' symbols within board
            if (row != numOfPlayers + 1 && row != 0)
                System.out.println("--+".repeat(numOfPlayers + 2));
            else
                System.out.println("---".repeat(numOfPlayers + 2));
        }
    }
}