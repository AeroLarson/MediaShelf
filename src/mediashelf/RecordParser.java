package mediashelf;

/**
 * Turns one line from a save file back into a MediaItem.
 * Line format: TYPE|title|year|rating|STATUS|creator|extra
 *
 * @author Aero Larson
 * @version Week 3
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

        MediaType type;
        Status status;
        try {
            type = MediaType.valueOf(fields[0].trim());
        } catch (IllegalArgumentException e) {
            throw new InvalidMediaException("Unknown type \"" + fields[0] + "\"", lineNumber, e);
        }
        try {
            status = Status.valueOf(fields[4].trim());
        } catch (IllegalArgumentException e) {
            throw new InvalidMediaException("Unknown status \"" + fields[4] + "\"", lineNumber, e);
        }

        int year = toInt(fields[2], "year", lineNumber);
        int rating = toInt(fields[3], "rating", lineNumber);
        int extra = toInt(fields[6], type.getExtraLabel().toLowerCase(), lineNumber);
        String title = fields[1];
        String creator = fields[5];

        try {
            switch (type) {
                case BOOK:
                    return new Book(title, year, rating, status, creator, extra);
                case MOVIE:
                    return new Movie(title, year, rating, status, creator, extra);
                default:
                    return new Game(title, year, rating, status, creator, extra);
            }
        } catch (InvalidMediaException e) {
            // same message, but add the line number
            throw new InvalidMediaException(e.getMessage(), lineNumber, e);
        }
    }

    /**
     * @param text       the text to turn into a number
     * @param fieldName  field name for the error message
     * @param lineNumber line number for the error message
     * @return the number
     * @throws InvalidMediaException if it's not a whole number
     */
    private int toInt(String text, String fieldName, int lineNumber)
            throws InvalidMediaException {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new InvalidMediaException("The " + fieldName + " \"" + text
                    + "\" isn't a whole number", lineNumber, e);
        }
    }
}
