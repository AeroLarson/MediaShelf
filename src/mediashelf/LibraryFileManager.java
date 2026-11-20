package mediashelf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Saves a library to a text file and loads it back.
 * LibraryFileManager has-a RecordParser.
 *
 * @author Aero Larson
 * @version Week 3
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
        // try-with-resources closes the file even if something goes wrong
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (MediaItem item : library.getItems()) {
                out.println(item.toRecord());
            }
            // PrintWriter doesn't throw, so check for errors here
            if (out.checkError()) {
                throw new IOException("Couldn't write to " + file.getName());
            }
        }
    }

    /**
     * Reads a library from a file. Skips blank lines.
     * If any line is bad, nothing gets loaded.
     *
     * @param file the file to read
     * @return the loaded library
     * @throws IOException           if the file can't be read
     * @throws InvalidMediaException if a line is bad
     */
    public Library load(File file) throws IOException, InvalidMediaException {
        Library library = new Library();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = in.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                library.addItem(parser.parse(line, lineNumber));
            }
        }
        return library;
    }
}
