package mediashelf;

/**
 * Thrown when media data is bad, from the form or from a save file line.
 * InvalidMediaException is-an Exception.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class InvalidMediaException extends Exception {

    private static final long serialVersionUID = 1L;

    /** Means the error didn't come from a file. */
    public static final int NO_LINE = -1;

    private final int lineNumber;

    /**
     * For bad input that didn't come from a file.
     *
     * @param message what's wrong
     */
    public InvalidMediaException(String message) {
        this(message, NO_LINE);
    }

    /**
     * For a bad line in a save file.
     *
     * @param message    what's wrong
     * @param lineNumber line number in the file
     */
    public InvalidMediaException(String message, int lineNumber) {
        super(message);
        this.lineNumber = lineNumber;
    }

    /**
     * For a bad line in a save file, keeping the original exception.
     *
     * @param message    what's wrong
     * @param lineNumber line number in the file
     * @param cause      the original exception
     */
    public InvalidMediaException(String message, int lineNumber, Throwable cause) {
        super(message, cause);
        this.lineNumber = lineNumber;
    }

    /**
     * @return the line number, or NO_LINE
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @return true if the error came from a file line
     */
    public boolean hasLineNumber() {
        return lineNumber != NO_LINE;
    }
}
