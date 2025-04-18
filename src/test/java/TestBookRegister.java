import controller.*;
import model.BookList;
import model.IBook;
import model.IBookList;
import model.StandardBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.BookRegister;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBookRegister {

    private ByteArrayOutputStream output;
    private IBook book1;
    private IBook book2;
    private IBookController controller;
    private IBookList bookList;
    private IPlanner planner;

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @BeforeEach
    public void setup() {

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));



        // Sample books
        book1 = new StandardBook("1234567890", "Test Book One", "Alice", "Fiction", "available");
        book2 = new StandardBook("9876543210", "Test Book Two", "Bob", "Science", "unavailable");

        Set<IBook> books = new HashSet<>(Set.of(book1, book2));
        bookList = new BookList();
        bookList.addToList("1234567890", books.stream());
        bookList.addToList("9876543210", books.stream());

        planner = new Planner(books);
        controller = new BookController();

    }

    @AfterEach
    public void restoreIO() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testHelpCommand() {
        String input = "help\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BookRegister register = new BookRegister(bookList, planner, controller);
        register.start();

        String outContent = output.toString().toLowerCase();
        assertTrue(outContent.contains("commands"));
        assertTrue(outContent.contains("checkin"));
        assertTrue(outContent.contains("checkout"));
    }

    @Test
    public void testCheckOutValidISBN() {
        String input = "list add all\nlist checkout 1234567890\nlist\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BookRegister register = new BookRegister(bookList, planner, controller);
        register.start();

        String outContent = output.toString().toLowerCase();
        assertTrue(outContent.contains("checked out"));
        assertTrue(outContent.contains("test book one"));
    }

    @Test
    public void testCheckInInvalidISBNFormat() {
        String input = "list add all\nlist checkin abc123\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BookRegister register = new BookRegister(bookList, planner, controller);
        register.start();

        String outContent = output.toString();
        assertTrue(outContent.contains("Invalid ISBN format. Must be 10 or 13 digits."));

    }

    @Test
    public void testUnknownCommand() {
        String input = "hello world\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BookRegister register = new BookRegister(bookList, planner, controller);
        register.start();

        String outContent = output.toString().toLowerCase();
        assertTrue(outContent.contains("invalid") || outContent.contains("unknown"));
    }

    @Test
    public void testQuitCommand() {
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BookRegister register = new BookRegister(bookList, planner, controller);
        register.start();

        String outContent = output.toString().toLowerCase();
        assertTrue(outContent.contains("goodbye"));
    }
}
