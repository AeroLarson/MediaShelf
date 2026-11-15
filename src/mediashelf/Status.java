package mediashelf;

/**
 * How far along I am with an item.
 *
 * @author Aero Larson
 * @version Week 2
 */
public enum Status {
    PLANNED("Planned"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    private final String displayName;

    /**
     * @param displayName name shown to the user
     */
    Status(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the name shown to the user
     */
    @Override
    public String toString() {
        return displayName;
    }
}
