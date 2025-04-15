package view;

import controller.BookController;
import controller.Filters;
import controller.IBookController;
import controller.Sorts;
import model.*;
import util.InputValidator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

public class BookRegister {
    private final Scanner in;
    private final PrintStream out;
    private final IBookController controller;

    public BookRegister(Scanner in, OutputStream out, IBookController controller) {
        this.in = in;
        this.out = new PrintStream(out);
        this.controller = controller;
    }

    public void start() {
        out.println("Welcome to Libris! Enter a command: checkin <ISBN>, checkout <ISBN>, list, help, or quit.");

        while (true) {
            out.print("> ");
            String inputLine = in.nextLine().trim();

            if (inputLine.equalsIgnoreCase("quit")) {
                out.println("Exiting the system. Goodbye!");
                break;
            } else if (inputLine.equalsIgnoreCase("help")) {
                processHelp();
            } else if (inputLine.equalsIgnoreCase("list")) {
                controller.displayCheckoutList();
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
        out.println("  list             - View currently checked-out books");
        out.println("  help             - Show this help message");
        out.println("  quit             - Exit the system");
    }

    public void processCheckIn(String isbn) {
        if (!InputValidator.isValidISBN(isbn)) {
            out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkInBooks(isbn);
    }

    public void processCheckOut(String isbn) {
        if (!InputValidator.isValidISBN(isbn)) {
            out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkOutBooks(isbn);
    }

    public static void main(String[] args) {
        Set<IBook> books = BookLoader.loadBooksFromFile("/Library.csv");
        IBookController controller = new BookController(new BookList(), new Filters(), new Sorts());
        BookRegister register = new BookRegister(new Scanner(System.in), System.out, controller);
        register.start();
    }
}
