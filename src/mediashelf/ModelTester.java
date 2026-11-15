package mediashelf;

/**
 * Quick console tests for the model classes until the GUI is done.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class ModelTester {

    private static int passed = 0;
    private static int failed = 0;

    /**
     * Runs the tests.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("--- making items ---");
        try {
            Book book = new Book("The Hobbit", 1937, 5, Status.FINISHED, "J.R.R. Tolkien", 310);
            Movie movie = new Movie("Inception", 2010, 4, Status.FINISHED, "Christopher Nolan", 148);
            Game game = new Game("Hollow Knight", 2017, 5, Status.IN_PROGRESS, "Switch", 52);
            System.out.println(book);
            System.out.println(movie);
            System.out.println(game);
            check("book summary", book.getSummary().equals("310 pages"));
            check("movie summary", movie.getSummary().equals("2h 28m"));
            check("game summary", game.getSummary().equals("52 hrs played"));
        } catch (InvalidMediaException e) {
            check("good items shouldn't throw: " + e.getMessage(), false);
        }

        System.out.println();
        System.out.println("--- bad values ---");
        tryBadBook("blank title", "", 2000, 3, "Author", 100);
        tryBadBook("year 1200", "Book", 1200, 3, "Author", 100);
        tryBadBook("rating 0", "Book", 2000, 0, "Author", 100);
        tryBadBook("rating 6", "Book", 2000, 6, "Author", 100);
        tryBadBook("blank author", "Book", 2000, 3, " ", 100);
        tryBadBook("0 pages", "Book", 2000, 3, "Author", 0);
        try {
            new Game("Game", 2000, 3, Status.PLANNED, "PC", -1);
            check("negative hours", false);
        } catch (InvalidMediaException e) {
            check("negative hours -> " + e.getMessage(), true);
        }

        System.out.println();
        System.out.println("--- library ---");
        try {
            Library library = new Library();
            library.addItem(new Book("The Hobbit", 1937, 5, Status.FINISHED, "J.R.R. Tolkien", 310));
            library.addItem(new Book("Project Hail Mary", 2021, 4, Status.IN_PROGRESS, "Andy Weir", 476));
            library.addItem(new Movie("Spirited Away", 2001, 5, Status.FINISHED, "Hayao Miyazaki", 125));
            library.addItem(new Movie("Inception", 2010, 4, Status.FINISHED, "Christopher Nolan", 148));
            library.addItem(new Game("Hollow Knight", 2017, 5, Status.IN_PROGRESS, "Switch", 52));
            library.addItem(new Game("Celeste", 2018, 4, Status.FINISHED, "PC", 18));
            library.addItem(new Book("Dune", 1965, 3, Status.PLANNED, "Frank Herbert", 412));
            library.addItem(new Movie("Dune: Part Two", 2024, 4, Status.PLANNED, "Denis Villeneuve", 166));
            check("8 items added", library.size() == 8);

            check("search dune", library.search("dune").size() == 2);
            check("search nolan (director)", library.search("nolan").size() == 1);
            check("filter games", library.filterByType(MediaType.GAME).size() == 2);

            library.sortByTitle();
            System.out.println("by title: " + titles(library));
            check("sort by title", library.getItems().get(0).getTitle().equals("Celeste"));

            library.sortByRating();
            System.out.println("by rating: " + titles(library));
            check("sort by rating", library.getItems().get(0).getRating() == 5
                    && library.getItems().get(7).getTitle().equals("Dune"));

            MediaItem first = library.getItems().get(0);
            check("remove", library.removeItem(first) && library.size() == 7);
        } catch (InvalidMediaException e) {
            check("library items shouldn't throw: " + e.getMessage(), false);
        }

        System.out.println();
        System.out.println("passed " + passed + ", failed " + failed);
    }

    /**
     * Makes a book that should fail and checks that it does.
     *
     * @param what   what's being tested
     * @param title  title
     * @param year   year
     * @param rating rating
     * @param author author
     * @param pages  pages
     */
    private static void tryBadBook(String what, String title, int year, int rating,
            String author, int pages) {
        try {
            new Book(title, year, rating, Status.PLANNED, author, pages);
            check(what, false);
        } catch (InvalidMediaException e) {
            check(what + " -> " + e.getMessage(), true);
        }
    }

    /**
     * Prints PASS or FAIL.
     *
     * @param what what was checked
     * @param ok   true if it passed
     */
    private static void check(String what, boolean ok) {
        if (ok) {
            passed++;
            System.out.println("PASS " + what);
        } else {
            failed++;
            System.out.println("FAIL " + what);
        }
    }

    /**
     * @param library the library
     * @return the titles in order, for printing
     */
    private static String titles(Library library) {
        String result = "";
        for (MediaItem item : library.getItems()) {
            result += item.getTitle() + ", ";
        }
        return result;
    }
}
