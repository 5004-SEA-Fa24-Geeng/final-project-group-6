import controller.BookController;
import controller.Filters;
import controller.IBookController;
import controller.Sorts;
import model.BookList;
import model.IBook;
import model.IBookList;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBookController {

    private IBookController controller;
    private IBookList bookList;

    private IBook book1;
    private IBook book2;
    private IBook book3;

    @TempDir
    Path tempDir;

    private Path libraryFile;
    private Path checkoutFile;
    private Set<IBook> allBooks;

    @BeforeEach
    public void setup() throws IOException {

        book1 = new StandardBook("1234567890", "Book One", "Alice", "Fiction", "available");
        book2 = new StandardBook("9876543210", "Book Two", "Bob", "Science", "available");
        book3 = new StandardBook("1111111111", "Book Three", "Charlie", "Drama", "unavailable");

        allBooks = new HashSet<>(Set.of(book1, book2, book3));


        libraryFile = tempDir.resolve("Library.csv");
        checkoutFile = tempDir.resolve("checkoutBook.csv");

        List<String> lines = List.of(
                "ISBN,Title,Author,Type,Status",
                "123456,Test Book One,Alice,STANDARD,available",
                "987654,Test Book Two,Bob,STANDARD,unavailable"
        );
        Files.write(libraryFile, lines);

        bookList = new BookList() {
            @Override
            public void updateLibrary(String filename, String ISBN, String newStatus) {
                super.updateLibrary(libraryFile.toString(), ISBN, newStatus);
            }

            @Override
            public void updateCheckoutFile(String filename, IBook book) {
                super.updateCheckoutFile(checkoutFile.toString(), book);
            }
        };

        allBooks.forEach(book -> bookList.addToList(book.getISBN(), allBooks.stream()));
        controller = new BookController();

        System.setErr(new PrintStream(OutputStream.nullOutputStream()));
    }


    @Test
    public void testCheckInBookUpdatesFile() throws IOException {
        controller.checkInBooks(allBooks, "987654", bookList);

        assertEquals("available", book2.getStatus());

        List<String> updatedLib = Files.readAllLines(libraryFile);
        assertTrue(updatedLib.stream().anyMatch(line -> line.contains("987654") && line.contains("available")));
    }

    @Test
    public void testCheckOutUnavailableBook() {
        controller.checkOutBooks(allBooks, "1111111111", bookList); // already unavailable
        assertEquals("unavailable", book3.getStatus());
    }

    @Test
    public void testCheckInAvailableBook() {
        controller.checkInBooks(allBooks, "1234567890", bookList); // already available
        assertEquals("available", book1.getStatus());
    }

    @Test
    public void testInvalidISBNCheckOut() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller.checkOutBooks(allBooks, "0000000000", bookList);

        String output = outContent.toString();
        assertTrue(output.contains("Book not found for checkout."));

        System.setOut(System.out);
    }

    @Test
    public void testDisplayCheckoutList() {

        assertEquals("available", book1.getStatus());
        assertEquals("available", book2.getStatus());
        assertEquals("unavailable", book3.getStatus());

        // Check out the available books
        controller.checkOutBooks(allBooks, "1234567890", bookList);
        controller.checkOutBooks(allBooks, "9876543210", bookList);

        long count = allBooks.stream()
                .filter(book -> book.getStatus().equalsIgnoreCase("unavailable"))
                .count();

        assertEquals(3, count);
    }
}
