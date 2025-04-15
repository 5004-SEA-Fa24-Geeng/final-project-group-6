package presenter;

import controller.*;
import model.BookList;
import model.BookLoader;
import model.IBookList;
import view.BookRegister;

public class LibrisApp {

    /** default location of Library. */
    private static final String DEFAULT_COLLECTION = "/Library.csv";

    /** private constructor as static class. */
    private LibrisApp() {}

    public static void main(String[] args) {

        IPlanner SabrinaPlanner = new Planner(BookLoader.loadBooksFromFile(DEFAULT_COLLECTION));
        IBookList list = new BookList();
        IBookController controller = new BookController();
        BookRegister app = new BookRegister(list, SabrinaPlanner, controller);
        app.start();

    }
}
