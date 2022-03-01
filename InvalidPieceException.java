public class InvalidPieceException extends Exception{
    // CUSTOM EXCEPTIONS NEED TO INHERIT FROM PARENT EXCEPTION

    private static String message = "Please enter a piece of length one.";

    // custom exception to ensure piece length is one character
    public InvalidPieceException () {
        super(message);
    }
}
