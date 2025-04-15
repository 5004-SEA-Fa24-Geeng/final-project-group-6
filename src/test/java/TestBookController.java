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

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBookController {

    private IBookController controller;
    private IBookList bookList;

    private IBook book1;
    private IBook book2;
    private IBook book3;

    private Set<IBook> allBooks;

    @BeforeEach
    public void setup() {
        // Sample books
        book1 = new StandardBook("1234567890", "Book One", "Alice", "Fiction", "available");
        book2 = new StandardBook("9876543210L", "Book Two", "Bob", "Science", "available");
        book3 = new StandardBook("1111111111", "Book Three", "Charlie", "Drama", "unavailable");

        allBooks = new HashSet<>();
        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);

        bookList = new BookList();
        bookList.addToList("1234567890", allBooks.stream());
        bookList.addToList("9876543210", allBooks.stream());
        bookList.addToList("1111111111", allBooks.stream());
        controller = new BookController();
    }

    @Test
    public void testSuccessfulCheckOut() {
        controller.checkOutBooks(allBooks,"1234567890", bookList);
        assertEquals("unavailable", book1.getStatus());
    }

    @Test
    public void testSuccessfulCheckIn() {
        controller.checkInBooks(allBooks,"1111111111", bookList);
        assertEquals("available", book3.getStatus());
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
        controller.checkOutBooks(allBooks, "0000000000", bookList);; // not found
        assertEquals("available", book1.getStatus());
        assertEquals("available", book2.getStatus());
    }

    @Test
    public void testDisplayCheckoutList() {
        controller.checkOutBooks(allBooks, "1234567890", bookList);
        controller.checkOutBooks(allBooks, "9876543210", bookList);

        long count = allBooks.stream().filter(book -> book.getStatus().equalsIgnoreCase("unavailable")).count();
        assertEquals(3, count); // including the initially unavailable book
    }
}
