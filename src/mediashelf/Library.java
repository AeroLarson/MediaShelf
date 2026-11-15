package mediashelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Holds all the items and can search, filter, and sort them.
 * Library has-many MediaItems.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class Library {

    // has-many MediaItems
    private final ArrayList<MediaItem> items;

    /**
     * Makes an empty library.
     */
    public Library() {
        items = new ArrayList<MediaItem>();
    }

    /**
     * @param item the item to add
     */
    public void addItem(MediaItem item) {
        items.add(item);
    }

    /**
     * @param item the item to remove
     * @return true if it was removed
     */
    public boolean removeItem(MediaItem item) {
        return items.remove(item);
    }

    /**
     * Removes everything.
     */
    public void clear() {
        items.clear();
    }

    /**
     * @return number of items
     */
    public int size() {
        return items.size();
    }

    /**
     * @return a read-only list of the items
     */
    public List<MediaItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Finds items whose title or creator contains the text (not case sensitive).
     *
     * @param text what to search for
     * @return the matches, or everything if text is blank
     */
    public ArrayList<MediaItem> search(String text) {
        ArrayList<MediaItem> matches = new ArrayList<MediaItem>();
        if (text == null || text.trim().isEmpty()) {
            matches.addAll(items);
            return matches;
        }
        String target = text.trim().toLowerCase();
        for (MediaItem item : items) {
            if (item.getTitle().toLowerCase().contains(target)
                    || item.getCreator().toLowerCase().contains(target)) {
                matches.add(item);
            }
        }
        return matches;
    }

    /**
     * @param type the type to keep
     * @return all items of that type
     */
    public ArrayList<MediaItem> filterByType(MediaType type) {
        ArrayList<MediaItem> matches = new ArrayList<MediaItem>();
        for (MediaItem item : items) {
            if (item.getType() == type) {
                matches.add(item);
            }
        }
        return matches;
    }

    /**
     * Sorts A to Z by title.
     */
    public void sortByTitle() {
        Collections.sort(items);
    }

    /**
     * Sorts highest rating first. Ties go by title.
     */
    public void sortByRating() {
        Collections.sort(items, new RatingComparator());
    }

    /**
     * Compares by rating (high to low), then title.
     * RatingComparator is-a Comparator.
     */
    private static class RatingComparator implements Comparator<MediaItem> {

        /**
         * @param a first item
         * @param b second item
         * @return negative if a goes first
         */
        @Override
        public int compare(MediaItem a, MediaItem b) {
            int byRating = Integer.compare(b.getRating(), a.getRating());
            if (byRating != 0) {
                return byRating;
            }
            return a.compareTo(b);
        }
    }
}
