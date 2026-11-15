package mediashelf;

/**
 * Counts how many items of each type got each star rating.
 * Uses a 2D array: rows are media types, columns are ratings.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class LibraryStats {

    // [type][rating - 1]
    private final int[][] ratingCounts;

    /**
     * Starts with all counts at 0.
     */
    public LibraryStats() {
        ratingCounts = new int[MediaType.values().length][MediaItem.MAX_RATING];
    }

    /**
     * Recounts everything from the library.
     *
     * @param library the library to count
     */
    public void compute(Library library) {
        // TODO: week 3
    }

    /**
     * @param type   the media type
     * @param rating 1 to 5
     * @return how many items of that type have that rating
     */
    public int getCount(MediaType type, int rating) {
        return ratingCounts[type.ordinal()][rating - 1];
    }

    /**
     * @param type the media type
     * @return total items of that type
     */
    public int getTotal(MediaType type) {
        // TODO: week 3
        return 0;
    }

    /**
     * @param type the media type
     * @return average rating for that type, or 0 if there are none
     */
    public double getAverageRating(MediaType type) {
        // TODO: week 3
        return 0.0;
    }
}
