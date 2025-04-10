package presenter;

import controller.BookController;
import model.BookLoader;
import model.IBook;
import view.BookRegister;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Scanner;

public class LibrisApp {
    public static void main(String[] args) {
        //Create BookController object
        String checkOutFile = "checkedout.csv";
        String checkInFile = "checkin.csv";
        BookController controller = new BookController(checkOutFile, checkInFile);

        Collection<IBook> books = BookLoader.loadBooks("standard_books.csv", "illegal_books.csv");

        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner scanner = new Scanner(inputStream);

        BookRegister register = new BookRegister(scanner, outputStream, books, controller);
        // Start the BookRegister application
        register.start();
    }
}
