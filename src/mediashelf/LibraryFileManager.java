package mediashelf;

import java.io.File;
import java.io.IOException;

/**
 * Saves a library to a text file and loads it back.
 * LibraryFileManager has-a RecordParser.
 *
 * @author Aero Larson
 * @version Week 2
 */
public class LibraryFileManager {

    // has-a RecordParser
    private final RecordParser parser;

    /**
     * Makes a file manager with its own parser.
     */
    public LibraryFileManager() {
        parser = new RecordParser();
    }

    /**
     * Writes every item to the file, one per line.
     *
     * @param library what to save
     * @param file    where to save it
     * @throws IOException if the file can't be written
     */
    public void save(Library library, File file) throws IOException {
        // TODO: week 3
    }

    /**
     * Reads a library from a file. Skips blank lines.
     *
     * @param file the file to read
     * @return the loaded library
     * @throws IOException           if the file can't be read
     * @throws InvalidMediaException if a line is bad
     */
    public Library load(File file) throws IOException, InvalidMediaException {
        Library library = new Library();
        // TODO: week 3
        return library;
    }
}
