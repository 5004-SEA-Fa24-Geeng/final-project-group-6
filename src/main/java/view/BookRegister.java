package view;

import controller.BookController;
import model.IBook;
import util.InputValidator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class BookRegister {
    private final Scanner in;
    private final PrintStream out;
    private final Collection<IBook> books;
    private final BookController controller;


    public BookRegister(Scanner in, OutputStream out, Collection<IBook> books, BookController controller) {
        this.in = in;
        this.out = new PrintStream(out);
        this.books = books;
        this.controller = controller;
    }

    public void start() {
        out.println("Welcome to Libris! Enter a command: checkin <ISBN>, checkout <ISBN>, help, or quit.");

        while (true) {
            out.print("> ");
            String inputLine = in.nextLine().trim();

            if (inputLine.equalsIgnoreCase("quit")) {
                out.println("Exiting the system. Goodbye!");
                break;
            } else if (inputLine.equalsIgnoreCase("help")) {
                processHelp();
            } else if (inputLine.startsWith("checkin ")) {
                processCheckIn(inputLine.substring(8).trim());
            } else if (inputLine.startsWith("checkout ")) {
                processCheckOut(inputLine.substring(9).trim());
            } else {
                out.println("Unknown command. Type 'help' for guidance.");
            }
        }
    }

    public void processHelp() {
        out.println("Available commands:");
        out.println("  checkin <ISBN>   - Return a book");
        out.println("  checkout <ISBN>  - Borrow a book");
        out.println("  help             - Show this help message");
        out.println("  quit             - Exit the system");
    }


    public void processCheckIn(String isbn) {
        // Validate ISBN format before proceeding
        if (!InputValidator.isValidISBN(isbn)) {
            out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkInBooks(books, isbn);
    }

    public void processCheckOut(String ISBN) {
        // Validate ISBN format before proceeding
        if (!InputValidator.isValidISBN(ISBN)) {
            out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkOutBooks(books, ISBN);
    }
}
