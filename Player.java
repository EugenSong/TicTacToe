
public class Player {

    // INSTANCE VARIABLES
    private char piece;

    public Player(char piece) {
        this.piece = piece;
    }

    // validates whether spot is taken or not + places piece
    public void placePiece(String[][] board, int row, int column, char piece) {

        // have to +1 and -1 to adjust for the column and row numbers
        if (board[row + 1][column + 1].equals("  ")) {
            board[row + 1][column + 1] = piece + " ";
        } else {
            System.out.println("There is already a piece there. Please choose another coordinate.");
        }
    }

    // getter to return player piece; do not need parameters b/c it uses instance
    // variables
    public char getPiece() {
        return piece;
    }

    // setter to set player's piece
    public void setPiece(char piece) {
        this.piece = piece;
    }

    // method to print info
    public void printPlayerInfo() {
        System.out.print("Player piece -> " + piece);
    }

}
