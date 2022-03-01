import java.util.*;

public class GameLogic {

    private int conditionToWin;

    // CONSTRUCTORS
    public GameLogic(int conditionToWin) {
        this.conditionToWin = conditionToWin;
    }

    public GameLogic() {

    }

    // METHODS
    public int getConditionToWin() {
        return conditionToWin;
    }

    public int[] whoGoesFirst(int playerCount) {

        // initialize Integer array and int array
        Integer[] arr;
        int[] array;

        /* create array of Integers based on numOfPlayers (wrapper class)
         Side note: Integer wrapper class is useful when parsing from string into int */
        arr = new Integer[playerCount];

        // initialize each value in the array based on their original value
        for (int i = 0; i < playerCount; i++) {
            arr[i] = i;
        }

        // cast List onto Integer[] so we can use Collections.shuffle to randomize list
        List<Integer> myList = Arrays.asList(arr);

        // shuffle entries in an array (default shuffle) using Collections library
        Collections.shuffle(myList);

        // cast shuffled List to int[] array (also determines size of int[] array) using java streams
        array = myList.stream().mapToInt(i->i).toArray();

        return array;
    }



    // check for consecutive row win
    public boolean checkHorizontal (String[][] ticTacToeBoard, char piece, int columnRowSize, int conditionToWin) {

        boolean isAWin = false;
        int counter = 0;

        // iterates through each row of the board starting at row 1
        for (int eachRow = 1; eachRow < columnRowSize + 1; eachRow++) {
            // columnRowSize is = (playerCount + 1) then add another 1 so that it accounts for the entire # of rows

            // iterate through columns until conditionToWin away from the end so that it does not check over in the while loop
            for (int eachColumn = 1; eachColumn < columnRowSize - 1; eachColumn++ ) {

                // checks the current spot for the piece then checks each spot over
                while (ticTacToeBoard[eachRow][eachColumn].equals(piece + " ")) {
                    eachColumn++;
                    counter++;

                    // if a win -> announce a winner
                    if (counter == conditionToWin) {
                        isAWin = true;
                        break;
                    }
                }
                // reset counter if no winner found
                counter = 0;
            }
        }
        return isAWin;
    }

    // check for consecutive vertical win
    public boolean checkVertical (String[][] ticTacToeBoard, char piece, int columnRowSize, int conditionToWin) {
        boolean isAWin = false;
        int counter = 0;

        // iterates through each column of the board starting at column 1
        for (int eachColumn = 1; eachColumn < columnRowSize + 1; eachColumn++) {
            // columnRowSize is = (playerCount + 1) then add another 1 so that it accounts for the entire # of rows

            // iterate through row until conditionToWin away from the end so that it does not check over in the while loop
            for (int eachRow = 1; eachRow < columnRowSize - 1; eachRow++ ) {

                // checks the current spot for the piece then checks each spot over
                while (ticTacToeBoard[eachRow][eachColumn].equals(piece + " ")) {
                    eachRow++;
                    counter++;

                    // if a win -> announce a winner
                    if (counter == conditionToWin) {
                        isAWin = true;
                        break;
                    }
                }
                // reset counter if no winner found
                counter = 0;
            }
        }
        return isAWin;
    }

    // method to check for consecutive right falling diagonal win
    public boolean checkFrontDiagonal (String[][] ticTacToeBoard, char piece, int columnRowSize, int conditionToWin) {
        boolean isAWin = false;
        int counter = 0;

        // iterates through each column of the board starting at column 1
        for (int eachColumn = 1; eachColumn < columnRowSize - (conditionToWin - 2); eachColumn++) {
            // columnRowSize is = (playerCount + 1) then subtract 2 from condition
            // so that it accounts for the entire # of rows

            // iterate through row until conditionToWin away from the end so that it does not check over in the while loop
            for (int eachRow = 1; eachRow < columnRowSize - (conditionToWin - 2); eachRow++) {

                // checks the current spot for the piece then checks each spot over
                while (ticTacToeBoard[eachRow][eachColumn].equals(piece + " ")) {
                        eachRow++;
                        eachColumn++;
                        counter++;

                    // if a win -> announce a winner
                    if (counter == conditionToWin) {
                        isAWin = true;
                        break;
                    }
                }
                // reset counter if no winner found
                counter = 0;
            }
        }
        return isAWin;
    }


    // method to check for consecutive left falling diagonal win
    public boolean checkBackwardDiagonal (String[][] ticTacToeBoard, char piece, int columnRowSize, int conditionToWin) {
        boolean isAWin = false;
        int counter = 0;

        // iterates through each column of the board starting at last column
        for (int eachColumn = columnRowSize; eachColumn > 1 + (conditionToWin - 2); eachColumn--) {

            // iterate through row until conditionToWin away from the end so that it does not check over in the while loop
            for (int eachRow = 1; eachRow < columnRowSize - (conditionToWin - 2); eachRow++) {

                // checks the current spot for the piece then checks each spot over
                while (ticTacToeBoard[eachRow][eachColumn].equals(piece + " ")) {
                    eachRow++;
                    eachColumn--;
                    counter++;

                    // if a win -> announce a winner
                    if (counter == conditionToWin) {
                        isAWin = true;
                        break;
                    }
                }
                // reset counter if no winner found
                counter = 0;
            }
        }
        return isAWin;
    }
}