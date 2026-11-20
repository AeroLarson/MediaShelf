package mediashelf;

import java.time.Year;

/**
 * One item in the collection. Abstract because every item is really a
 * Book, Movie, or Game.
 * MediaItem is-a Recordable and is-a Comparable. MediaItem has-a Status.
 *
 * @author Aero Larson
 * @version Week 3
 */
public abstract class MediaItem implements Recordable, Comparable<MediaItem> {

    /** Lowest rating. */
    public static final int MIN_RATING = 1;

    /** Highest rating. */
    public static final int MAX_RATING = 5;

    /** Earliest year allowed. */
    public static final int MIN_YEAR = 1450;

    private String title;
    private int year;
    private int rating;
    // has-a Status
    private Status status;

    /**
     * @param title  the title
     * @param year   release year
     * @param rating 1 to 5 stars
     * @param status planned, in progress, or finished
     * @throws InvalidMediaException if a value is bad
     */
    public MediaItem(String title, int year, int rating, Status status)
            throws InvalidMediaException {
        String cleanTitle = requireText(title, "Title");

        // next year is ok for upcoming releases
        int latestYear = Year.now().getValue() + 1;
        if (year < MIN_YEAR || year > latestYear) {
            throw new InvalidMediaException("Year must be between " + MIN_YEAR
                    + " and " + latestYear + " (got " + year + ")");
        }
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new InvalidMediaException("Rating must be " + MIN_RATING
                    + " to " + MAX_RATING + " (got " + rating + ")");
        }
        if (status == null) {
            throw new InvalidMediaException("Status must be picked");
        }

        this.title = cleanTitle;
        this.year = year;
        this.rating = rating;
        this.status = status;
    }

    /**
     * Makes sure a text field isn't blank.
     *
     * @param value     the text
     * @param fieldName field name for the error message
     * @return the text, trimmed
     * @throws InvalidMediaException if it's blank or has a |
     */
    protected static String requireText(String value, String fieldName)
            throws InvalidMediaException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidMediaException(fieldName + " can't be blank");
        }
        // | would break the save file
        if (value.contains(SEPARATOR)) {
            throw new InvalidMediaException(fieldName + " can't have a | in it");
        }
        return value.trim();
    }

    /**
     * Makes sure a number isn't too small.
     *
     * @param value     the number
     * @param minimum   smallest allowed
     * @param fieldName field name for the error message
     * @throws InvalidMediaException if it's too small
     */
    protected static void requireAtLeast(int value, int minimum,
            String fieldName) throws InvalidMediaException {
        if (value < minimum) {
            throw new InvalidMediaException(fieldName + " must be at least "
                    + minimum + " (got " + value + ")");
        }
    }

    /**
     * @return book, movie, or game
     */
    public abstract MediaType getType();

    /**
     * @return author, director, or platform
     */
    public abstract String getCreator();

    /**
     * @return pages, minutes, or hours played
     */
    public abstract int getExtraDetail();

    /**
     * @return the extra detail in words, like "310 pages"
     */
    public abstract String getSummary();

    /**
     * @return this item as one line for the save file
     */
    @Override
    public String toRecord() {
        // name() not toString() so valueOf() can read it back
        return getType().name() + SEPARATOR + title + SEPARATOR + year
                + SEPARATOR + rating + SEPARATOR + status.name()
                + SEPARATOR + getCreator() + SEPARATOR + getExtraDetail();
    }

    /**
     * Sorts by title, ignoring case.
     *
     * @param other the other item
     * @return negative, zero, or positive
     */
    @Override
    public int compareTo(MediaItem other) {
        return title.compareToIgnoreCase(other.title);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the new status
     * @throws InvalidMediaException if status is null
     */
    public void setStatus(Status status) throws InvalidMediaException {
        if (status == null) {
            throw new InvalidMediaException("Status must be picked");
        }
        this.status = status;
    }

    /**
     * @return the item as text, for testing
     */
    @Override
    public String toString() {
        return getType() + ": " + title + " (" + year + "), " + getCreator()
                + ", " + getSummary() + ", " + rating + "/" + MAX_RATING
                + ", " + status;
    }
}
