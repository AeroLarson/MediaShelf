package mediashelf;

/**
 * The kinds of media: book, movie, or game.
 * Each type knows its form labels, so the GUI doesn't need instanceof.
 *
 * @author Aero Larson
 * @version Week 2
 */
public enum MediaType {
    BOOK("Book", "Author", "Pages"),
    MOVIE("Movie", "Director", "Minutes"),
    GAME("Game", "Platform", "Hours played");

    private final String displayName;
    private final String creatorLabel;
    private final String extraLabel;

    /**
     * @param displayName  name shown to the user
     * @param creatorLabel label for the creator field
     * @param extraLabel   label for the number field
     */
    MediaType(String displayName, String creatorLabel, String extraLabel) {
        this.displayName = displayName;
        this.creatorLabel = creatorLabel;
        this.extraLabel = extraLabel;
    }

    /**
     * @return label for the creator field, like "Author"
     */
    public String getCreatorLabel() {
        return creatorLabel;
    }

    /**
     * @return label for the number field, like "Pages"
     */
    public String getExtraLabel() {
        return extraLabel;
    }

    /**
     * @return the name shown to the user
     */
    @Override
    public String toString() {
        return displayName;
    }
}
