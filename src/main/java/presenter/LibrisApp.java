package presenter;

import controller.*;
import model.BookList;
import model.BookLoader;
import model.IBook;
import model.IBookList;
import view.BookRegister;


import java.util.Scanner;
import java.util.Set;

public class LibrisApp {
    public static void main(String[] args) {
        // Load books from Library.csv located in src/main/resources
        Set<IBook> books = BookLoader.loadBooksFromFile("/Library.csv");

        // Initialize BookList, controller.Filters, and controller.Sorts
        IBookList bookList = new BookList();
        IFilters filters = new Filters();
        ISorts sorts = new Sorts();

        // Create the controller and the UI entry
        IBookController controller = new BookController(bookList, filters, sorts);
        BookRegister register = new BookRegister(new Scanner(System.in), System.out, controller);

        // Start the command line interface
        register.start();
    }
}
