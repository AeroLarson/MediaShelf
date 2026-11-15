package mediashelf;

/**
 * A movie. Movie is-a MediaItem.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class Movie extends MediaItem {

    private String director;
    private int minutes;

    /**
     * @param title    the title
     * @param year     release year
     * @param rating   1 to 5 stars
     * @param status   planned, in progress, or finished
     * @param director who directed it
     * @param minutes  length in minutes
     * @throws InvalidMediaException if a value is bad
     */
    public Movie(String title, int year, int rating, Status status,
            String director, int minutes) throws InvalidMediaException {
        super(title, year, rating, status);
        this.director = requireText(director, "Director");
        requireAtLeast(minutes, 1, "Minutes");
        this.minutes = minutes;
    }

    /**
     * @return MOVIE
     */
    @Override
    public MediaType getType() {
        return MediaType.MOVIE;
    }

    /**
     * @return the director
     */
    @Override
    public String getCreator() {
        return director;
    }

    /**
     * @return length in minutes
     */
    @Override
    public int getExtraDetail() {
        return minutes;
    }

    /**
     * @return like "2h 28m", or "45m" if under an hour
     */
    @Override
    public String getSummary() {
        int hours = minutes / 60;
        int leftover = minutes % 60;
        if (hours == 0) {
            return leftover + "m";
        }
        return hours + "h " + leftover + "m";
    }
}
