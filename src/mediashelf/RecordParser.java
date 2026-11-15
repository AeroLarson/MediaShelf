package mediashelf;

/**
 * Turns one line from a save file back into a MediaItem.
 * Line format: TYPE|title|year|rating|STATUS|creator|extra
 *
 * @author Aero Larson
 * @version Week 2
 */
public class RecordParser {

    /** Number of fields in one line. */
    public static final int FIELD_COUNT = 7;

    /**
     * @param line       the line from the file
     * @param lineNumber which line it is, for error messages
     * @return the item on that line
     * @throws InvalidMediaException if the line is bad
     */
    public MediaItem parse(String line, int lineNumber) throws InvalidMediaException {
        // -1 keeps empty fields at the end
        String[] fields = line.split("\\|", -1);
        if (fields.length != FIELD_COUNT) {
            throw new InvalidMediaException("Expected " + FIELD_COUNT
                    + " fields separated by | but found " + fields.length, lineNumber);
        }

        // TODO: week 3
        throw new InvalidMediaException("RecordParser.parse is not written yet", lineNumber);
    }
}
