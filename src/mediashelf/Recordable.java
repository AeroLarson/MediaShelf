package mediashelf;

/**
 * Something that can be saved as one line of text.
 *
 * @author Aero Larson
 * @version Week 2
 */
public interface Recordable {

    /** Goes between fields in a saved line. */
    String SEPARATOR = "|";

    /**
     * @return this object as one line for the save file
     */
    String toRecord();
}
