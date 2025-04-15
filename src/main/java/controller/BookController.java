package controller;

import model.IBook;
import model.IBookList;

import java.util.Set;
import java.util.stream.Collectors;

public class BookController implements IBookController {
    private final IBookList bookList;
    private final IFilters filters;
    private final ISorts sorts;

    public BookController(IBookList bookList, IFilters filters, ISorts sorts) {
        this.bookList = bookList;
        this.filters = filters;
        this.sorts = sorts;
    }

    @Override
    public void checkOutBooks(String isbn) {
        IBook book = bookList.getBookByISBN(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return;
        }
        if (!book.getStatus().equalsIgnoreCase("available")) {
            System.out.println("Book is not available for checkout.");
            return;
        }
        book.setStatus("unavailable");
        System.out.println("Checked out: " + book.getBookTitle());
    }

    @Override
    public void checkInBooks(String isbn) {
        IBook book = bookList.getBookByISBN(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return;
        }
        if (!book.getStatus().equalsIgnoreCase("unavailable")) {
            System.out.println("Book is not currently checked out.");
            return;
        }
        book.setStatus("available");
        System.out.println("Checked in: " + book.getBookTitle());
    }

    @Override
    public void displayCheckoutList() {
        Set<IBook> checkedOutBooks = bookList.getAllBooks().stream()
                .filter(book -> book.getStatus().equalsIgnoreCase("unavailable"))
                .collect(Collectors.toSet());
        if (checkedOutBooks.isEmpty()) {
            System.out.println("No books currently checked out.");
        } else {
            checkedOutBooks.forEach(book ->
                    System.out.println(book.getBookTitle() + " by " + book.getAuthor() +
                            " (ISBN: " + book.getISBN() + ")"));
        }
    }
}
