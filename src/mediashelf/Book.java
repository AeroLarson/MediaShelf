package mediashelf;

/**
 * A book. Book is-a MediaItem.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class Book extends MediaItem {

    private String author;
    private int pages;

    /**
     * @param title  the title
     * @param year   year published
     * @param rating 1 to 5 stars
     * @param status planned, in progress, or finished
     * @param author who wrote it
     * @param pages  number of pages
     * @throws InvalidMediaException if a value is bad
     */
    public Book(String title, int year, int rating, Status status,
            String author, int pages) throws InvalidMediaException {
        super(title, year, rating, status);
        this.author = requireText(author, "Author");
        requireAtLeast(pages, 1, "Pages");
        this.pages = pages;
    }

    /**
     * @return BOOK
     */
    @Override
    public MediaType getType() {
        return MediaType.BOOK;
    }

    /**
     * @return the author
     */
    @Override
    public String getCreator() {
        return author;
    }

    /**
     * @return number of pages
     */
    @Override
    public int getExtraDetail() {
        return pages;
    }

    /**
     * @return like "310 pages"
     */
    @Override
    public String getSummary() {
        return pages + " pages";
    }
}
