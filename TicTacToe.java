
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        boolean playAgain = true;

        // loop controls for multiple games ; if players want to play again
        while (playAgain) {

            int counter = 0;
            char piece;
            char turn;
            boolean horizontal, vertical, frontDiag, backDiag;

            // ask user for player size; final b/c it is immutable
            final int PLAYER_COUNT = askForNumOfPlayers();

            // create an array of Player objects to store players
            Player[] players = new Player[PLAYER_COUNT];

            // create an instance of board
            Board board = new Board(PLAYER_COUNT);

            // create an instance of gameLogic to use methods
            GameLogic gameLogic = new GameLogic();

            // returns a 2D array of board based on size
            String firstBoard[][] = board.generateBoard();

            // ask user for piece of choice, stores pieces in an array; returns array;
            char[] pieces = characterPiece(PLAYER_COUNT);

            // storing into array of Player objects
            while (counter < PLAYER_COUNT) {

                /*
                 * CHAR: assigning player piece into earlier array that stores each player's
                 * piece
                 * -> char[] pieces = characterPiece(playerCount)
                 */
                piece = pieces[counter];

                // PLAYER OBJECT ARRAY: creating each instance of player
                players[counter] = new Player(piece); // name and piece are set here b/c of Player constructor

                counter++;
            }

            // DISPLAY: printing every player's info
            for (int eachPlayer = 0; eachPlayer < PLAYER_COUNT; eachPlayer++) {
                System.out.println("Player " + (eachPlayer + 1) + ":");
                players[eachPlayer].printPlayerInfo();
                System.out.println();
            }

            // INT: condition to win; returns the # in a row to win based on user input
            int finalWinCondition = inARow(PLAYER_COUNT);

            // create a second instance of gameLogic with parameter
            GameLogic secondGameLogic = new GameLogic(finalWinCondition);

            // BOOLEAN: start game
            readyToPlay();

            // display board first time
            board.printBoard(firstBoard);

            // Figure out which player goes first, second... last ; returns int[] array
            int[] playerOrder = gameLogic.whoGoesFirst(PLAYER_COUNT);
            System.out.println("The order of the players is ");
            for (int i = 0; i < playerOrder.length; i++) {
                // can rewrite using enhanced for loop
                System.out.println(
                        "Player " + (playerOrder[i] + 1) + "'" + players[playerOrder[i]].getPiece() + "'" + ".");
            }
            System.out.println();

            int totalNumOfSquares = board.getSize(); // initialize the entire size of board based on player count
            int row = 0;
            int column = 0;
            boolean alreadyTaken = true;
            boolean isNotOver = true;
            int numPiecesOnBoard = 0; // counter for total num of pieces on board

            while (isNotOver && numPiecesOnBoard < totalNumOfSquares) {
                for (int j = 0; j < pieces.length; j++) {
                    turn = pieces[playerOrder[j]];

                    // display whose turn
                    System.out.print("Turn for Player " + turn + ".");
                    System.out.println();

                    while (alreadyTaken) {
                        // ask for row
                        row += askForRow();
                        if (row < 0 || row > PLAYER_COUNT) {
                            System.out.println(
                                    "Invalid row number. Please enter a number between 0 and " + PLAYER_COUNT + ".");
                            row = 0;
                        } else {
                            while (true) {
                                // ask for column
                                column += askForColumn();
                                if (column < 0 || column > PLAYER_COUNT) {
                                    System.out.println("Invalid column number. Please enter a number between 0 and "
                                            + PLAYER_COUNT + ".");
                                    column = 0;
                                } else {
                                    // checks whether a coordinate is occupied or not
                                    if (firstBoard[row + 1][column + 1].equals("  ")) {
                                        players[j].placePiece(firstBoard, row, column,
                                                players[playerOrder[j]].getPiece());
                                        alreadyTaken = false;
                                        break;
                                    } else {
                                        System.out.println(
                                                "There is already a piece there. Please choose another coordinate.");
                                        // need to reset row and column back to 0 if spot is already taken
                                        row = 0;
                                        column = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    numPiecesOnBoard++;
                    board.printBoard(firstBoard);
                    alreadyTaken = true;
                    row = 0;
                    column = 0;

                    // checks for any horizontal win
                    horizontal = secondGameLogic.checkHorizontal(firstBoard, turn,
                            PLAYER_COUNT + 1, secondGameLogic.getConditionToWin());
                    if (horizontal) {
                        System.out.println("Player " + turn + " has a horizontal win");
                        System.out.println("Player " + turn + " wins!!!");
                    }

                    // checks for any vertical win
                    vertical = secondGameLogic.checkVertical(firstBoard, turn,
                            PLAYER_COUNT + 1, secondGameLogic.getConditionToWin());
                    if (vertical) {
                        System.out.println("Player " + turn + " has a vertical win");
                        System.out.println("Player " + turn + " wins!!!");
                    }

                    // checks for any front diag win
                    frontDiag = secondGameLogic.checkFrontDiagonal(firstBoard, turn,
                            PLAYER_COUNT + 1, secondGameLogic.getConditionToWin());
                    if (frontDiag) {
                        System.out.println("Player " + turn + " has a front diagonal win");
                        System.out.println("Player " + turn + " wins!!!");
                    }

                    // checks for any back diag win
                    backDiag = secondGameLogic.checkBackwardDiagonal(firstBoard, turn,
                            PLAYER_COUNT + 1, secondGameLogic.getConditionToWin());
                    if (backDiag) {
                        System.out.println("Player " + turn + " has a backward diagonal win");
                        System.out.println("Player " + turn + " wins!!!");
                    }

                    // checks for a draw
                    if (numPiecesOnBoard == totalNumOfSquares && !vertical && !horizontal && !frontDiag && !backDiag)
                        System.out.println("It's a draw!");

                    // depending on result of game, ask players if they want to play another or not

                    if (horizontal || vertical || frontDiag || backDiag || numPiecesOnBoard == totalNumOfSquares) {
                        isNotOver = false;
                        break;
                    }
                }
            }

            Scanner scannerTwo = new Scanner(System.in);
            System.out.println("Do you want to play again? y for yes, n for no: ");
            while (true) {
                String answer = scannerTwo.nextLine();
                if (answer.equals("y")) {
                    playAgain = true;
                    break;
                } else if (answer.equals("n")) {
                    System.out.println("Good bye!");
                    playAgain = false;
                    break;
                } else {
                    System.out.println("Please enter y or n");
                }
            }
        }
    }

    // ************************** I/O METHODS
    // ************************************************

    // method to begin game and ask user for number of players; returns # of player
    public static int askForNumOfPlayers() {

        int numberOfPlayers;

        System.out.println("How many players are going to play?: ");

        while (true) {

            // try-catch clause is implemented to catch any InputMismatchException
            // exceptions
            try {
                Scanner scanner = new Scanner(System.in);
                numberOfPlayers = scanner.nextInt();

                // number of players has to be between 3 and 10 inclusive
                if (numberOfPlayers < 3 || numberOfPlayers > 10) {
                    System.out.println("Invalid number. Please enter a number between 3 and 10 inclusive.");
                } else
                    break;

            } catch (InputMismatchException exception) {
                System.out.println("Invalid input. Please enter a number between 3 and 10 inclusive.");
            }

        }

        return numberOfPlayers;
    }

    // method to create unique pieces for each player (LENGTH + CHAR) ; returns char
    // array of player pieces
    public static char[] characterPiece(int numberOfPlayers) {

        // declare array of char b/c each piece represents '1'
        char[] playerPiece = new char[numberOfPlayers];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a unique piece for each player (one character): ");

        // loop through each player
        for (int eachPlayer = 0; eachPlayer < numberOfPlayers; eachPlayer++) {

            boolean enterInputAgain = true;

            System.out.print("Player " + (eachPlayer + 1) + "'s piece: ");

            // VALIDATE PLAYER PIECE LENGTH & UNIQUE HERE
            while (enterInputAgain) {
                // DON'T NEED TO RESET 'enterInputAgain' B/C IT RESETS EACH ITERATION

                // need 'try-catch' for exception handling w/ the 'throw new <custom exception>'
                try {

                    String answer = (scanner.nextLine()); // ********* RECEIVE PLAYER PIECE HERE **********

                    if (answer.length() > 1) {
                        throw new InvalidPieceException(); // ONCE I THROW, I CATCH @ BOTTOM AND
                        // REPEAT THE WHILE LOOP WITH CURRENT PLAYER
                    }

                    if (answer.length() == 1) { // IF PLAYER PIECE IS ONE CHARACTER...

                        // ********* VALIDATE UNIQUE PLAYER PIECE *************************

                        // Player1's piece is already unique -> I can exit the loop
                        if (eachPlayer == 0) {
                            playerPiece[eachPlayer] = answer.charAt(0);
                            enterInputAgain = false;
                            continue;
                        }

                        // every other player besides Player1
                        else {

                            // first assign the piece
                            playerPiece[eachPlayer] = answer.charAt(0);

                            boolean stop = false;

                            // ALGORITHM: FIGURE OUT HOW TO MAKE SURE PIECE_X IS != TO PIECE_1 and so on
                            for (int i = eachPlayer; i < playerPiece.length; i++) {
                                for (int j = 0; j < i; j++) {

                                    // compares char value to the previous indices

                                    // if the pieces are the same AND playerPiece[i] and playerPiece[j] are not
                                    // equal to NULL or 0 in char
                                    if (playerPiece[i] == playerPiece[j] && playerPiece[i] != 0
                                            && playerPiece[j] != 0) {
                                        // for (int indigo = 0; indigo < playerPiece.length; indigo++) {
                                        //// System.out.println(playerPiece[indigo]);
                                        //// };
                                        System.out.println("Piece already exists. Please enter a different piece: ");
                                        stop = true;

                                        // exit inner loop
                                        break;
                                    }
                                }
                                // exit outer loop
                                if (stop)
                                    break;
                            }
                            // if none of the pieces are the same
                            if (!stop) {
                                enterInputAgain = false;
                            }
                        }
                    }
                } catch (InvalidPieceException e) {
                    System.out.println(e);
                }

            }
        }
        return playerPiece;
    }

    // method to get the number of pieces in a row to win; returns an integer
    public static int inARow(int numberOfPlayers) {

        int answer;
        System.out.println("How many pieces in a row to win?: ");

        while (true) {

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {

                answer = scanner.nextInt();

                // loop exits if user's input is between 3 and player count + 1, inclusive
                if (answer >= 3 && answer <= numberOfPlayers + 1) {
                    break;
                } else
                    System.out.println("Invalid number. Please enter a number between 3 - " + (numberOfPlayers + 1)
                            + " inclusive.");
            } else
                System.out.println("Please enter a number.");

        }

        return answer;
    }

    // method to ask user for row
    public static int askForRow() {

        int row = 0;
        boolean keepGoing = true;

        while (keepGoing) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Row: ");

            if (scanner.hasNextInt()) {
                row += scanner.nextInt();
                keepGoing = false;
            } else {
                System.out.println("Invalid input. please enter a number");
            }

        }

        return row;
    }

    // method to ask user for column
    public static int askForColumn() {

        int column = 0;
        boolean keepGoing = true;

        while (keepGoing) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Column: ");

            if (scanner.hasNextInt()) {
                column += scanner.nextInt();
                keepGoing = false;
            } else {
                System.out.println("Invalid input. please enter a number");
            }

        }

        return column;
    }

    // method to ask user if they are ready to begin; after collecting data about
    // players; returns true or exits program
    public static boolean readyToPlay() {
        Scanner scanner = new Scanner(System.in);
        String response;

        System.out.println("Are you ready to play? Enter 'y' for yes, 'n' for no: ");
        while (true) {
            response = scanner.nextLine();
            if (response.equals("y")) {
                break;
            } else if (response.equals("n")) {
                System.out.println("Exiting...see you next time!");
                System.exit(0);
            } else {
                System.out.println("Invalid answer. Please enter 'y' or 'n': ");
            }

        }
        return true;
    }

}
