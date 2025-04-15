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
import view.BookRegister;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBookRegister {

    private ByteArrayOutputStream output;
    private IBook book1;
    private IBook book2;
    private IBookController controller;

    @BeforeEach
    public void setup() {
        output = new ByteArrayOutputStream();

        book1 = new StandardBook(1234567890, "Test Book One", "Alice", "Fiction", "available");
        book2 = new StandardBook(9876543210L, "Test Book Two", "Bob", "Science", "unavailable");

        Set<IBook> books = new HashSet<>();
        books.add(book1);
        books.add(book2);

        IBookList bookList = new BookList(books);
        controller = new BookController(bookList, new Filters(), new Sorts());
    }

    @Test
    public void testHelpCommand() {
        String input = "help\nquit\n";
        BookRegister register = new BookRegister(
                new Scanner(new ByteArrayInputStream(input.getBytes())),
                output,
                controller
        );
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Available commands:"));
        assertTrue(outContent.contains("checkin"));
        assertTrue(outContent.contains("checkout"));
    }

    @Test
    public void testCheckOutValidISBN() {
        String input = "checkout 1234567890\nlist\nquit\n";
        BookRegister register = new BookRegister(
                new Scanner(new ByteArrayInputStream(input.getBytes())),
                output,
                controller
        );
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Checked out: Test Book One"));
        assertTrue(outContent.contains("Test Book One by Alice"));
    }

    @Test
    public void testCheckInInvalidISBNFormat() {
        String input = "checkin abc123\nquit\n";
        BookRegister register = new BookRegister(
                new Scanner(new ByteArrayInputStream(input.getBytes())),
                output,
                controller
        );
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Invalid ISBN format"));
    }

    @Test
    public void testUnknownCommand() {
        String input = "hello world\nquit\n";
        BookRegister register = new BookRegister(
                new Scanner(new ByteArrayInputStream(input.getBytes())),
                output,
                controller
        );
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Unknown command"));
    }

    @Test
    public void testQuitCommand() {
        String input = "quit\n";
        BookRegister register = new BookRegister(
                new Scanner(new ByteArrayInputStream(input.getBytes())),
                output,
                controller
        );
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Exiting the system"));
    }
}
