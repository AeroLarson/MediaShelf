package mediashelf;

/**
 * A video game. Game is-a MediaItem.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class Game extends MediaItem {

    private String platform;
    private int hoursPlayed;

    /**
     * @param title       the title
     * @param year        release year
     * @param rating      1 to 5 stars
     * @param status      planned, in progress, or finished
     * @param platform    like "Switch" or "PC"
     * @param hoursPlayed hours played so far
     * @throws InvalidMediaException if a value is bad
     */
    public Game(String title, int year, int rating, Status status,
            String platform, int hoursPlayed) throws InvalidMediaException {
        super(title, year, rating, status);
        this.platform = requireText(platform, "Platform");
        // 0 is ok if I haven't started it
        requireAtLeast(hoursPlayed, 0, "Hours played");
        this.hoursPlayed = hoursPlayed;
    }

    /**
     * @return GAME
     */
    @Override
    public MediaType getType() {
        return MediaType.GAME;
    }

    /**
     * @return the platform
     */
    @Override
    public String getCreator() {
        return platform;
    }

    /**
     * @return hours played
     */
    @Override
    public int getExtraDetail() {
        return hoursPlayed;
    }

    /**
     * @return like "52 hrs played"
     */
    @Override
    public String getSummary() {
        return hoursPlayed + " hrs played";
    }
}
