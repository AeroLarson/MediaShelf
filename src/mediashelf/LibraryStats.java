package mediashelf;

/**
 * Counts how many items of each type got each star rating.
 * Uses a 2D array: rows are media types, columns are ratings.
 *
 * @author Aero Larson
 * @version Week 3
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
        // reset to 0 first
        for (int row = 0; row < ratingCounts.length; row++) {
            for (int col = 0; col < ratingCounts[row].length; col++) {
                ratingCounts[row][col] = 0;
            }
        }
        for (MediaItem item : library.getItems()) {
            ratingCounts[item.getType().ordinal()][item.getRating() - 1]++;
        }
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
        int total = 0;
        for (int count : ratingCounts[type.ordinal()]) {
            total += count;
        }
        return total;
    }

    /**
     * @param type the media type
     * @return average rating for that type, or 0 if there are none
     */
    public double getAverageRating(MediaType type) {
        int total = getTotal(type);
        if (total == 0) {
            return 0.0;
        }
        int sum = 0;
        int[] row = ratingCounts[type.ordinal()];
        for (int col = 0; col < row.length; col++) {
            // col 0 is 1 star
            sum += row[col] * (col + 1);
        }
        return (double) sum / total;
    }
}
